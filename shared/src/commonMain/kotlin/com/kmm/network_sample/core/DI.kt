package com.kmm.network_sample.core

import com.arkivanov.decompose.ComponentContext
import com.kmm.network_sample.core.error_handling.ErrorHandler
import com.kmm.network_sample.core.message.data.MessageService
import com.kmm.network_sample.core.message.data.MessageServiceImpl
import com.kmm.network_sample.core.message.ui.MessageComponent
import com.kmm.network_sample.core.message.ui.RealMessageComponent
import com.kmm.network_sample.core.network.NetworkKtorApiFactory
import com.kmm.network_sample.core.network.NetworkKtorfitApiFactory
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.pokemons.data.PokemonKtorApi
import com.kmm.network_sample.pokemons.data.PokemonKtorfitApi
import de.jensklingenberg.ktorfit.create
import org.koin.core.component.get
import org.koin.dsl.module

val coreModule = module {
    single { PokemonKtorApi(NetworkKtorApiFactory().createUnauthorizedApi()) }
    single { NetworkKtorfitApiFactory().createUnauthorizedApi().create<PokemonKtorfitApi>() }
    single { ErrorHandler(get(), get()) }
    single<MessageService> { MessageServiceImpl() }
}

fun ComponentFactory.createMessageComponent(
    componentContext: ComponentContext
): MessageComponent {
    return RealMessageComponent(componentContext, get())
}
