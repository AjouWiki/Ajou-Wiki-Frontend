package com.ajoudb.ajouwiki.network.signin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET
import retrofit2.http.Headers;
import retrofit2.http.Path

interface LockService {
    @Headers("Content-Type: application/json")
    @GET("wikis/{id}")
    fun GetLockByEnqueue(
        @Body id: LockRequestBody,
        @Path("id") wiki_id: Int
    ): Call<LockResponseBody>
}
