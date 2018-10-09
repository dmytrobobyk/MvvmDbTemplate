package dmytro.com.mvvmdbtemplate.common.rest

import dmytro.com.mvvmdbtemplate.common.rest.exceptions.NetworkException
import dmytro.com.mvvmdbtemplate.common.rest.reseponse.BaseResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type


class CallAdapterFactory : CallAdapter.Factory() {

    private val factory = RxJava2CallAdapterFactory.create()

    override fun get(
            returnType: Type?,
            annotations: Array<out Annotation>?,
            retrofit: Retrofit?
    ): CallAdapter<BaseResponse, Single<BaseResponse>>? {

        @Suppress("UNCHECKED_CAST",
                "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val callAdapter = factory.get(returnType, annotations, retrofit)
                as CallAdapter<BaseResponse, Single<BaseResponse>>? ?: return null

        return RxCallAdapterWrapper(callAdapter)
    }

    private class RxCallAdapterWrapper<R : BaseResponse>(
            private val callAdapter: CallAdapter<R, Single<R>>
    ) : CallAdapter<R, Single<R>> {

        override fun responseType(): Type = callAdapter.responseType()

        override fun adapt(call: Call<R>): Single<R> {
            val url = call.request().url().toString()

            return callAdapter.adapt(call)
                    .onErrorResumeNext {
                        Single.error(asNetworkException(url, it))
                    }
                    .map(ResponseHandler<R>(url))
        }

        private fun asNetworkException(url: String, throwable: Throwable): NetworkException {
            return when (throwable) {
                is HttpException -> NetworkException.httpError(url, throwable)
                is IOException -> NetworkException.networkError(url, throwable)
                else -> NetworkException.unknownError(url, throwable)
            }
        }
    }
}
