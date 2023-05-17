package com.ajoudb.ajouwiki.network.signin

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInService {
    @Headers("Content-Type: application/json")
    @POST("users/log-in")
    fun signInUserByEnqueue(@Body userInfo: SignInRequestBody): Call<SignInResponseBody>
}