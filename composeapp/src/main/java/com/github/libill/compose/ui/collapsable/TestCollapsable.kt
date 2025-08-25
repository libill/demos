package com.github.libill.compose.ui.collapsable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.libill.compose.ui.view.calendar.CalendarDate
import com.github.libill.compose.ui.view.calendar.CalendarEvent
import com.github.libill.compose.ui.view.calendar.CalendarViewMode
import com.github.libill.compose.ui.view.calendar.MonthView
import com.github.libill.compose.ui.view.calendar.WeekHeader


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
            .systemBarsPadding() //同时添加状态栏和导航栏高度对应的上下 padding
//            .statusBarsPadding() //只添加状态栏
//            .navigationBarsPadding()//只添加导航啦
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            items(20) {
                Text(text = "第 $it 项")
            }
        }
    }
}


@Composable
fun TestCollapsable() {
    val listState = rememberLazyListState()
    val collapsableLayoutState = rememberCollapsableLayoutState(50.dp)
    val bottomContentScrolled: State<Boolean> = remember {
        derivedStateOf {
            !(listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0)
        }
    }
    collapsableLayoutState.currentTopState

    Column {
        WeekHeader()
        CoverLayout(
            topContent = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                    //.height(200.dp)
                    //.alpha(collapsableLayoutState.expendProgress)
                ) {
                    MonthViewItem()
                }
            },

            bottomContent = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding(),
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    items(30) {
                        Text(text = "第 $it 项")
                    }
                }
            },
            bottomContentScrolled = bottomContentScrolled,
            state = collapsableLayoutState
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TestPreview() {
    TestCollapsable()
}


@Composable
fun MonthViewItem() {
    var currentViewMode by remember { mutableStateOf(CalendarViewMode.MONTH) }
    var selectedDate by remember { mutableStateOf(CalendarDate.now()) }
    var currentDate by remember { mutableStateOf(CalendarDate.now()) }
    val events = remember {
        mutableStateListOf<CalendarEvent>().apply {
            // Add events for multiple days
            for (i in 0..30) {
                val date = CalendarDate.now().plusDays(i)
                add(CalendarEvent("${i * 3 + 1}", "会议 ${i + 1}", "重要会议讨论", date, "09:00"))
                add(CalendarEvent("${i * 3 + 2}", "项目评审", "项目进度检查", date, "14:00"))
                add(CalendarEvent("${i * 3 + 3}", "团队建设", "团队活动安排", date, "18:00"))
            }
        }
    }
    MonthView(
        currentDate = currentDate,
        selectedDate = selectedDate,
        events = events,
        onDateSelected = { selectedDate = it },
        onDateChange = { currentDate = it }
    )
}