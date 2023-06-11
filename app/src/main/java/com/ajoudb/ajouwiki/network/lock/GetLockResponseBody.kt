package com.ajoudb.ajouwiki.network.lock

import java.io.Serializable

data class LockResponseBody(
    val result: String,
    val user_pk: String,
    val expired_at: String
): Serializable
