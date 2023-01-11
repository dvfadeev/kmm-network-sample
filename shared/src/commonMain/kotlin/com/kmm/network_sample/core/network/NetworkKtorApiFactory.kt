package com.kmm.network_sample.core.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://pokeapi.co/"

class NetworkKtorApiFactory {

    fun createUnauthorizedApi() = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(createJson())
        }
        defaultRequest {
            url(BASE_URL)
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

    fun createAuthorizedApi() = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(Auth) {
            basic {
                // Configure basic authentication
                credentials {
                    BasicAuthCredentials(username = "jetbrains", password = "foobar")
                    // or use digest auth
                    // DigestAuthCredentials(username = "jetbrains", password = "foobar")
                }
                realm = "Access to the '/' path"
            }
            bearer {
                // Configure bearer authentication
                loadTokens {
                    // Load tokens from a local storage and return them as the 'BearerTokens' instance
                    BearerTokens("abc123", "xyz111")
                }
            }
        }
        //TODO: Setup HTTP Client
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun createJson(): Json {
        return Json {
            explicitNulls = false
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}
