package com.ajoudb.ajouwiki.network.search

import com.ajoudb.ajouwiki.Wiki
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {
    @Headers("Content-Type: application/json")
    @GET("wikis/search/{search}")
    fun searchByEnqueue(
        @Path("search") search: String
    ): Call<SearchResponseBody>
}