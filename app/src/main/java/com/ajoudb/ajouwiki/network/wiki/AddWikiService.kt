package com.ajoudb.ajouwiki.network.wiki

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AddWikiService {
    @Headers("Content-Type: application/json")
    @POST("wikis/")
    fun addWikiByEnqueue(@Body wikiinfo: AddWikiRequestBody): Call<AddWikiResponseBody>

}