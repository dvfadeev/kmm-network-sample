package com.kmm.network_sample.core.message.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.kmm.network_sample.core.message.data.MessageService
import com.kmm.network_sample.core.message.domain.Message
import com.kmm.network_sample.core.utils.componentCoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RealMessageComponent(
    componentContext: ComponentContext,
    private val messageService: MessageService,
) : ComponentContext by componentContext, MessageComponent {

    companion object {
        private const val SHOW_TIME = 4000L
    }

    private val coroutineScope = componentCoroutineScope()

    override val visibleMessage: MutableValue<Message> = MutableValue(Message.Empty)

    private var autoDismissJob: Job? = null

    init {
        lifecycle.doOnCreate(::collectMessages)
    }

    override fun onActionClick() {
        val message = visibleMessage.value
        if (message is Message.Popup) {
            autoDismissJob?.cancel()
            message.action?.invoke()
            visibleMessage.value = Message.Empty
        }

    }

    private fun collectMessages() {
        coroutineScope.launch {
            messageService.messageFlow.collect { messageData ->
                showMessage(messageData)
            }
        }
    }

    private fun showMessage(message: Message) {
        autoDismissJob?.cancel()
        visibleMessage.value = message
        autoDismissJob = coroutineScope.launch {
            delay(SHOW_TIME)
            visibleMessage.value = Message.Empty
        }
    }
}
