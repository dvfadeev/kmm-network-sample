package com.kmm.network_sample

import com.kmm.network_sample.di.allModules
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initAndroidShared(
    appDeclaration: KoinApplication.() -> KoinApplication,
    onKoinReady: (Koin) -> Unit
) {
    val koinApp = startKoin {
        appDeclaration.invoke(this)
        modules(allModules)
    }
    onKoinReady(koinApp.koin)
}

fun initIOSShared(
    onKoinReady: (Koin) -> Unit
) {
    val koinApp = startKoin {
        modules(allModules)
    }
    onKoinReady(koinApp.koin)
}
