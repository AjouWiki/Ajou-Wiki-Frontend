package com.ajoudb.ajouwiki.network.signup

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpService {
    @Headers("Content-Type: application/json")
    @POST("users/")
    fun signUpUserByEnqueue(@Body userInfo: SignUpRequestBody): Call<SignUpResponseBody>
}