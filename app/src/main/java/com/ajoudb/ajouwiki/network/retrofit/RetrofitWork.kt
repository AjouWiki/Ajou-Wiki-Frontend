package com.ajoudb.ajouwiki.network.retrofit

import android.util.Log
import com.ajoudb.ajouwiki.LoginActivity
//import com.ajoudb.ajouwiki.network.emaildupcheck.EmailDupCheckRequestBody
//import com.ajoudb.ajouwiki.network.emaildupcheck.EmailDupCheckResponseBody
import com.ajoudb.ajouwiki.network.signin.SignInRequestBody
import com.ajoudb.ajouwiki.network.signin.SignInResponseBody
//import com.ajoudb.ajouwiki.network.signup.SignUpRequestBody
//import com.ajoudb.ajouwiki.network.signup.SignUpResponseBody
//import com.ajoudb.ajouwiki.network.studentnumberdupcheck.StudentNumberDupCheckRequestBody
//import com.ajoudb.ajouwiki.network.studentnumberdupcheck.StudentNumberDupCheckResponseBody
import retrofit2.Call
import retrofit2.Response

class RetrofitWork (){
//    fun signUpWork(userInfo: SignUpRequestBody) {
//        val service = RetrofitAPI.emgMedService
//
////        Call 작업은 두 가지로 실행됨
////        execute 를 사용하면 request 를 보내고 response 를 받는 행위를 동기적으로 수행한다.
////        enqueue 작업을 실행하면 request 는 비동기적으로 보내고, response 는 콜백으로 받게 된다.
//        service.addUserByEnqueue(userInfo)
//            .enqueue(object : retrofit2.Callback<SignUpResponseBody> {
//                override fun onResponse(
//                    call: Call<SignUpResponseBody>,
//                    response: Response<SignUpResponseBody>
//                ) {
//                    if (response.isSuccessful) {
//                        val result = response.body()
//                        Log.d("회원가입 성공", "$result")
//                    }
//                }
//
//                override fun onFailure(call: Call<SignUpResponseBody>, t: Throwable) {
//                    Log.d("회원가입 실패", t.message.toString())
//                }
//            })
//    }

    fun signInWork(userInfo: SignInRequestBody) {
        val service = RetrofitAPI.emgMedService

//        Call 작업은 두 가지로 실행됨
//        execute 를 사용하면 request 를 보내고 response 를 받는 행위를 동기적으로 수행한다.
//        enqueue 작업을 실행하면 request 는 비동기적으로 보내고, response 는 콜백으로 받게 된다.
        service.signInUserByEnqueue(userInfo)
            .enqueue(object : retrofit2.Callback<SignInResponseBody> {
                override fun onResponse(
                    call: Call<SignInResponseBody>,
                    response: Response<SignInResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        val status_code = result?.status
                        val user_info = result?.user_info
                        if (status_code == "200") {
                            Log.d("로그인 성공", "123123")
//                            Toast.makeText(LoginActivity().applicationContext, "로그인 성공", Toast.LENGTH_LONG).show()
                        }
                        else if (status_code == "403") {
                            Log.d("로그인 실패", "123123")

//                            Toast.makeText(LoginActivity().applicationContext, "로그인 실패", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                override fun onFailure(call: Call<SignInResponseBody>, t: Throwable) {
                    Log.d("로그인 실패2", "123123")

//                    Toast.makeText(LoginActivity().applicationContext, "로그인 실패", Toast.LENGTH_LONG).show()

                }
            })
    }

//    fun studentNumberDupCheckWork(userInfo: StudentNumberDupCheckRequestBody) {
//        val service = RetrofitAPI.emgMedService
//
////        Call 작업은 두 가지로 실행됨
////        execute 를 사용하면 request 를 보내고 response 를 받는 행위를 동기적으로 수행한다.
////        enqueue 작업을 실행하면 request 는 비동기적으로 보내고, response 는 콜백으로 받게 된다.
//        service.addUserByEnqueue(userInfo)
//            .enqueue(object : retrofit2.Callback<StudentNumberDupCheckResponseBody> {
//                override fun onResponse(
//                    call: Call<StudentNumberDupCheckResponseBody>,
//                    response: Response<StudentNumberDupCheckResponseBody>
//                ) {
//                    if (response.isSuccessful) {
//                        val result = response.body()
//                        Log.d("회원가입 성공", "$result")
//                    }
//                }
//
//                override fun onFailure(call: Call<StudentNumberDupCheckResponseBody>, t: Throwable) {
//                    Log.d("회원가입 실패", t.message.toString())
//                }
//            })
//    }
//    fun emailDupCheckWork(userInfo: EmailDupCheckRequestBody) {
//        val service = RetrofitAPI.emgMedService
//
////        Call 작업은 두 가지로 실행됨
////        execute 를 사용하면 request 를 보내고 response 를 받는 행위를 동기적으로 수행한다.
////        enqueue 작업을 실행하면 request 는 비동기적으로 보내고, response 는 콜백으로 받게 된다.
//        service.addUserByEnqueue(userInfo)
//            .enqueue(object : retrofit2.Callback<EmailDupCheckResponseBody> {
//                override fun onResponse(
//                    call: Call<EmailDupCheckResponseBody>,
//                    response: Response<EmailDupCheckResponseBody>
//                ) {
//                    if (response.isSuccessful) {
//                        val result = response.body()
//                        Log.d("회원가입 성공", "$result")
//                    }
//                }
//
//                override fun onFailure(call: Call<EmailDupCheckResponseBody>, t: Throwable) {
//                    Log.d("회원가입 실패", t.message.toString())
//                }
//            })
//    }
}