package com.kmm.network_sample.android

import android.app.Application
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.initAndroidShared
import org.koin.core.Koin

class App : Application(), KoinProvider {

    override lateinit var koin: Koin
        private set

    override fun onCreate() {
        super.onCreate()

        initAndroidShared(
            appDeclaration = {
                modules(
                    // TODO
                )
            },
            onKoinReady = { koin ->
                this.koin = koin
                koin.apply {
                    declare(ComponentFactory(this))
                }
            }
        )
    }
}
