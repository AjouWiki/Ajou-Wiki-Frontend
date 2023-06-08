package com.ajoudb.ajouwiki.network.wiki

import com.ajoudb.ajouwiki.Wiki
import java.io.Serializable

data class WikiDetailResponseBody(
    val result: Wiki,
    val status: Int
): Serializable