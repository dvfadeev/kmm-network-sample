package com.kmm.network_sample.main.ui

import com.arkivanov.decompose.ComponentContext

class RealMainComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, MainComponent
