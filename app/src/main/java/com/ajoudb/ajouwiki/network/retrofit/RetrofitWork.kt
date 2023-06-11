package com.ajoudb.ajouwiki.network.retrofit

import com.ajoudb.ajouwiki.TokenManager
import com.ajoudb.ajouwiki.UserInfo
import com.ajoudb.ajouwiki.Wiki
import com.ajoudb.ajouwiki.network.wiki.AddWikiRequestBody
import com.ajoudb.ajouwiki.network.wiki.AddWikiResponseBody
import com.ajoudb.ajouwiki.network.checkemail.CheckEmailRequestBody
import com.ajoudb.ajouwiki.network.checkemail.CheckEmailResponseBody
import com.ajoudb.ajouwiki.network.checkid.CheckIdRequestBody
import com.ajoudb.ajouwiki.network.checkid.CheckIdResponseBody
import com.ajoudb.ajouwiki.network.lock.GetLockResponseBody
import com.ajoudb.ajouwiki.network.lock.LockResponseBody
import com.ajoudb.ajouwiki.network.lock.UnlockResponseBody
import com.ajoudb.ajouwiki.network.search.SearchResponseBody
import com.ajoudb.ajouwiki.network.signin.SignInRequestBody
import com.ajoudb.ajouwiki.network.signin.SignInResponseBody
import com.ajoudb.ajouwiki.network.signup.SignUpRequestBody
import com.ajoudb.ajouwiki.network.signup.SignUpResponseBody
import com.ajoudb.ajouwiki.network.wiki.AddWikiDetailRequestBody
import com.ajoudb.ajouwiki.network.wiki.AddWikiDetailResponseBody
import com.ajoudb.ajouwiki.network.wiki.EditWikiRequestBody
import com.ajoudb.ajouwiki.network.wiki.WikiDetailResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.Date

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
                        if (statusCode == "200") {
                            TokenManager.setToken(token!!)
                            onSuccess(userInfoForReturn!!)
                        }
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
    fun wikiWork(onSuccess: (wikiList: List<Wiki>) -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.wikiListService

        service.wikiListByEnqueue()
            .enqueue(object : retrofit2.Callback<List<Wiki>> {
                override fun onResponse(
                    call: Call<List<Wiki>>,
                    response: Response<List<Wiki>>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        onSuccess(result!!)
                    }
                    else {
                        onFailure()
                    }
                }
                override fun onFailure(call: Call<List<Wiki>>, t: Throwable) {
                    onFailure()

                }
            })
    }
    fun searchWork(searchKW: String,
                   onSuccess: (wikiList: SearchResponseBody) -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.searchService

        service.searchByEnqueue(searchKW)
            .enqueue(object : retrofit2.Callback<SearchResponseBody> {
                override fun onResponse(
                    call: Call<SearchResponseBody>,
                    response: Response<SearchResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        onSuccess(result!!)
                    }
                    else {
                        onFailure()
                    }
                }
                override fun onFailure(call: Call<SearchResponseBody>, t: Throwable) {
                    onFailure()

                }
            })
    }

    fun wikiDetailWork(id: Int,
                   onSuccess: (wikiDetail: WikiDetailResponseBody) -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.wikiDetailService

        service.wikiDetailByEnqueue(id)
            .enqueue(object : retrofit2.Callback<WikiDetailResponseBody> {
                override fun onResponse(
                    call: Call<WikiDetailResponseBody>,
                    response: Response<WikiDetailResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        onSuccess(result!!)
                    }
                    else {
                        onFailure()
                    }
                }
                override fun onFailure(call: Call<WikiDetailResponseBody>, t: Throwable) {
                    onFailure()

                }
            })
    }

    fun addWikiWork(wikiinfo: AddWikiRequestBody,
                    onSuccess: () -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.addWikiService

        service.addWikiByEnqueue(wikiinfo)
            .enqueue(object : retrofit2.Callback<AddWikiResponseBody> {
                override fun onResponse(
                    call: Call<AddWikiResponseBody>,
                    response: Response<AddWikiResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        val statusCode = result?.status
                        if (statusCode == 200) {
                            onSuccess()
                        }
                        else onFailure()
                    }
                }
                override fun onFailure(call: Call<AddWikiResponseBody>, t: Throwable) {
                    onFailure()

                }
            })
    }

    fun addWikiDetailWork(wikiDetail: AddWikiDetailRequestBody, id: Int,
                          onSuccess: () -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.addWikiDetailService

        service.addWikiDetailByEnqueue(wikiDetail, id)
            .enqueue(object : retrofit2.Callback<AddWikiDetailResponseBody> {
                override fun onResponse(
                    call: Call<AddWikiDetailResponseBody>,
                    response: Response<AddWikiDetailResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val statusCode = response.code()
                        if (statusCode == 200) {
                            onSuccess()
                        }
                        else onFailure()
                    }
                }
                override fun onFailure(call: Call<AddWikiDetailResponseBody>, t: Throwable) {
                    onFailure()

                }
            })
    }

    fun editWikiDetailWork(wikiDetail: AddWikiDetailRequestBody, id: Int, detail_pk: Int,
                          onSuccess: () -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.editWikiDetailService

        service.editWikiDetailByEnqueue(wikiDetail, id, detail_pk)
            .enqueue(object : retrofit2.Callback<AddWikiDetailResponseBody> {
                override fun onResponse(
                    call: Call<AddWikiDetailResponseBody>,
                    response: Response<AddWikiDetailResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val statusCode = response.code()
                        if (statusCode == 200) {
                            onSuccess()
                        }
                        else onFailure()
                    }
                }
                override fun onFailure(call: Call<AddWikiDetailResponseBody>, t: Throwable) {
                    onFailure()

                }
            })
    }

    fun editWikiWork(tags: EditWikiRequestBody, id: Int,
                           onSuccess: () -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.editWikiService

        service.editWikiByEnqueue(tags, id)
            .enqueue(object : retrofit2.Callback<AddWikiResponseBody> {
                override fun onResponse(
                    call: Call<AddWikiResponseBody>,
                    response: Response<AddWikiResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val statusCode = response.code()
                        if (statusCode == 200) {
                            onSuccess()
                        }
                        else onFailure()
                    }
                }
                override fun onFailure(call: Call<AddWikiResponseBody>, t: Throwable) {
                    onFailure()

                }
            })
    }

    fun getLockWork(id: Int, detail_pk: Int,
                    onSuccess: () -> Unit, onFailure: (expiredAt: String) -> Unit) {
        val service = RetrofitAPI.getLockService

        service.getLockByEnqueue(id, detail_pk)
            .enqueue(object : retrofit2.Callback<GetLockResponseBody> {
                override fun onResponse(
                    call: Call<GetLockResponseBody>,
                    response: Response<GetLockResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        if (result?.result == false) onSuccess()
                        else {
                            onFailure(result?.expired_at!!)
                        }
                    }
                }
                override fun onFailure(call: Call<GetLockResponseBody>, t: Throwable) {
                    onFailure("Fail")
                }
            })
    }
    fun lockWork(id: Int, detail_pk: Int,
                    onSuccess: () -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.lockService

        service.lockByEnqueue(id, detail_pk)
            .enqueue(object : retrofit2.Callback<LockResponseBody> {
                override fun onResponse(
                    call: Call<LockResponseBody>,
                    response: Response<LockResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        if (result?.status == 200) onSuccess()
                        else {
                            onFailure()
                        }
                    }
                }
                override fun onFailure(call: Call<LockResponseBody>, t: Throwable) {
                    onFailure()

                }
            })
    }
    fun unlockWork(id: Int, detail_pk: Int,
                    onSuccess: () -> Unit, onFailure: () -> Unit) {
        val service = RetrofitAPI.unlockService

        service.unlockByEnqueue(id, detail_pk)
            .enqueue(object : retrofit2.Callback<UnlockResponseBody> {
                override fun onResponse(
                    call: Call<UnlockResponseBody>,
                    response: Response<UnlockResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        if (result?.status == 200) onSuccess()
                        else {
                            onFailure()
                        }
                    }
                }
                override fun onFailure(call: Call<UnlockResponseBody>, t: Throwable) {
                    onFailure()
                }
            })
    }
}