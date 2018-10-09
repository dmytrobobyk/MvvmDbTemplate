package dmytro.com.mvvmdbtemplate.common.rest

import dmytro.com.mvvmdbtemplate.common.rest.exceptions.NetworkException
import dmytro.com.mvvmdbtemplate.common.rest.reseponse.BaseResponse
import io.reactivex.functions.Function
import dmytro.com.mvvmdbtemplate.common.rest.reseponse.*

class ResponseHandler<R : BaseResponse>(private val url: String) : Function<R, R> {

    override fun apply(response: R): R = when {
//        response.responseCode != SUCCESS -> {
        response.page == 0 -> {
            throw NetworkException.serverError(url,
                    response.responseCode ?: "null", response.errorMessage)
        }
        !response.isResponseValid() -> {
            throw NetworkException.invalidResponseError(url,
                    "${response.javaClass.simpleName} is not valid")
        }
        else -> response
    }

}