package com.kmm.network_sample.pokemons.ui.details

import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.pokemons.domain.DetailedPokemon

interface PokemonDetailsComponent {

    val pokemonState: Value<List<DetailedPokemon>>
}