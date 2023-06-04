package com.ajoudb.ajouwiki.network.wiki

import java.io.Serializable

/*Todo: API 정의에 맞게 새로 만들기*/

data class WikiListResponseBody (
    var thumbnailPath: String?,
    var name: String?,
    var tag: String?,
): Serializable