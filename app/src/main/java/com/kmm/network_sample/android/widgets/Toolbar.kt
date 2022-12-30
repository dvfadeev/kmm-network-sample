package com.kmm.network_sample.android.widgets

import androidx.annotation.DrawableRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kmm.network_sample.android.theme.customColors
import com.kmm.network_sample.android.utils.dispatchOnBackPressed

@Composable
fun Toolbar(
    title: String?,
    navigationIcon: @Composable (() -> Unit)? = { BackButton() },
    actionIcon: @Composable (() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.customColors.background.brand,
    contentColor: Color = MaterialTheme.customColors.text.invert,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = { title?.let { Text(text = it) } },
        navigationIcon = navigationIcon,
        actions = { actionIcon?.invoke() },
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = 0.dp
    )
}

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    NavigationButton(iconRes = androidx.appcompat.R.drawable.abc_ic_ab_back_material, onClick, modifier)
}

@Composable
fun NavigationButton(
    @DrawableRes iconRes: Int,
    onClick: () -> Unit = { },
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    IconButton(onClick = {
        onClick()
        dispatchOnBackPressed(context)
    }, modifier = modifier) {
        Icon(
            painterResource(iconRes),
            contentDescription = null
        )
    }
}
