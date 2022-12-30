package com.kmm.network_sample.android.utils

import androidx.compose.runtime.*
import com.arkivanov.decompose.value.Value
import com.kmm.network_sample.core.ui.Optional

@Composable
fun <T : Any, F : Any> Value<Optional<T>>.subscribeAsStateOptional(transform: (T?) -> F?): State<F?> {
    val state = remember(this) { mutableStateOf(transform(value.value)) }
    DisposableEffect(this) {
        val observer: (Optional<T>) -> Unit = {
            state.value = transform(it.value)
        }
        subscribe(observer)
        onDispose {
            unsubscribe(observer)
        }
    }
    return state
}
