package com.kmm.network_sample.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.android.theme.AppTheme
import com.kmm.network_sample.android.theme.customColors
import com.kmm.network_sample.core.message.ui.MessageComponent
import com.kmm.network_sample.core.utils.createFakeChildStack
import com.kmm.network_sample.root.ui.RootComponent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootUi(
    component: RootComponent,
    modifier: Modifier = Modifier
        .background(MaterialTheme.customColors.background.brand)
        .fillMaxSize()
) {
    Surface(modifier = modifier.systemBarsPadding()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Children(stack = component.childStack) {
                when (val child = it.instance) {
                    is RootComponent.Child.Main -> MainUi(component = child.component)
                    is RootComponent.Child.Pokemons -> PokemonsUi(component = child.component)
                }
            }

            MessageUi(
                component = component.messageComponent,
                bottomPadding = 16.dp
            )
        }
    }
}

@Preview
@Composable
fun RootUiPreview() {
    AppTheme {
        RootUi(FakeRootComponent())
    }
}

class FakeRootComponent : RootComponent {
    override val childStack: Value<ChildStack<*, RootComponent.Child>> = createFakeChildStack(
        RootComponent.Child.Main(FakeMainComponent())
    )

    override val messageComponent: MessageComponent = FakeMessageComponent()
}
