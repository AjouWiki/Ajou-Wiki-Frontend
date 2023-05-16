package com.ajoudb.ajouwiki.network.signin

import com.google.gson.annotations.SerializedName

data class SignInResponseBody(
    @SerializedName("result")
    val result: String?,
    @SerializedName("status")
    val status: String?
)