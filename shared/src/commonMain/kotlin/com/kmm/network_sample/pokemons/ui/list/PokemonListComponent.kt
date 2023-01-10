package com.kmm.network_sample.pokemons.ui.list

import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.pokemons.domain.Pokemon
import com.kmm.network_sample.pokemons.data.LoadingType

interface PokemonListComponent {

    val isRefreshingState: Value<Boolean>

    val pokemonListState: Value<List<Pokemon>>

    sealed interface Output {
        data class RequestPokemonDetails(val pokemon: Pokemon, val loadingType: LoadingType) : Output
    }

    fun onRefreshClick()

    fun onPokemonClick(pokemon: Pokemon)
}
