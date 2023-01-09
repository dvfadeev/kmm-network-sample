package com.kmm.network_sample.pokemons.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.pokemons.domain.Pokemon
import com.kmm.network_sample.pokemons.createPokemonDetailsComponent
import com.kmm.network_sample.pokemons.createPokemonListComponent
import com.kmm.network_sample.pokemons.data.LoadingType
import com.kmm.network_sample.pokemons.ui.list.PokemonListComponent

class RealPokemonsComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory,
    private val loadingType: LoadingType,
) : ComponentContext by componentContext, PokemonsComponent {

    companion object {
        private const val CHILD_STACK_KEY = "pokemonsChildStack"
    }

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: Value<ChildStack<*, PokemonsComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.List(loadingType),
        handleBackButton = true,
        key = CHILD_STACK_KEY,
        childFactory = ::createChild
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): PokemonsComponent.Child = when (config) {
        is ChildConfig.List -> PokemonsComponent.Child.List(
            componentFactory.createPokemonListComponent(
                componentContext,
                loadingType,
                ::onPokemonListOutput
            )
        )
        is ChildConfig.Details -> PokemonsComponent.Child.Details(
            componentFactory.createPokemonDetailsComponent(
                componentContext,
                config.pokemon,
                config.loadingType
            )
        )
    }

    private fun onPokemonListOutput(output: PokemonListComponent.Output) {
        when (output) {
            is PokemonListComponent.Output.RequestPokemonDetails -> {
                navigation.push(ChildConfig.Details(output.pokemon, output.loadingType))
            }
        }
    }

    private sealed class ChildConfig : Parcelable {

        @Parcelize
        data class List(val loadingType: LoadingType) : ChildConfig()

        @Parcelize
        data class Details(val pokemon: Pokemon, val loadingType: LoadingType) : ChildConfig()
    }
}
