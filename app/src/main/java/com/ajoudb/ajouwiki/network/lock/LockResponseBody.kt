package com.ajoudb.ajouwiki.network.lock

import java.io.Serializable

data class LockResponseBody(
    val result: String,
    val status: Int
): Serializable
