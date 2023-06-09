package com.ajoudb.ajouwiki.network.retrofit

import com.ajoudb.ajouwiki.TokenManager
import com.ajoudb.ajouwiki.network.wiki.AddWikiService
import com.ajoudb.ajouwiki.network.checkemail.CheckEmailService
import com.ajoudb.ajouwiki.network.checkid.CheckIdService
import com.ajoudb.ajouwiki.network.search.SearchService
import com.ajoudb.ajouwiki.network.signin.SignInService
import com.ajoudb.ajouwiki.network.signup.SignUpService
import com.ajoudb.ajouwiki.network.wiki.AddWikiDetailService
import com.ajoudb.ajouwiki.network.wiki.EditWikiDetailService
import com.ajoudb.ajouwiki.network.wiki.WikiDetailService
import com.ajoudb.ajouwiki.network.wiki.WikiListService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {
    private const val BASE_URL = "https://ajouwiki.onrender.com/api/v1/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) // 로그캣에서 패킷 내용을 모니터링 할 수 있음 (인터셉터)
            .build()
    }

    val signInService: SignInService by lazy {
        retrofit.create(SignInService::class.java)
    }
    val signUpService: SignUpService by lazy {
        retrofit.create(SignUpService::class.java)
    }
    val checkEmailService: CheckEmailService by lazy {
        retrofit.create(CheckEmailService::class.java)
    }
    val checkIdService: CheckIdService by lazy {
        retrofit.create(CheckIdService::class.java)
    }
    val wikiListService: WikiListService by lazy {
        val authToken = TokenManager.getToken()
        val authenticatedHttpClient = okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("Jwt", "$authToken")
                    .build()

                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(authenticatedHttpClient)
            .build()

        retrofit.create(WikiListService::class.java)
    }
    val searchService: SearchService by lazy {
        val authToken = TokenManager.getToken()
        val authenticatedHttpClient = okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("Jwt", "$authToken")
                    .build()

                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(authenticatedHttpClient)
            .build()

        retrofit.create(SearchService::class.java)
    }

    val wikiDetailService: WikiDetailService by lazy {
        val authToken = TokenManager.getToken()
        val authenticatedHttpClient = okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("Jwt", "$authToken")
                    .build()

                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(authenticatedHttpClient)
            .build()

        retrofit.create(WikiDetailService::class.java)
    }

    val addWikiService: AddWikiService by lazy {
        val authToken = TokenManager.getToken()
        val authenticatedHttpClient = okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("Jwt", "$authToken")
                    .build()

                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(authenticatedHttpClient)
            .build()

        retrofit.create(AddWikiService::class.java)
    }

    val addWikiDetailService: AddWikiDetailService by lazy{
        val authToken = TokenManager.getToken()
        val authenticatedHttpClient = okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("Jwt", "$authToken")
                    .build()

                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(authenticatedHttpClient)
            .build()

        retrofit.create(AddWikiDetailService::class.java)
    }

    val editWikiDetailService: EditWikiDetailService by lazy{
        val authToken = TokenManager.getToken()
        val authenticatedHttpClient = okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .addHeader("Jwt", "$authToken")
                    .build()

                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(authenticatedHttpClient)
            .build()

        retrofit.create(EditWikiDetailService::class.java)
    }
}