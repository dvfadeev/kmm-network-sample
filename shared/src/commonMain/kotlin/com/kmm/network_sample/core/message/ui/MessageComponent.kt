package com.kmm.network_sample.core.message.ui

import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.core.message.domain.Message

/**
 * A component for centralized message showing. There should be only one instance
 * of this component in the app connected to the root component.
 */
interface MessageComponent {

    val visibleMessage: Value<Message>

    fun onActionClick()
}
