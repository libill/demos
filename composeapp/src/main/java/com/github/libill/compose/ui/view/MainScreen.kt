package com.github.libill.compose.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.libill.compose.WeComposeViewModel
import com.github.libill.compose.ui.collapsable.TestCollapsable
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: WeComposeViewModel
) {
    Column(Modifier.statusBarsPadding()) {
        val pageState = rememberPagerState { 4 }
        HorizontalPager(pageState, Modifier.weight(1f)) { page ->
            when (page) {
                0 -> ChatPage(viewModel.chats)
                1 -> ContactsPage()
//                2 -> DiscoverPage()
                2 -> TestCollapsable()
                3 -> MePage(
                    onNavigateToCalendar = {
                        navController.navigate("calendar")
                    }
                )
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