package com.kmm.network_sample.android.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCustomTypography provides AppTypography,
        LocalCustomColors provides LightAppColors,
        LocalDefaultButtonColors provides ButtonColors
    ) {
        MaterialTheme(
            colors = LightAppColors.toMaterialColors(),
            typography = AppTypography.toMaterialTypography(),
            shapes = Shapes,
            content = content
        )
    }
}
