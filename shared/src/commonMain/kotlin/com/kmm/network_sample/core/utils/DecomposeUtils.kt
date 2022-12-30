package com.kmm.network_sample.core.utils

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.kmm.network_sample.core.ui.Optional
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

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

fun <T : Any> Flow<T?>.asValue(scope: CoroutineScope): Value<Optional<T>> {
    val mutableValue = MutableValue(Optional<T>(null))
    scope.launch(Dispatchers.Main) {
        this@asValue
            .collect {
                mutableValue.value = Optional(it)
            }
    }
    return mutableValue
}
