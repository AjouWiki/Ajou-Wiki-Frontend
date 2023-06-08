package com.ajoudb.ajouwiki.network.wiki

import java.io.Serializable

data class AddWikiDetailRequestBody(
    val title: String,
    val description: String
): Serializable
