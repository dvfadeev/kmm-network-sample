package com.kmm.network_sample.root

import com.arkivanov.decompose.ComponentContext
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.root.ui.RealRootComponent
import com.kmm.network_sample.root.ui.RootComponent
import org.koin.core.component.get


fun ComponentFactory.createRootComponent(
    componentContext: ComponentContext
): RootComponent {
    return RealRootComponent(componentContext, get())
}
