package com.kmm.network_sample.pokemons.ui.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.kmm.network_sample.core.error_handling.ErrorHandler
import com.kmm.network_sample.core.error_handling.safeLaunch
import com.kmm.network_sample.core.utils.componentCoroutineScope
import com.kmm.network_sample.pokemons.data.PokemonsApi
import com.kmm.network_sample.pokemons.data.toDomain
import com.kmm.network_sample.pokemons.domain.Pokemon
import com.kmm.network_sample.pokemons.data.LoadingType
import kotlinx.coroutines.launch

class RealPokemonListComponent(
    componentContext: ComponentContext,
    private val loadingType: LoadingType,
    private val onOutput: (PokemonListComponent.Output) -> Unit,
    pokemonsApi: PokemonsApi,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, PokemonListComponent {

    private val scope = componentCoroutineScope()

    override val pokemonListState: MutableValue<List<Pokemon>> = MutableValue(listOf())

    init {
        scope.launch {
            safeLaunch(errorHandler) {
                pokemonListState.value = pokemonsApi.getPokemonList().results.map { it.toDomain() }
            }
        }
    }

    override fun onPokemonClick(pokemon: Pokemon) {
        onOutput(
            PokemonListComponent.Output.RequestPokemonDetails(
                pokemon,
                loadingType
            )
        )
    }
}
