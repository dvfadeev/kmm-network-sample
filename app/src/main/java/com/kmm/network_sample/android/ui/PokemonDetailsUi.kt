package com.kmm.network_sample.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.kmm.network_sample.pokemons.domain.DetailedPokemon
import com.kmm.network_sample.pokemons.ui.details.PokemonDetailsComponent

@Composable
fun PokemonDetailsUi(
    component: PokemonDetailsComponent,
    modifier: Modifier = Modifier
) {
    val pokemons by component.pokemonState.subscribeAsState()

    if(pokemons.isNotEmpty()) {
        PokemonDetailsContent(
            pokemon = pokemons.first(),
            modifier = modifier
        )
    }
}

@Composable
private fun PokemonDetailsContent(
    pokemon: DetailedPokemon,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = pokemon.name,
            style = MaterialTheme.customTypography.title.boldExtra,
            color = MaterialTheme.customColors.text.primary
        )
    }
}

@Preview
@Composable
fun PokemonDetailsUiPreview() {
    AppTheme {
        PokemonDetailsUi(FakePokemonDetailsComponent())
    }
}

class FakePokemonDetailsComponent : PokemonDetailsComponent {

    override val pokemonState: Value<List<DetailedPokemon>> = MutableValue(listOf())
}
