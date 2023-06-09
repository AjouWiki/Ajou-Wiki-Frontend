package com.ajoudb.ajouwiki.network.wiki

import java.io.Serializable

data class EditWikiRequestBody(
    val tags: String,
): Serializable
