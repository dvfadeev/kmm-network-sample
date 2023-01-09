package com.kmm.network_sample.core.error_handling

/**
 * Returns human readable messages for exceptions.
 */

expect class ErrorMessageFactory() {

    fun getMessage(throwable: Throwable): String
}
