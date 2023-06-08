package com.ajoudb.ajouwiki.network.wiki

import com.ajoudb.ajouwiki.Wiki
import java.io.Serializable

/*Todo: API 정의에 맞게 새로 만들기*/

data class WikiListResponseBody (
    val wiki: List<Wiki>
): Serializable