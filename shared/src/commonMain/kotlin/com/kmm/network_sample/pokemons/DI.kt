package com.kmm.network_sample.pokemons

import com.arkivanov.decompose.ComponentContext
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.pokemons.domain.Pokemon
import com.kmm.network_sample.pokemons.data.LoadingType
import com.kmm.network_sample.pokemons.ui.PokemonsComponent
import com.kmm.network_sample.pokemons.ui.RealPokemonsComponent
import com.kmm.network_sample.pokemons.ui.details.PokemonDetailsComponent
import com.kmm.network_sample.pokemons.ui.details.RealPokemonDetailsComponent
import com.kmm.network_sample.pokemons.ui.list.PokemonListComponent
import com.kmm.network_sample.pokemons.ui.list.RealPokemonListComponent
import org.koin.core.component.get

fun ComponentFactory.createPokemonsComponent(
    componentContext: ComponentContext,
    loadingType: LoadingType
): PokemonsComponent {
    return RealPokemonsComponent(componentContext, this, loadingType)
}

fun ComponentFactory.createPokemonListComponent(
    componentContext: ComponentContext,
    loadingType: LoadingType,
    onOutput: (PokemonListComponent.Output) -> Unit
): PokemonListComponent {
    return RealPokemonListComponent(componentContext, loadingType, onOutput, get(), get())
}

fun ComponentFactory.createPokemonDetailsComponent(
    componentContext: ComponentContext,
    pokemon: Pokemon,
    loadingType: LoadingType
): PokemonDetailsComponent {
    return RealPokemonDetailsComponent(componentContext, pokemon, loadingType, get(), get())
}
