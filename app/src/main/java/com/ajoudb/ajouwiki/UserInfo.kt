package com.ajoudb.ajouwiki

import java.io.Serializable

data class UserInfo (
    var id: String?,
    var name: String?,
    var studentNumber: String?,
    var email: String?,
    var department: String?,
    var sex: String?
):Serializable