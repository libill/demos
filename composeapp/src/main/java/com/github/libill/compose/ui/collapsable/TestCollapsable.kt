package com.github.libill.compose.ui.collapsable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.libill.compose.ui.view.calendar.CalendarDate
import com.github.libill.compose.ui.view.calendar.CalendarEvent
import com.github.libill.compose.ui.view.calendar.CalendarViewMode
import com.github.libill.compose.ui.view.calendar.MonthView
import com.github.libill.compose.ui.view.calendar.WeekHeader
import com.github.libill.compose.utils.pxToDp

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
                        .offset(y = (collapsableLayoutState.offset.y).pxToDp(context = LocalContext.current))
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