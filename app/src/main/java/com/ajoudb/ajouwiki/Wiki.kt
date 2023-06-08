package com.ajoudb.ajouwiki

import java.io.Serializable

data class Wiki (
    var id: Int?,
    var name: String?,
    var wiki_details: List<WikiDetail>,
    var created_at: String?,
    var updated_at: String?,
    var user_id: Int?,
    var tags: List<Tag>,
    var lock_expires_at: String?,
    var lock_user_id: Int?
):Serializable