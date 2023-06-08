package com.ajoudb.ajouwiki.network.addwiki

import com.google.gson.annotations.SerializedName

data class AddWikiResponseBody(
    @SerializedName("status")
    val status: Int?
)