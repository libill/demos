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
fun MonthView(
    currentDate: CalendarDate,
    selectedDate: CalendarDate,
    events: List<CalendarEvent>,
    onDateSelected: (CalendarDate) -> Unit,
    onDateChange: (CalendarDate) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    
    // Calculate months around current date
    val months = remember(currentDate) {
        val startMonth = CalendarYearMonth.from(currentDate).minusMonths(24) // Show 24 months before
        val endMonth = CalendarYearMonth.from(currentDate).plusMonths(24)   // Show 24 months after
        
        val monthsList = mutableListOf<CalendarYearMonth>()
        var currentMonth = startMonth
        
        while (currentMonth.year < endMonth.year || 
               (currentMonth.year == endMonth.year && currentMonth.month <= endMonth.month)) {
            monthsList.add(currentMonth)
            currentMonth = currentMonth.plusMonths(1)
        }
        monthsList
    }
    
    // Find current month index
    val currentMonthIndex = remember(currentDate, months) {
        months.indexOfFirst { month ->
            month.year == currentDate.year && month.month == currentDate.month
        }.takeIf { it >= 0 } ?: 24
    }
    
    // Auto scroll to current month - immediate positioning
    LaunchedEffect(currentMonthIndex) {
        if (currentMonthIndex >= 0) {
            listState.scrollToItem(currentMonthIndex) // Use scrollToItem for immediate positioning
        }
    }
    
    // Also scroll immediately when currentDate changes
    LaunchedEffect(currentDate) {
        val newMonthIndex = months.indexOfFirst { month ->
            month.year == currentDate.year && month.month == currentDate.month
        }
        if (newMonthIndex >= 0) {
            listState.scrollToItem(newMonthIndex)
        }
    }
    
    // More sensitive gesture detection for month switching
    val density = LocalDensity.current
    var dragOffset by remember { mutableFloatStateOf(0f) }
    
    // Detect when scrolling stops and update currentDate to the visible month's first day
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val visibleItemIndex = listState.firstVisibleItemIndex
            if (visibleItemIndex >= 0 && visibleItemIndex < months.size) {
                val visibleMonth = months[visibleItemIndex]
                val monthStartDate = visibleMonth.atDay(1) // First day of month
                if (monthStartDate.year != currentDate.year || monthStartDate.month != currentDate.month) {
                    onDateChange(monthStartDate)
                }
            }
        }
    }
    
    Column {
        // Week days header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val weekDays = listOf("日", "一", "二", "三", "四", "五", "六")
            weekDays.forEach { day ->
                Text(
                    text = day,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
        
        // Month view with horizontal scrolling and sensitive gesture detection
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
                                    // Swipe right - go to previous month
                                    val currentMonthIndex = months.indexOfFirst { month ->
                                        month.year == currentDate.year && month.month == currentDate.month
                                    }
                                    if (currentMonthIndex > 0) {
                                        val prevMonth = months[currentMonthIndex - 1]
                                        val prevMonthStart = prevMonth.atDay(1)
                                        onDateChange(prevMonthStart)
                                        coroutineScope.launch {
                                            listState.animateScrollToItem(currentMonthIndex - 1)
                                        }
                                    }
                                }
                                dragOffset < -threshold -> {
                                    // Swipe left - go to next month
                                    val currentMonthIndex = months.indexOfFirst { month ->
                                        month.year == currentDate.year && month.month == currentDate.month
                                    }
                                    if (currentMonthIndex < months.size - 1) {
                                        val nextMonth = months[currentMonthIndex + 1]
                                        val nextMonthStart = nextMonth.atDay(1)
                                        onDateChange(nextMonthStart)
                                        coroutineScope.launch {
                                            listState.animateScrollToItem(currentMonthIndex + 1)
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
            items(months.size) { monthIndex ->
                val month = months[monthIndex]
                
                MonthGrid(
                    yearMonth = month,
                    selectedDate = selectedDate,
                    events = events,
                    onDateSelected = {
                        onDateSelected(it)
                        onDateChange(it)
                    },
                    modifier = Modifier
                        .width(350.dp)
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun MonthGrid(
    yearMonth: CalendarYearMonth,
    selectedDate: CalendarDate,
    events: List<CalendarEvent>,
    onDateSelected: (CalendarDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val firstDayOfMonth = yearMonth.atDay(1)
    val firstDayOfWeek = firstDayOfMonth.getDayOfWeek() // 1 = Sunday, 7 = Saturday
    val daysInMonth = yearMonth.lengthOfMonth()
    
    // Calculate the starting offset (0 = Sunday, 6 = Saturday)
    val startOffset = if (firstDayOfWeek == Calendar.SUNDAY) 0 else firstDayOfWeek - 1
    
    // Calculate previous month days to show
    val prevMonth = yearMonth.minusMonths(1)
    val daysInPrevMonth = prevMonth.lengthOfMonth()
    val prevMonthDays = if (startOffset > 0) {
        (daysInPrevMonth - startOffset + 1..daysInPrevMonth).map { day ->
            prevMonth.atDay(day)
        }
    } else emptyList()
    
    // Calculate next month days to show
    val totalCells = 42 // 6 rows × 7 days
    val currentMonthDays = (1..daysInMonth).map { day ->
        yearMonth.atDay(day)
    }
    val remainingCells = totalCells - prevMonthDays.size - currentMonthDays.size
    val nextMonth = yearMonth.plusMonths(1)
    val nextMonthDays = if (remainingCells > 0) {
        (1..remainingCells).map { day ->
            nextMonth.atDay(day)
        }
    } else emptyList()
    
    val allDays = prevMonthDays + currentMonthDays + nextMonthDays
    
    Column(modifier = modifier) {
        // Create 6 rows of 7 days each
        for (week in 0 until 6) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (day in 0 until 7) {
                    val dayIndex = week * 7 + day
                    if (dayIndex < allDays.size) {
                        val date = allDays[dayIndex]
                        val isCurrentMonth = date.month == yearMonth.month && date.year == yearMonth.year
                        
                        MonthDayCell(
                            date = date,
                            isSelected = date == selectedDate,
                            isToday = date == CalendarDate.now(),
                            isCurrentMonth = isCurrentMonth,
                            hasEvents = events.any { it.date == date },
                            onClick = { onDateSelected(date) },
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun MonthDayCell(
    date: CalendarDate,
    isSelected: Boolean,
    isToday: Boolean,
    isCurrentMonth: Boolean,
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
        !isCurrentMonth -> Color.Gray
        else -> Color.Black
    }
    
    Column(
        modifier = modifier
            .padding(2.dp)
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
        
        if (hasEvents && !isSelected && isCurrentMonth) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF07C160))
            )
        }
    }
}