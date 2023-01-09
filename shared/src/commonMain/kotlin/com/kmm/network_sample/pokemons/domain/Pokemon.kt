package com.kmm.network_sample.pokemons.domain

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class Pokemon(
    val id: String,
    val name: String
) : Parcelable
