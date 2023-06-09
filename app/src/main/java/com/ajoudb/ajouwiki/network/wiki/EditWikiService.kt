package com.ajoudb.ajouwiki.network.wiki

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface EditWikiService {
    @Headers("Content-Type: application/json")
    @PUT("wikis/{id}")
    fun editWikiByEnqueue(
        @Body addWiki: EditWikiRequestBody,
        @Path("id") id: Int
    ): Call<AddWikiResponseBody>
}