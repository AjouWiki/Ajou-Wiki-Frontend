package com.ajoudb.ajouwiki.network.signup

import com.ajoudb.ajouwiki.UserInfo

data class SignUpRequestBody(
    val user_info: UserInfo?
)
