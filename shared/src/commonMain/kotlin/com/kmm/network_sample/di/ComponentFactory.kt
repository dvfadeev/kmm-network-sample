package com.kmm.network_sample.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

/**
 * Фабрика для создания Decompose компонента. Используем ее в функциях расширениях.
 */
class ComponentFactory(private val localKoin: Koin) : KoinComponent {

    override fun getKoin(): Koin = localKoin
}
