package com.ajoudb.ajouwiki.network.wiki

import com.google.gson.annotations.SerializedName

data class AddWikiResponseBody(
    @SerializedName("status")
    val status: Int?
)