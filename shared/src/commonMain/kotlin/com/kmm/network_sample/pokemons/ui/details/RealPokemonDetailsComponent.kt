package com.kmm.network_sample.pokemons.ui.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.kmm.network_sample.core.utils.componentCoroutineScope
import com.kmm.network_sample.pokemons.data.LoadingType
import com.kmm.network_sample.pokemons.data.PokemonsApi
import com.kmm.network_sample.pokemons.data.toDomain
import com.kmm.network_sample.pokemons.domain.DetailedPokemon
import com.kmm.network_sample.pokemons.domain.Pokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RealPokemonDetailsComponent(
    componentContext: ComponentContext,
    pokemon: Pokemon,
    loadingType: LoadingType,
    pokemonsApi: PokemonsApi
) : ComponentContext by componentContext, PokemonDetailsComponent {

    private val scope = componentCoroutineScope()

    override var pokemonState: MutableValue<List<DetailedPokemon>> = MutableValue(listOf())

    init {
        scope.launch {
            val detailedPokemon = pokemonsApi.getPokemonById(pokemon.id)
            pokemonState.value = listOf(detailedPokemon.toDomain())
        }
    }
}
