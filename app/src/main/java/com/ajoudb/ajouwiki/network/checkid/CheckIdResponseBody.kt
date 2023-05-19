package com.ajoudb.ajouwiki.network.checkid

import com.google.gson.annotations.SerializedName

data class CheckIdResponseBody(
    @SerializedName("result")
    val result: String?,
    @SerializedName("status")
    val status: String?
)