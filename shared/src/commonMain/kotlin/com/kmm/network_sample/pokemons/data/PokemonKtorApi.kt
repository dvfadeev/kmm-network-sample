package com.kmm.network_sample.pokemons.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class PokemonKtorApi(private val client: HttpClient) {

    suspend fun getPokemonList(limit: Int = 100): PokemonListResponse = client.get("api/v2/pokemon") {
        url {
            parameters.append("limit", limit.toString())
        }
    }.body()

    suspend fun getPokemonById(id: String): DetailedPokemonResponse = client.get("api/v2/pokemon") {
        url {
            appendPathSegments(id)
        }
    }.body()
}
