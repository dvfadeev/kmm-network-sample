package com.kmm.network_sample.android.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun dispatchOnBackPressed(context: Context) {
    val activity = context.getActivity() ?: return
    activity.onBackPressed()
}
