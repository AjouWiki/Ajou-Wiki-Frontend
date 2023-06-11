package com.ajoudb.ajouwiki.network.lock;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET
import retrofit2.http.Headers;
import retrofit2.http.Path

interface LockService {
    @Headers("Content-Type: application/json")
    @GET("wikis/{id}/{detail_pk}/LOCK")
    fun lockByEnqueue(
        @Path("id") id: Int,
        @Path("detail_pk") detail_pk: Int
    ): Call<LockResponseBody>
}
