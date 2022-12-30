package com.kmm.network_sample.android.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val AppTypography: CustomTypography = CustomTypography(
    title = CustomTypography.Title(),
    body = CustomTypography.Body(),
    button = CustomTypography.Button(),
    caption = CustomTypography.Caption()
)

@Immutable
data class CustomTypography(
    val title: Title,
    val body: Body,
    val button: Button,
    val caption: Caption
) {
    data class Title(
        val boldExtra: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            lineHeight = 42.sp,
            letterSpacing = 0.004.em
        ),
        val mediumLarge: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            lineHeight = 26.sp,
            letterSpacing = (-0.0026).em
        ),
        val regularSystem: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 21.sp,
            letterSpacing = (-0.0043).em
        )
    )

    data class Body(
        val LightNormal: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            lineHeight = 21.sp,
            letterSpacing = (-0.0023).em
        )
    )

    data class Button(
        val MediumNormal: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 21.sp,
            letterSpacing = (-0.0023).em
        )
    )

    data class Caption(
        val regularLarge: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = (-0.0023).em
        ),
        val regularNormal: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.0023).em
        ),
        val mediumNormal: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.0023).em
        ),
        val regularSmall: TextStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            letterSpacing = (-0.0108).em
        )
    )
}

fun CustomTypography.toMaterialTypography(): Typography {
    return Typography(
        h1 = title.boldExtra,
        h2 = title.mediumLarge,
        h3 = title.mediumLarge,
        h4 = title.regularSystem,
        subtitle1 = caption.regularLarge,
        subtitle2 = caption.regularNormal,
        body1 = caption.mediumNormal,
        body2 = body.LightNormal,
        button = button.MediumNormal,
        caption = caption.regularSmall,
    )
}

val LocalCustomTypography = staticCompositionLocalOf<CustomTypography> {
    error("CustomTypography are not provided. Did you forget to apply CustomTheme?")
}

val MaterialTheme.customTypography: CustomTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomTypography.current
