package com.ajoudb.ajouwiki.network.wiki

import java.io.Serializable

data class AddWikiDetailResponseBody(
    val title: String,
    val order: Int,
    val description: String,
    val wiki_id: Int,
    val user_id: Int
): Serializable
