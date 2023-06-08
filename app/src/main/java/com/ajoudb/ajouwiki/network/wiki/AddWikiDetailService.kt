package com.ajoudb.ajouwiki.network.wiki;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path

interface AddWikiDetailService {
    @Headers("Content-Type: application/json")
    @POST("wikis/{id}")
    fun addWikiDetailByEnqueue(
        @Body addWiki: AddWikiDetailRequestBody,
        @Path("id") id: Int
    ): Call<AddWikiDetailResponseBody>
}
