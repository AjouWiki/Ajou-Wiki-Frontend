package com.ajoudb.ajouwiki.network.lock;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path

interface LockService {
    @Headers("Content-Type: application/json")
    @POST("wikis/{id}")
    fun addWikiDetailByEnqueue(
        @Body addWiki: LockRequestBody,
        @Path("id") id: Int
    ): Call<LockResponseBody>
}
