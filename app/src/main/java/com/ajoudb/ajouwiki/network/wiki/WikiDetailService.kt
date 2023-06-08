package com.ajoudb.ajouwiki.network.wiki

import com.ajoudb.ajouwiki.WikiDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WikiDetailService {
    @Headers("Content-Type: application/json")
    @GET("wikis/{id}")
    fun wikiDetailByEnqueue(
        @Path("id") id: Int
    ): Call<WikiDetailResponseBody>
}