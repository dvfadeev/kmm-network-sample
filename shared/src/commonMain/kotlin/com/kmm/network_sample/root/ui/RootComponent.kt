package com.kmm.network_sample.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.core.message.ui.MessageComponent
import com.kmm.network_sample.main.ui.MainComponent
import com.kmm.network_sample.pokemons.ui.PokemonsComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    val messageComponent: MessageComponent

    sealed interface Child {

        class Main(val component: MainComponent) : Child

        class Pokemons(val component: PokemonsComponent) : Child
    }
}
