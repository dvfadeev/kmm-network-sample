package com.kmm.network_sample.main.ui

import com.kmm.network_sample.pokemons.data.LoadingType

interface MainComponent {

    fun onKtorClick()

    fun onKtorfitClick()

    sealed interface Output {
        data class RequestPokemonList(val loadingType: LoadingType) : Output
    }
}
