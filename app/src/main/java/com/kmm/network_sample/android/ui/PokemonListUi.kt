package com.kmm.network_sample.android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.android.theme.AppTheme
import com.kmm.network_sample.android.theme.customColors
import com.kmm.network_sample.android.theme.customTypography
import com.kmm.network_sample.pokemons.domain.Pokemon
import com.kmm.network_sample.pokemons.ui.list.PokemonListComponent

@Composable
fun PokemonListUi(
    component: PokemonListComponent,
    modifier: Modifier = Modifier
) {
    val pokemonList by component.pokemonListState.subscribeAsState()
    PokemonListContent(
        pokemonList = pokemonList,
        onPokemonClick = component::onPokemonClick,
        modifier
    )
}

@Composable
private fun PokemonListContent(
    pokemonList: List<Pokemon>,
    onPokemonClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = pokemonList, key = { it.id }) {
            PokemonData(
                pokemon = it,
                onClick = { onPokemonClick(it) }
            )
        }
    }
}

@Composable
private fun PokemonData(
    pokemon: Pokemon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = pokemon.name,
        style = MaterialTheme.customTypography.body.LightNormal,
        color = MaterialTheme.customColors.text.primary,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    )
}

@Preview
@Composable
fun PokemonListUiPreview() {
    AppTheme {
        PokemonListUi(FakePokemonListComponent())
    }
}

class FakePokemonListComponent : PokemonListComponent {

    override val pokemonListState: Value<List<Pokemon>> = MutableValue(listOf())

    override fun onPokemonClick(pokemon: Pokemon) = Unit
}
