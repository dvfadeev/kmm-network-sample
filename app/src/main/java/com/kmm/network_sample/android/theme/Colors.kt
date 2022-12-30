package com.kmm.network_sample.android.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LightAppColors = CustomColors(
    isLight = true,
    text = CustomColors.TextColors(),
    icon = CustomColors.IconColors(),
    background = CustomColors.BackgroundColors(),
    button = CustomColors.ButtonColors()
)

object RawColors {
    val blackA: Color = Color(0xFF000000)
    val grayA: Color = Color(0xFF455A64)
    val grayB: Color = Color(0xFF718792)
    val grayC: Color = Color(0xFF1C313A)
    val grayD: Color = Color(0xFFEBEBEB)
    val grayE: Color = Color(0xFF9E9E9E)
    val whiteA: Color = Color(0xFFFFFFFF)
}

@Immutable
data class CustomColors(
    val isLight: Boolean,
    val text: TextColors,
    val icon: IconColors,
    val background: BackgroundColors,
    val button: ButtonColors
) {
    data class TextColors(
        val primary: Color = RawColors.blackA,
        val caption: Color = RawColors.grayE,
        val brand: Color = RawColors.grayA,
        val invert: Color = RawColors.whiteA
    )

    data class IconColors(
        val primary: Color = RawColors.blackA,
        val secondary: Color = RawColors.grayB,
        val brand: Color = RawColors.grayA,
        val invert: Color = RawColors.whiteA
    )

    data class BackgroundColors(
        val primary: Color = RawColors.whiteA,
        val secondary: Color = RawColors.grayD,
        val brand: Color = RawColors.grayA,
        val separator: Color = RawColors.grayE
    )

    data class ButtonColors(
        val primary: Color = RawColors.blackA,
        val brand: StateColors = StateColors(
            default = RawColors.blackA,
            pressed = RawColors.grayB
        )
    )

    data class StateColors(
        val default: Color,
        val pressed: Color?
    )
}

val LocalCustomColors = staticCompositionLocalOf<CustomColors> {
    error("CustomColors are not provided. Did you forget to apply CustomTheme?")
}

val MaterialTheme.customColors: CustomColors
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColors.current

fun CustomColors.toMaterialColors(): Colors {
    return Colors(
        primary = button.brand.default,
        primaryVariant = button.brand.default,
        secondary = button.brand.default,
        secondaryVariant = button.brand.default,
        background = background.primary,
        surface = background.primary,
        onPrimary = text.invert,
        onSecondary = text.invert,
        onBackground = text.primary,
        onSurface = text.primary,
        onError = text.invert,
        error = text.caption,
        isLight = isLight
    )
}
