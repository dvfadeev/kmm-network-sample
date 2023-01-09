package com.kmm.network_sample.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.android.R
import com.kmm.network_sample.android.theme.AppTheme
import com.kmm.network_sample.android.widgets.Toolbar
import com.kmm.network_sample.core.utils.createFakeChildStack
import com.kmm.network_sample.pokemons.ui.PokemonsComponent

@Composable
fun PokemonsUi(
    component: PokemonsComponent,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { Toolbar(title = stringResource(R.string.pokemons_header)) },
        content = {
            PokemonsContent(component = component)
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun PokemonsContent(component: PokemonsComponent) {
    Box(modifier = Modifier.fillMaxSize()) {
        Children(stack = component.childStack) {
            when (val child = it.instance) {
                is PokemonsComponent.Child.List -> PokemonListUi(component = child.component)
                is PokemonsComponent.Child.Details -> PokemonDetailsUi(component = child.component)
            }
        }
    }
}

@Preview
@Composable
fun PokemonsUiPreview() {
    AppTheme {
        PokemonsUi(FakePokemonsComponent())
    }
}

class FakePokemonsComponent : PokemonsComponent {

    override val childStack: Value<ChildStack<*, PokemonsComponent.Child>> = createFakeChildStack(
        PokemonsComponent.Child.List(FakePokemonListComponent())
    )
}
