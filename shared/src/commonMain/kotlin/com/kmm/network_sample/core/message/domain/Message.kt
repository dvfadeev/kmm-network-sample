package com.kmm.network_sample.core.message.domain

sealed class Message {
    data class Popup(
        val text: String,
        val iconRes: Int? = null,
        val actionTitle: String? = null,
        val action: (() -> Unit)? = null
    ) : Message()

    object Empty : Message()
}
