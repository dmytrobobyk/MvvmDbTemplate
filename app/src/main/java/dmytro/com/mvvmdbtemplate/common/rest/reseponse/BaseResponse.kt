package dmytro.com.mvvmdbtemplate.common.rest.reseponse

import com.google.gson.annotations.SerializedName

abstract class BaseResponse : ResponseValidator {

    @SerializedName("response_code")
    var responseCode: String? = null

    @SerializedName("error_message")
    var errorMessage: String? = null

    @SerializedName("page")
    var page: Int? = null
}