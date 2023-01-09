package com.kmm.network_sample.pokemons.data

import com.kmm.network_sample.core.getImageUrl
import com.kmm.network_sample.pokemons.domain.DetailedPokemon
import kotlinx.serialization.Serializable

@Serializable
class DetailedPokemonResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int
)

fun DetailedPokemonResponse.toDomain() = DetailedPokemon(
    id = id.toString(),
    name = name,
    height = height,
    weight = weight,
    imageUrl = getImageUrl(id.toString())
)
