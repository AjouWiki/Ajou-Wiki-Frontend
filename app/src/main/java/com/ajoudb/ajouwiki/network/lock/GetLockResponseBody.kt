package com.ajoudb.ajouwiki.network.lock

import java.io.Serializable

data class GetLockResponseBody(
    val result: Boolean,
    val user_pk: String,
    val expired_at: String
): Serializable
