package com.kmm.network_sample.root.ui

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.main.ui.MainComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Main(val component: MainComponent) : Child
    }
}
