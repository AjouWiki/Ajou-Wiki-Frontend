package com.ajoudb.ajouwiki

import java.io.Serializable

data class WikiDetail (
    var id: Int?,
    var created_at: String?,
    var updated_at: String?,
    var title: String?,
    var order: Int?,
    var description: String?,
    var lock_user_id: Int?,
    var lock_expires_at: String?,
    var wiki_id: Int?,
    var user_id: Int?
):Serializable