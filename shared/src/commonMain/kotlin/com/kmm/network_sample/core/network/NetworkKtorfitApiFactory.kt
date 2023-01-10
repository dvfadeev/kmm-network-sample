package com.kmm.network_sample.core.network

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://pokeapi.co/"

class NetworkKtorfitApiFactory {

    fun createUnauthorizedApi(): Ktorfit = Ktorfit.Builder()
        .baseUrl(BASE_URL)
        .httpClient(
            HttpClient {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                    })
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
        )
        .build()

    fun createAuthorizedApi() = Ktorfit.Builder()
        .baseUrl(BASE_URL)
        .httpClient(
            HttpClient {
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
        )
        .build()
}
