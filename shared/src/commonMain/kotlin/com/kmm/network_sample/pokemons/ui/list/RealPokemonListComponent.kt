package com.kmm.network_sample.pokemons.ui.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.kmm.network_sample.core.error_handling.ErrorHandler
import com.kmm.network_sample.core.error_handling.safeLaunch
import com.kmm.network_sample.core.utils.componentCoroutineScope
import com.kmm.network_sample.pokemons.data.LoadingType
import com.kmm.network_sample.pokemons.data.PokemonKtorApi
import com.kmm.network_sample.pokemons.data.toDomain
import com.kmm.network_sample.pokemons.domain.Pokemon
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RealPokemonListComponent(
    componentContext: ComponentContext,
    private val loadingType: LoadingType,
    private val onOutput: (PokemonListComponent.Output) -> Unit,
    private val pokemonKtorApi: PokemonKtorApi,
    private val errorHandler: ErrorHandler
) : ComponentContext by componentContext, PokemonListComponent {

    private val scope = componentCoroutineScope()

    override val isRefreshingState: MutableValue<Boolean> = MutableValue(true)

    override val pokemonListState: MutableValue<List<Pokemon>> = MutableValue(listOf())

    init {
        onRefreshClick()
    }

    override fun onRefreshClick() {
        isRefreshingState.value = true
        scope.launch {
            safeLaunch(errorHandler) {
                pokemonListState.value = when (loadingType) {
                    // Use Ktor API
                    LoadingType.KTOR -> {
                        pokemonKtorApi.getPokemonList().results.map { it.toDomain() }
                    }
                }
                isRefreshingState.value = false
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
