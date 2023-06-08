package com.ajoudb.ajouwiki.network.search

import com.ajoudb.ajouwiki.Wiki
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponseBody(
    val result: List<Wiki>,
    val status: Int
):Serializable