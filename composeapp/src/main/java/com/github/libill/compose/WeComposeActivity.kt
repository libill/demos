package com.github.libill.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.libill.compose.ui.theme.WeComposeTheme
import com.github.libill.compose.ui.view.WeNavigationBar
import androidx.lifecycle.viewmodel.compose.viewModel

class WeComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: WeComposeViewModel = viewModel()
            WeComposeTheme {
                WeNavigationBar(viewModel.selectedTab) {
                    viewModel.selectedTab = it
                }
            }
        }
    }
}
