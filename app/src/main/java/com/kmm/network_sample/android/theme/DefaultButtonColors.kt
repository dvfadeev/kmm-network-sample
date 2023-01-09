package com.kmm.network_sample.android.theme

import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

val ButtonColors = DefaultButtonColors(
    backgroundColor = LightAppColors.button.brand.default,
    contentColor = LightAppColors.text.invert,
    disabledBackgroundColor = LightAppColors.button.brand.disabled,
    disabledContentColor = LightAppColors.text.disabled
)

val TextButtonColors = DefaultButtonColors(
    backgroundColor = LightAppColors.button.invert.default,
    contentColor = LightAppColors.text.primary,
    disabledBackgroundColor = LightAppColors.button.invert.disabled,
    disabledContentColor = LightAppColors.text.disabled
)

class DefaultButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color
) : ButtonColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }
}

val LocalDefaultButtonColors = staticCompositionLocalOf<DefaultButtonColors> {
    error("DefaultButtonColors are not provided. Did you forget to apply CustomTheme?")
}

val MaterialTheme.defaultButtonColors: DefaultButtonColors
    @Composable
    @ReadOnlyComposable
    get() = LocalDefaultButtonColors.current
