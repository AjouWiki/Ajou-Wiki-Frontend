package com.ajoudb.ajouwiki.network.retrofit

import android.util.Log
import com.ajoudb.ajouwiki.UserInfo
import com.ajoudb.ajouwiki.network.checkemail.CheckEmailRequestBody
import com.ajoudb.ajouwiki.network.checkemail.CheckEmailResponseBody
import com.ajoudb.ajouwiki.network.signin.SignInRequestBody
import com.ajoudb.ajouwiki.network.signin.SignInResponseBody
import com.ajoudb.ajouwiki.network.signup.SignUpRequestBody
import com.ajoudb.ajouwiki.network.signup.SignUpResponseBody
import retrofit2.Call
import retrofit2.Response

class RetrofitWork {
    fun signInWork(userInfo: SignInRequestBody,
                   onSuccessful: (UserInfo) -> Unit, onFailure: (Int) -> Unit) {
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
                        if (statusCode == "200") onSuccessful(userInfoForReturn!!)
                        else if (statusCode == "403") onFailure(1)
                    }
                }
                override fun onFailure(call: Call<SignInResponseBody>, t: Throwable) {onFailure(2)}
            })
    }

    fun checkEmailWork(userEmail: CheckEmailRequestBody,
                       onSuccessful: () -> Unit, onFailure: (Int) -> Unit) {
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
                            onSuccessful()
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

    fun signUpUserWork(userInfo: SignUpRequestBody,
                       onSuccessful: () -> Unit, onFailure: (Int) -> Unit) {
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
                        Log.d("statuscode", "$statusCode")
                        if (statusCode == "200") onSuccessful()
                        else onFailure(1)
                    }
                }
                override fun onFailure(call: Call<SignUpResponseBody>, t: Throwable) {
                    onFailure(2)
                }
            })
    }

}