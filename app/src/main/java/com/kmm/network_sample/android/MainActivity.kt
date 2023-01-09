package com.kmm.network_sample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.kmm.network_sample.android.theme.AppTheme
import com.kmm.network_sample.android.ui.RootUi
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.root.createRootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentFactory = application.koin.get<ComponentFactory>()
        val rootComponent = componentFactory.createRootComponent(defaultComponentContext())

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppTheme {
                RootUi(component = rootComponent)
            }
        }
    }
}
