package com.kmm.network_sample.core.network

import io.ktor.client.network.sockets.*
import io.ktor.utils.io.errors.*
import kotlinx.serialization.SerializationException

/**
 * Converts platform exceptions to [ApplicationException]s.
 */
object ErrorConverter {

    fun mapToFailureException(throwable: Throwable) = when (throwable) {
        is ApplicationException -> throwable
        is SerializationException -> DeserializationException(throwable)
        is SocketTimeoutException -> NoServerResponseException(throwable)
        is SSLHandshakeException -> SSLHandshakeException(throwable)
        is IOException -> (throwable.cause as? ApplicationException)
            ?: NoInternetException(throwable)
        else -> UnknownException(throwable, throwable.message ?: "Unknown")
    }
}
