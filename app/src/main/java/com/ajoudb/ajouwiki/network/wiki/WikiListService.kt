package com.ajoudb.ajouwiki.network.wiki

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.GET

interface WikiListService {
    @Headers("Content-Type: application/json")
    @GET("wikis/")
    fun wikiListByEnqueue(): Call<WikiListResponseBody>
}