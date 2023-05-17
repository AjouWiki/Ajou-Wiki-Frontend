package com.ajoudb.ajouwiki.network.signin

import com.ajoudb.ajouwiki.UserInfo
import com.google.gson.annotations.SerializedName

data class SignInResponseBody(
    @SerializedName("result")
    val result: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("user_info")
    val user_info: UserInfo?
)