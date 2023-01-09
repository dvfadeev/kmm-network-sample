package com.kmm.network_sample.pokemons.data

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonResponse>
)
