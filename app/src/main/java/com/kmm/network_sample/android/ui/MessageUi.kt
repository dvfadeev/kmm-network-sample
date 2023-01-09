package com.kmm.network_sample.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.kmm.network_sample.android.theme.AppTheme
import com.kmm.network_sample.core.message.domain.Message
import com.kmm.network_sample.core.message.ui.MessageComponent

/**
 * Displays a [Message] as a popup at the bottom of screen.
 */
@Composable
fun MessageUi(
    component: MessageComponent, modifier: Modifier = Modifier, bottomPadding: Dp
) {
    val additionalBottomPadding = with(LocalDensity.current) {
        16.dp
        // LocalMessageOffsets.current.values.maxOrNull()?.toDp() ?: 0.dp
    }
    val message by component.visibleMessage.subscribeAsState()
    AppTheme {
        MessagePopup(
            message = message,
            bottomPadding = bottomPadding + additionalBottomPadding,
            onAction = component::onActionClick
        )
    }
}

@Composable
private fun MessagePopup(
    message: Message, onAction: () -> Unit, bottomPadding: Dp
) {
    if (message !is Message.Popup) {
        return
    }
    Popup(
        alignment = Alignment.BottomCenter, properties = PopupProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 3.dp,
            modifier = Modifier
                .padding(bottom = bottomPadding, start = 8.dp, end = 8.dp)
                .wrapContentSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(vertical = 13.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                message.iconRes?.let {
                    Icon(
                        painter = painterResource(it), contentDescription = null, tint = MaterialTheme.colors.primary
                    )
                }
                Text(
                    modifier = Modifier.weight(1f),
                    text = message.text,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
                message.actionTitle?.let {
                    MessageButton(text = it, onClick = onAction)
                }
            }
        }
    }
}

@Composable
private fun MessageButton(
    text: String, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Text(
            text = text, style = MaterialTheme.typography.body2
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MessageUiPreview() {
    AppTheme {
        MessageUi(FakeMessageComponent(), Modifier, 40.dp)
    }
}

class FakeMessageComponent : MessageComponent {

    override val visibleMessage = MutableValue(Message.Popup(text = "Message"))

    override fun onActionClick() = Unit
}
