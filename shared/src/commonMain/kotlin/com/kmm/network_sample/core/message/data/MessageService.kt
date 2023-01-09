package com.kmm.network_sample.core.message.data

import com.kmm.network_sample.core.message.domain.Message
import kotlinx.coroutines.flow.Flow

/**
 * A service for centralized message showing
 */
interface MessageService {

    val messageFlow: Flow<Message>

    fun showMessage(message: Message)
}
