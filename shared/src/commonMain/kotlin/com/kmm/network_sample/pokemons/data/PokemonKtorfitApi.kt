package com.kmm.network_sample.pokemons.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

interface PokemonKtorfitApi {

    @GET("api/v2/pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int = 100): PokemonListResponse

    @GET("api/v2/pokemon/{pokemonId}")
    suspend fun getPokemonById(@Path("pokemonId") id: String): DetailedPokemonResponse
}
