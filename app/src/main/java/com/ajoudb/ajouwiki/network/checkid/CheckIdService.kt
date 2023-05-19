package com.ajoudb.ajouwiki.network.checkid

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CheckIdService {
    @Headers("Content-Type: application/json")
    @POST("users/is-username-available")
    fun checkIdByEnqueue(@Body username: CheckIdRequestBody): Call<CheckIdResponseBody>

}