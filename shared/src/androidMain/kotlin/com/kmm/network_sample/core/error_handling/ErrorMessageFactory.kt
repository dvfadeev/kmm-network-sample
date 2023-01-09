package com.kmm.network_sample.core.error_handling

import android.content.Context
import com.kmm.network_sample.R
import com.kmm.network_sample.core.network.*

actual class ErrorMessageFactory actual constructor() {

    private lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
    }

    actual fun getMessage(throwable: Throwable): String = when (throwable) {
        is ServerException, is DeserializationException -> getString(R.string.error_invalid_response)

        is NoServerResponseException -> getString(R.string.error_no_server_response)

        is NoInternetException -> getString(R.string.error_no_internet_connection)

        is SSLHandshakeException -> getString(R.string.error_ssl_handshake)

        is ExternalAppNotFoundException -> getString(R.string.error_matching_application_not_found)

        else -> getString(R.string.error_unexpected)
    }

    private fun getString(resId: Int) = context.getString(resId)
}