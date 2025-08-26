package com.github.libill.compose.ui.view.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun WeekView(
    currentDate: CalendarDate,
    selectedDate: CalendarDate,
    events: List<CalendarEvent>,
    onDateSelected: (CalendarDate) -> Unit,
    onDateChange: (CalendarDate) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    
    // Calculate weeks around current date
    val weeks = remember(currentDate) {
        val startWeek = currentDate.minusWeeks(52) // Show 52 weeks before
        val endWeek = currentDate.plusWeeks(52)   // Show 52 weeks after
        
        val weeksList = mutableListOf<List<CalendarDate>>()
        var currentWeekStart = startWeek.withDayOfWeek(Calendar.SUNDAY)
        
        while (currentWeekStart <= endWeek) {
            val week = (0..6).map { dayOffset ->
                currentWeekStart.plusDays(dayOffset)
            }
            weeksList.add(week)
            currentWeekStart = currentWeekStart.plusWeeks(1)
        }
        weeksList
    }
    
    // Find current week index
    val currentWeekIndex = remember(currentDate, weeks) {
        weeks.indexOfFirst { week ->
            week.any { date ->
                date.year == currentDate.year &&
                date.month == currentDate.month &&
                date.day == currentDate.day
            }
        }.takeIf { it >= 0 } ?: 52
    }
    
    // Auto scroll to current week - immediate positioning
    LaunchedEffect(currentWeekIndex) {
        if (currentWeekIndex >= 0) {
            listState.scrollToItem(currentWeekIndex) // Use scrollToItem for immediate positioning
        }
    }
    
    // Also scroll immediately when currentDate changes
    LaunchedEffect(currentDate) {
        val newWeekIndex = weeks.indexOfFirst { week ->
            week.any { date ->
                date.year == currentDate.year &&
                date.month == currentDate.month &&
                date.day == currentDate.day
            }
        }
        if (newWeekIndex >= 0) {
            listState.scrollToItem(newWeekIndex)
        }
    }
    
    // More sensitive gesture detection for week switching
    val density = LocalDensity.current
    var dragOffset by remember { mutableFloatStateOf(0f) }
    
    // Detect when scrolling stops and update currentDate to the visible week's start
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val visibleItemIndex = listState.firstVisibleItemIndex
            if (visibleItemIndex >= 0 && visibleItemIndex < weeks.size) {
                val visibleWeek = weeks[visibleItemIndex]
                val weekStartDate = visibleWeek.first() // Sunday (start of week)
                if (weekStartDate != currentDate) {
                    onDateChange(weekStartDate)
                }
            }
        }
    }
    
    Column {
        // Week view with horizontal scrolling and sensitive gesture detection
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            val threshold = with(density) { 30.dp.toPx() } // Lower threshold for more sensitivity
                            when {
                                dragOffset > threshold -> {
                                    // Swipe right - go to previous week
                                    val currentWeekIndex = weeks.indexOfFirst { week ->
                                        week.any { date ->
                                            date.year == currentDate.year &&
                                            date.month == currentDate.month &&
                                            date.day == currentDate.day
                                        }
                                    }
                                    if (currentWeekIndex > 0) {
                                        val prevWeek = weeks[currentWeekIndex - 1]
                                        val prevWeekStart = prevWeek.first()
                                        onDateChange(prevWeekStart)
                                        coroutineScope.launch {
                                            listState.animateScrollToItem(currentWeekIndex - 1)
                                        }
                                    }
                                }
                                dragOffset < -threshold -> {
                                    // Swipe left - go to next week
                                    val currentWeekIndex = weeks.indexOfFirst { week ->
                                        week.any { date ->
                                            date.year == currentDate.year &&
                                            date.month == currentDate.month &&
                                            date.day == currentDate.day
                                        }
                                    }
                                    if (currentWeekIndex < weeks.size - 1) {
                                        val nextWeek = weeks[currentWeekIndex + 1]
                                        val nextWeekStart = nextWeek.first()
                                        onDateChange(nextWeekStart)
                                        coroutineScope.launch {
                                            listState.animateScrollToItem(currentWeekIndex + 1)
                                        }
                                    }
                                }
                            }
                            dragOffset = 0f
                        }
                    ) { _, dragAmount ->
                        dragOffset += dragAmount
                    }
                },
            contentPadding = PaddingValues(horizontal = 8.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
        ) {
            items(weeks.size) { weekIndex ->
                val week = weeks[weekIndex]
                
                Row(
                    modifier = Modifier
                        .width(350.dp)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    week.forEach { date ->
                        WeekDayCell(
                            date = date,
                            isSelected = date == selectedDate,
                            isToday = date == CalendarDate.now(),
                            hasEvents = events.any { it.date == date },
                            onClick = {
                                onDateSelected(date)
                                onDateChange(date)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WeekDayCell(
    date: CalendarDate,
    isSelected: Boolean,
    isToday: Boolean,
    hasEvents: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        isSelected -> Color(0xFF07C160)
        isToday -> Color(0xFFE3F2FD)
        else -> Color.Transparent
    }
    
    val textColor = when {
        isSelected -> Color.White
        isToday -> Color(0xFF1976D2)
        else -> Color.Black
    }
    
    Column(
        modifier = modifier
            .padding(4.dp)
            .size(40.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = date.day.toString(),
            fontSize = 14.sp,
            fontWeight = if (isSelected || isToday) FontWeight.Bold else FontWeight.Normal,
            color = textColor,
            textAlign = TextAlign.Center
        )
        
        if (hasEvents && !isSelected) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF07C160))
            )
        }
    }
}