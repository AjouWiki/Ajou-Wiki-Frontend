package com.ajoudb.ajouwiki.network.signup

data class SignUpRequestBody(
    val username: String?,
    val password: String?,
    val name: String?,
    val student_id: String?,
    val email: String?,
    val department: String?,
    val sex: String?
)
