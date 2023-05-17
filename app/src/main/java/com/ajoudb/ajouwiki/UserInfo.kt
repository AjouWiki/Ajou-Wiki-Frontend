package com.ajoudb.ajouwiki

import java.io.Serializable

data class UserInfo (
    var name: String?,
    var student_id: String?,
    var email: String?,
    var department: String?,
    var sex: String?
):Serializable