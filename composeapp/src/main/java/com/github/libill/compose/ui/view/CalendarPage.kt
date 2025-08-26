package com.github.libill.compose.ui.view

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.libill.compose.ui.view.calendar.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarPage(onBackPressed: () -> Unit = {}) {
    var currentViewMode by remember { mutableStateOf(CalendarViewMode.MONTH) }
    var selectedDate by remember { mutableStateOf(CalendarDate.now()) }
    var currentDate by remember { mutableStateOf(CalendarDate.now()) }
    
    // Transition progress: 0f = week view, 1f = month view
    var transitionProgress by remember { mutableFloatStateOf(1f) }
    var isTransitioning by remember { mutableStateOf(false) }
    
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    // Sample events - extended list for better scrolling demo
    val events = remember {
        mutableStateListOf<CalendarEvent>().apply {
            // Add events for multiple days
            for (i in 0..30) {
                val date = CalendarDate.now().plusDays(i)
                add(CalendarEvent("${i*3+1}", "会议 ${i+1}", "重要会议讨论", date, "09:00"))
                add(CalendarEvent("${i*3+2}", "项目评审", "项目进度检查", date, "14:00"))
                add(CalendarEvent("${i*3+3}", "团队建设", "团队活动安排", date, "18:00"))
            }
        }
    }

    // Nested scroll connection for smooth week/month transition
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: androidx.compose.ui.geometry.Offset, source: NestedScrollSource): androidx.compose.ui.geometry.Offset {
                if (currentViewMode == CalendarViewMode.YEAR) return androidx.compose.ui.geometry.Offset.Zero
                
                val delta = available.y
                val threshold = with(density) { 50.dp.toPx() }
                
                when {
                    // Scrolling up - transition to week view
                    delta < 0 && transitionProgress > 0f && !isTransitioning -> {
                        val newProgress = (transitionProgress + delta / threshold).coerceIn(0f, 1f)
                        transitionProgress = newProgress
                        
                        if (newProgress <= 0.1f && currentViewMode != CalendarViewMode.WEEK) {
                            coroutineScope.launch {
                                isTransitioning = true
                                currentViewMode = CalendarViewMode.WEEK
                                transitionProgress = 0f
                                isTransitioning = false
                            }
                        }
                        return androidx.compose.ui.geometry.Offset(0f, delta * 0.5f)
                    }
                    // Scrolling down - transition to month view
                    delta > 0 && transitionProgress < 1f && !isTransitioning -> {
                        val newProgress = (transitionProgress + delta / threshold).coerceIn(0f, 1f)
                        transitionProgress = newProgress
                        
                        if (newProgress >= 0.9f && currentViewMode != CalendarViewMode.MONTH) {
                            coroutineScope.launch {
                                isTransitioning = true
                                currentViewMode = CalendarViewMode.MONTH
                                transitionProgress = 1f
                                isTransitioning = false
                            }
                        }
                        return androidx.compose.ui.geometry.Offset(0f, delta * 0.5f)
                    }
                }
                return androidx.compose.ui.geometry.Offset.Zero
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        // Header with padding
        Box(modifier = Modifier.padding(16.dp)) {
            CalendarHeader(
                currentViewMode = currentViewMode,
                currentDate = currentDate,
                onViewModeChange = { 
                    currentViewMode = it
                    transitionProgress = when (it) {
                        CalendarViewMode.WEEK -> 0f
                        CalendarViewMode.MONTH -> 1f
                        CalendarViewMode.YEAR -> 1f
                    }
                },
                onDateChange = { currentDate = it },
                onTodayClick = {
                    val today = CalendarDate.now()
                    currentDate = today
                    selectedDate = today
                }
            )
        }

        WeekHeader()

        // Calendar content with smooth transition
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            when (currentViewMode) {
                CalendarViewMode.WEEK -> {
                    WeekView(
                        currentDate = currentDate,
                        selectedDate = selectedDate,
                        events = events,
                        onDateSelected = { selectedDate = it },
                        onDateChange = { currentDate = it }
                    )
                }
                CalendarViewMode.MONTH -> {
                    MonthView(
                        currentDate = currentDate,
                        selectedDate = selectedDate,
                        events = events,
                        onDateSelected = { selectedDate = it },
                        onDateChange = { currentDate = it }
                    )
                }
                CalendarViewMode.YEAR -> {
                    YearView(
                        currentDate = currentDate,
                        selectedDate = selectedDate,
                        events = events,
                        onDateSelected = { selectedDate = it },
                        onDateChange = { currentDate = it },
                        onViewModeChange = { 
                            currentViewMode = it
                            transitionProgress = if (it == CalendarViewMode.MONTH) 1f else 0f
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Events list that extends to bottom with system calendar-like behavior
        SystemCalendarEventsList(
            selectedDate = selectedDate,
            events = events,
            listState = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun SystemCalendarEventsList(
    selectedDate: CalendarDate,
    events: List<CalendarEvent>,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    // Group events by date for better organization
    val groupedEvents = remember(events) {
        events.groupBy { it.date }.toSortedMap()
    }
    
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header for selected date
            item {
                Text(
                    text = "${selectedDate.format("MM月dd日")} 的日程",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            
            // Events for selected date
            val selectedDateEvents = groupedEvents[selectedDate] ?: emptyList()
            if (selectedDateEvents.isNotEmpty()) {
                items(selectedDateEvents) { event ->
                    SystemCalendarEventItem(event = event, isHighlighted = true)
                }
                
                item {
                    Divider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = Color.Gray.copy(alpha = 0.3f)
                    )
                }
            }
            
            // Header for upcoming events
            item {
                Text(
                    text = "即将到来的日程",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            
            // All events grouped by date (excluding selected date)
            groupedEvents.forEach { (date, dateEvents) ->
                if (date != selectedDate) {
                    item {
                        Text(
                            text = date.format("MM月dd日 EEEE"),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    
                    items(dateEvents) { event ->
                        SystemCalendarEventItem(event = event, isHighlighted = false)
                    }
                    
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            
            // Add some bottom padding for better scrolling experience
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
private fun SystemCalendarEventItem(
    event: CalendarEvent,
    isHighlighted: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isHighlighted) Color(0xFFF0F8FF) else Color(0xFFF8F8F8)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isHighlighted) 2.dp else 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Time indicator
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isHighlighted) Color(0xFF07C160) else Color(0xFF999999)
                ),
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = event.time,
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Event details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = event.title,
                    fontSize = 16.sp,
                    fontWeight = if (isHighlighted) FontWeight.Bold else FontWeight.Medium,
                    color = if (isHighlighted) Color.Black else Color(0xFF333333)
                )
                if (event.description.isNotEmpty()) {
                    Text(
                        text = event.description,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}