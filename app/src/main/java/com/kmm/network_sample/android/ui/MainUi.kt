package com.kmm.network_sample.android.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kmm.network_sample.android.R
import com.kmm.network_sample.android.theme.AppTheme
import com.kmm.network_sample.android.widgets.Toolbar
import com.kmm.network_sample.main.ui.MainComponent

@Composable
fun MainUi(
    component: MainComponent,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { Toolbar(title = stringResource(R.string.app_name)) },
        content = { MainContent() }
    )
}

@Composable
fun MainContent() {
    Text(text = "Main component")
}


@Preview
@Composable
fun AboutUiPreview() {
    AppTheme {
        MainUi(FakeMainComponent())
    }
}

class FakeMainComponent : MainComponent
