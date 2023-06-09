package com.ajoudb.ajouwiki.network.wiki

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface EditWikiDetailService {
    @Headers("Content-Type: application/json")
    @PUT("wikis/{id}/{detail_pk}")
    fun editWikiDetailByEnqueue(
        @Body addWiki: AddWikiDetailRequestBody,
        @Path("id") id: Int,
        @Path("detail_pk") detail_pk: Int
    ): Call<AddWikiDetailResponseBody>
}