package com.kmm.network_sample.pokemons.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.pokemons.ui.details.PokemonDetailsComponent
import com.kmm.network_sample.pokemons.ui.list.PokemonListComponent

interface PokemonsComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {

        class List(val component: PokemonListComponent) : Child

        class Details(val component: PokemonDetailsComponent) : Child
    }
}
