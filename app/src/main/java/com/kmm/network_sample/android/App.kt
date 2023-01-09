package com.kmm.network_sample.android

import android.app.Application
import com.kmm.network_sample.core.error_handling.ErrorMessageFactory
import com.kmm.network_sample.di.ComponentFactory
import com.kmm.network_sample.initAndroidShared
import org.koin.core.Koin
import org.koin.dsl.module

class App : Application(), KoinProvider {

    override lateinit var koin: Koin
        private set

    override fun onCreate() {
        super.onCreate()

        initAndroidShared(
            appDeclaration = {
                modules(
                    module {
                        single <ErrorMessageFactory> { ErrorMessageFactory(applicationContext) }
                    }
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
