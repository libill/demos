package com.github.libill.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.github.libill.compose.ui.theme.WeComposeTheme
import com.github.libill.compose.ui.view.ChatList
import com.github.libill.compose.ui.view.WeNavigationBar
import kotlinx.coroutines.launch

class WeComposeActivity : ComponentActivity() {
    private val viewModel: WeComposeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeComposeTheme {
                Column(Modifier.statusBarsPadding()) {
                    val pageState = rememberPagerState { 4 }
                    HorizontalPager(pageState, Modifier.weight(1f)) { page ->
                        when (page) {
                            0 -> ChatList(viewModel.chats)
                            1 -> Text("2", Modifier.fillMaxSize())
                            2 -> Text("3", Modifier.fillMaxSize())
                            3 -> Text("4", Modifier.fillMaxSize())
//                            1 -> ContactList(viewModel)
//                            2 -> DiscoverList(viewModel)
//                            3 -> MeList(viewModel)
                        }
                    }
                    val scope = rememberCoroutineScope()
                    WeNavigationBar(pageState.currentPage) {
                        scope.launch {
                            pageState.animateScrollToPage(it)
                        }
                    }
                }
            }
        }
    }
}
