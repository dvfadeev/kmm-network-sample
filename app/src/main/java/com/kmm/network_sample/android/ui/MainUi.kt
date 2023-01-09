package com.kmm.network_sample.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmm.network_sample.android.R
import com.kmm.network_sample.android.theme.AppTheme
import com.kmm.network_sample.android.theme.customColors
import com.kmm.network_sample.android.theme.customTypography
import com.kmm.network_sample.android.theme.defaultButtonColors
import com.kmm.network_sample.android.widgets.Toolbar
import com.kmm.network_sample.main.ui.MainComponent

@Composable
fun MainUi(
    component: MainComponent,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { Toolbar(title = stringResource(R.string.app_name)) },
        content = {
            MainContent(
                onKtorClick = component::onKtorClick
            )
        },
        modifier = modifier
    )
}

@Composable
private fun MainContent(
    onKtorClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp)
    ) {
        MenuButton(text = stringResource(id = R.string.menu_ktor), onClick = onKtorClick)
    }
}

@Composable
private fun MenuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = MaterialTheme.defaultButtonColors,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.customTypography.button.MediumNormal,
            color = MaterialTheme.customColors.button.primary
        )
    }
}

@Preview
@Composable
fun MainUiPreview() {
    AppTheme {
        MainUi(FakeMainComponent())
    }
}

class FakeMainComponent : MainComponent {

    override fun onKtorClick() = Unit
}
