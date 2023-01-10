package com.kmm.network_sample.core.utils

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * Создает фейковый [ChildStack] с одним активным компонентом. Используется для превью Compose.
 */
fun <T : Any> createFakeChildStack(instance: T): Value<ChildStack<*, T>> {
    return object : Value<ChildStack<*, T>>() {
        override val value: ChildStack<*, T>
            get() = ChildStack(
                configuration = "<fake>",
                instance = instance
            )

        override fun subscribe(observer: (ChildStack<*, T>) -> Unit) {
            // Nothing
        }

        override fun unsubscribe(observer: (ChildStack<*, T>) -> Unit) {
            // Nothing
        }
    }
}

/**
 *  Создает CoroutineScope привязанный к жизненному циклу компонента.
 */
fun LifecycleOwner.componentCoroutineScope(): CoroutineScope {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    if (lifecycle.state != Lifecycle.State.DESTROYED) {
        lifecycle.doOnDestroy {
            scope.cancel()
        }
    } else {
        scope.cancel()
    }

    return scope
}
