package com.ajoudb.ajouwiki.network.checkemail

import com.google.gson.annotations.SerializedName

data class CheckEmailResponseBody(
    @SerializedName("result")
    val result: String?,
    @SerializedName("status")
    val status: String?
)