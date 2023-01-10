package com.kmm.network_sample.core

import com.arkivanov.decompose.ComponentContext
import com.kmm.network_sample.core.error_handling.ErrorHandler
import com.kmm.network_sample.core.message.data.MessageService
import com.kmm.network_sample.core.message.data.MessageServiceImpl
import com.kmm.network_sample.core.message.ui.MessageComponent
import com.kmm.network_sample.core.message.ui.RealMessageComponent
import com.kmm.network_sample.core.network.NetworkApiFactory
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.pokemons.data.PokemonKtorApi
import org.koin.core.component.get
import org.koin.dsl.module

val coreModule = module {
    single<PokemonKtorApi> { PokemonKtorApi(NetworkApiFactory().createUnauthorizedApi()) }
    single<ErrorHandler> { ErrorHandler(get(), get()) }
    single<MessageService> { MessageServiceImpl() }
}

fun ComponentFactory.createMessageComponent(
    componentContext: ComponentContext
): MessageComponent {
    return RealMessageComponent(componentContext, get())
}
