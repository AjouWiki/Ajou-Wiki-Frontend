package com.ajoudb.ajouwiki.network.lock;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path

interface GetLockService {
    @Headers("Content-Type: application/json")
    @GET("wikis/{id}/{detail_pk}/GETLOCK")
    fun getLockByEnqueue(
        @Path("id") id: Int,
        @Path("detail_pk") detail_pk: Int
    ): Call<GetLockResponseBody>
}
