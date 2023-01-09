package com.kmm.network_sample.pokemons.data

import com.kmm.network_sample.core.parseId
import com.kmm.network_sample.pokemons.domain.Pokemon
import kotlinx.serialization.Serializable

@Serializable
class PokemonResponse(
    val name: String,
    val url: String
)

fun PokemonResponse.toDomain() = Pokemon(
    id = parseId(url),
    name = name
)
