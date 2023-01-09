package com.kmm.network_sample.core.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class NetworkApiFactory {

    fun createApi(): HttpClient {
        return HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            defaultRequest {
                url("https://pokeapi.co/")
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    throw ErrorConverter.mapToFailureException(exception)
                }
                validateResponse { response ->
                    // You can validate successful response body here
                }
            }
        }
    }
}
