package com.kmm.network_sample.main

import com.arkivanov.decompose.ComponentContext
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.main.ui.MainComponent
import com.kmm.network_sample.main.ui.RealMainComponent

fun ComponentFactory.createMainComponent(
    componentContext: ComponentContext,
    onOutput: (MainComponent.Output) -> Unit
): MainComponent {
    return RealMainComponent(componentContext, onOutput)
}
