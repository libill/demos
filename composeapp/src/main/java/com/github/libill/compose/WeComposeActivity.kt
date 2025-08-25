package com.github.libill.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.libill.compose.ui.theme.WeComposeTheme
import com.github.libill.compose.ui.view.CalendarPage
import com.github.libill.compose.ui.view.MainScreen
import com.github.libill.compose.ui.view.NestedSlidingListSample

class WeComposeActivity : ComponentActivity() {
    private val viewModel: WeComposeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeComposeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {
                    composable("main") {
                        MainScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                    composable("calendar") {
                        CalendarPage(
                            onBackPressed = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable("NestedSlidingListSample") {
                        NestedSlidingListSample()
                    }
                }
            }
        }
    }
}
