package com.kmm.network_sample.main.ui

import com.arkivanov.decompose.ComponentContext
import com.kmm.network_sample.pokemons.data.LoadingType

class RealMainComponent(
    componentContext: ComponentContext,
    private val onOutput: (MainComponent.Output) -> Unit
) : ComponentContext by componentContext, MainComponent {

    override fun onKtorClick() {
        onOutput(MainComponent.Output.RequestPokemonList(LoadingType.KTOR))
    }

    override fun onKtorfitClick() {
        onOutput(MainComponent.Output.RequestPokemonList(LoadingType.KTORFIT))
    }
}
