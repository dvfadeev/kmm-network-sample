package com.kmm.network_sample.pokemons.ui.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.kmm.network_sample.core.error_handling.ErrorHandler
import com.kmm.network_sample.core.error_handling.safeLaunch
import com.kmm.network_sample.core.utils.componentCoroutineScope
import com.kmm.network_sample.pokemons.data.LoadingType
import com.kmm.network_sample.pokemons.data.PokemonKtorApi
import com.kmm.network_sample.pokemons.data.toDomain
import com.kmm.network_sample.pokemons.domain.DetailedPokemon
import com.kmm.network_sample.pokemons.domain.Pokemon
import kotlinx.coroutines.launch

class RealPokemonDetailsComponent(
    componentContext: ComponentContext,
    pokemon: Pokemon,
    loadingType: LoadingType,
    pokemonKtorApi: PokemonKtorApi,
    errorHandler: ErrorHandler
) : ComponentContext by componentContext, PokemonDetailsComponent {

    private val scope = componentCoroutineScope()

    override val isRefreshing: MutableValue<Boolean> = MutableValue(true)

    override var pokemonState: MutableValue<List<DetailedPokemon>> = MutableValue(listOf())

    init {
        scope.launch {
            safeLaunch(errorHandler) {
                pokemonState.value = listOf(
                    when (loadingType) {
                        // Use Ktor API
                        LoadingType.KTOR -> {
                            pokemonKtorApi.getPokemonById(pokemon.id).toDomain()
                        }
                    }
                )
                isRefreshing.value = false
            }
        }
    }
}
