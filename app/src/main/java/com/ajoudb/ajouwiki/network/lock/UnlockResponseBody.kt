package com.ajoudb.ajouwiki.network.lock

import java.io.Serializable

data class UnlockResponseBody(
    val result: String,
    val status: Int
): Serializable
