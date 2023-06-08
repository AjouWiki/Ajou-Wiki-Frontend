package com.ajoudb.ajouwiki.network.retrofit

import android.util.Log
import com.ajoudb.ajouwiki.TokenManager
import com.ajoudb.ajouwiki.UserInfo
import com.ajoudb.ajouwiki.network.checkemail.CheckEmailRequestBody
import com.ajoudb.ajouwiki.network.checkemail.CheckEmailResponseBody
import com.ajoudb.ajouwiki.network.checkid.CheckIdRequestBody
import com.ajoudb.ajouwiki.network.checkid.CheckIdResponseBody
import com.ajoudb.ajouwiki.network.signin.SignInRequestBody
import com.ajoudb.ajouwiki.network.signin.SignInResponseBody
import com.ajoudb.ajouwiki.network.signup.SignUpRequestBody
import com.ajoudb.ajouwiki.network.signup.SignUpResponseBody
import com.ajoudb.ajouwiki.network.wiki.WikiListResponseBody
import retrofit2.Call
import retrofit2.Response

class RetrofitWork {
    fun signInWork(userInfo: SignInRequestBody,
                   onSuccess: (UserInfo) -> Unit, onFailure: (Int) -> Unit) {
        val service = RetrofitAPI.signInService

        service.signInUserByEnqueue(userInfo)
            .enqueue(object : retrofit2.Callback<SignInResponseBody> {
                override fun onResponse(
                    call: Call<SignInResponseBody>,
                    response: Response<SignInResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        val statusCode = result?.status
                        val userInfoForReturn = result?.user_info
                        val token = result?.token
                        TokenManager.setToken(token!!)
                        if (statusCode == "200") onSuccess(userInfoForReturn!!)
                        else if (statusCode == "403") onFailure(1)
                        else if (statusCode == "401") onFailure(3)
                    }
                }
                override fun onFailure(call: Call<SignInResponseBody>, t: Throwable) {onFailure(2)}
            })
    }
    fun signUpUserWork(userInfo: SignUpRequestBody,
                       onSuccess: () -> Unit, onFailure: (Int) -> Unit) {
        val service = RetrofitAPI.signUpService

        service.signUpUserByEnqueue(userInfo)
            .enqueue(object : retrofit2.Callback<SignUpResponseBody> {
                override fun onResponse(
                    call: Call<SignUpResponseBody>,
                    response: Response<SignUpResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        val statusCode = result?.status
                        if (statusCode == "200") onSuccess()
                        else onFailure(1)
                    }
                }
                override fun onFailure(call: Call<SignUpResponseBody>, t: Throwable) {
                    onFailure(2)
                }
            })
    }

    fun checkEmailWork(userEmail: CheckEmailRequestBody,
                       onSuccess: () -> Unit, onFailure: (Int) -> Unit) {
        val service = RetrofitAPI.checkEmailService

        service.checkEmailByEnqueue(userEmail)
            .enqueue(object : retrofit2.Callback<CheckEmailResponseBody> {
                override fun onResponse(
                    call: Call<CheckEmailResponseBody>,
                    response: Response<CheckEmailResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        val statusCode = result?.status
                        if (statusCode == "200") {
                            onSuccess()
                        }
                        else if (statusCode == "403") {
                            onFailure(1)
                        }
                    }
                }
                override fun onFailure(call: Call<CheckEmailResponseBody>, t: Throwable) {
                    onFailure(2)

                }
            })
    }
    fun checkIdWork(username: CheckIdRequestBody,
                    onSuccess: () -> Unit, onFailure: (Int) -> Unit) {
        val service = RetrofitAPI.checkIdService

        service.checkIdByEnqueue(username)
            .enqueue(object : retrofit2.Callback<CheckIdResponseBody> {
                override fun onResponse(
                    call: Call<CheckIdResponseBody>,
                    response: Response<CheckIdResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        val statusCode = result?.status
                        if (statusCode == "200") {
                            onSuccess()
                        }
                        else if (statusCode == "403") {
                            onFailure(1)
                        }
                    }
                }
                override fun onFailure(call: Call<CheckIdResponseBody>, t: Throwable) {
                    onFailure(2)

                }
            })
    }
    fun wikiWork(onSuccess: () -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.wikiListService

        service.wikiListByEnqueue()
            .enqueue(object : retrofit2.Callback<WikiListResponseBody> {
                override fun onResponse(
                    call: Call<WikiListResponseBody>,
                    response: Response<WikiListResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        print(result)
                        onSuccess()
                    }
                }
                override fun onFailure(call: Call<WikiListResponseBody>, t: Throwable) {
                    onFailure()

                }
            })
    }
}