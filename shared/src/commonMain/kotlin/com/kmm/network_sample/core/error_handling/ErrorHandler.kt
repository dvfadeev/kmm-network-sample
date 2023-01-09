package com.kmm.network_sample.core.error_handling

import com.kmm.network_sample.core.message.data.MessageService
import com.kmm.network_sample.core.message.domain.Message
import com.kmm.network_sample.core.network.UnauthorizedException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class ErrorHandler(
    private val messageService: MessageService,
    private val errorMessageFactory: ErrorMessageFactory
) {

    private val unauthorizedEventChannel = Channel<Unit>(Channel.UNLIMITED)

    val unauthorizedEventFlow = unauthorizedEventChannel.receiveAsFlow()

    // Used to not handle the same exception more than one time.
    private var lastHandledException: Exception? = null

    fun handleError(exception: Exception, showError: Boolean = true) {
        if (lastHandledException === exception) return
        lastHandledException = exception

        when {
            exception is UnauthorizedException -> {
                unauthorizedEventChannel.trySend(Unit)
            }

            showError -> {
                messageService.showMessage(
                    Message.Popup(text = errorMessageFactory.getMessage(exception))
                )
            }
        }
    }
}
