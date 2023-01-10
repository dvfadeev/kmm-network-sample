package com.kmm.network_sample.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kmm.network_sample.android.R
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
    val isRefreshing by component.isRefreshing.subscribeAsState()
    val pokemon by component.pokemonState.subscribeAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { },
        modifier = modifier.fillMaxSize()
    ) {
        if (pokemon.isNotEmpty()) {
            PokemonDetailsContent(
                pokemon = pokemon.first()
            )
        }
    }
}

@Composable
private fun PokemonDetailsContent(
    pokemon: DetailedPokemon,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp)
    ) {
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = pokemon.name,
            style = MaterialTheme.customTypography.title.boldExtra,
            color = MaterialTheme.customColors.text.primary
        )
        Text(
            text = stringResource(R.string.pokemon_height, pokemon.height),
            style = MaterialTheme.customTypography.body.LightNormal,
            color = MaterialTheme.customColors.text.primary
        )
        Text(
            text = stringResource(R.string.pokemon_weight, pokemon.weight),
            style = MaterialTheme.customTypography.body.LightNormal,
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

    override val isRefreshing: Value<Boolean> = MutableValue(false)

    override val pokemonState: Value<List<DetailedPokemon>> = MutableValue(listOf())
}
