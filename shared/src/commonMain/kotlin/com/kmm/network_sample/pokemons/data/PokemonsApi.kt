package com.kmm.network_sample.pokemons.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class PokemonsApi(private val client: HttpClient) {

    suspend fun getPokemonList(): PokemonListResponse = client.get("api/v2/pokemon").body()

    suspend fun getPokemonById(id: String): DetailedPokemonResponse = client.get("api/v2/pokemon") {
        url {
            appendPathSegments(id)
        }
    }.body()
}
