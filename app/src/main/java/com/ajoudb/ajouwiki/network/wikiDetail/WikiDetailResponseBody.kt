package com.ajoudb.ajouwiki.network.wikiDetail

import com.ajoudb.ajouwiki.Wiki
import java.io.Serializable

data class WikiDetailResponseBody (
    val wiki: List<Wiki>
): Serializable