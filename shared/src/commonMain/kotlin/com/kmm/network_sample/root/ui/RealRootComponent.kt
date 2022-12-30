package com.kmm.network_sample.root.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.main.createMainComponent

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    companion object {
        private const val CHILD_STACK_KEY = "childStack"
    }

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Main,
        handleBackButton = true,
        key = CHILD_STACK_KEY,
        childFactory = ::createChild
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        is ChildConfig.Main -> RootComponent.Child.Main(
            componentFactory.createMainComponent(
                componentContext
            )
        )
    }

    private sealed class ChildConfig : Parcelable {
        @Parcelize
        object Main : ChildConfig()
    }
}
