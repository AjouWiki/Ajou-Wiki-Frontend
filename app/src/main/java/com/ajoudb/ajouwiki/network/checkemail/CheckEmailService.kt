package com.ajoudb.ajouwiki.network.checkemail

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CheckEmailService {
    @Headers("Content-Type: application/json")
    @POST("users/is-email-available")
    fun checkEmailByEnqueue(@Body userEmail: CheckEmailRequestBody): Call<CheckEmailResponseBody>

}