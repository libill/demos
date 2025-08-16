package com.github.libill.compose.ui.view.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun YearView(
    currentDate: CalendarDate,
    selectedDate: CalendarDate,
    events: List<CalendarEvent>,
    onDateSelected: (CalendarDate) -> Unit,
    onDateChange: (CalendarDate) -> Unit,
    onViewModeChange: (CalendarViewMode) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    
    // Calculate years around current date
    val years = remember(currentDate) {
        val startYear = currentDate.year - 10 // Show 10 years before
        val endYear = currentDate.year + 10   // Show 10 years after
        (startYear..endYear).toList()
    }
    
    // Find current year index
    val currentYearIndex = remember(currentDate, years) {
        years.indexOf(currentDate.year).takeIf { it >= 0 } ?: 10
    }
    
    // Auto scroll to current year - immediate positioning
    LaunchedEffect(currentYearIndex) {
        if (currentYearIndex >= 0) {
            listState.scrollToItem(currentYearIndex) // Use scrollToItem for immediate positioning
        }
    }
    
    // Also scroll immediately when currentDate changes
    LaunchedEffect(currentDate) {
        val newYearIndex = years.indexOf(currentDate.year)
        if (newYearIndex >= 0) {
            listState.scrollToItem(newYearIndex)
        }
    }
    
    // Year view with horizontal scrolling
    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(years.size) { yearIndex ->
            val year = years[yearIndex]
            
            YearGrid(
                year = year,
                selectedDate = selectedDate,
                events = events,
                onMonthSelected = { month ->
                    val newDate = CalendarDate.of(year, month, 1)
                    onDateChange(newDate)
                    onViewModeChange(CalendarViewMode.MONTH)
                },
                modifier = Modifier
                    .width(350.dp)
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
private fun YearGrid(
    year: Int,
    selectedDate: CalendarDate,
    events: List<CalendarEvent>,
    onMonthSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Year title
        Text(
            text = "${year}年",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )
        
        // 12 months in a 3x4 grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(12) { monthIndex ->
                val month = monthIndex + 1
                val yearMonth = CalendarYearMonth.of(year, month)
                
                MonthCard(
                    yearMonth = yearMonth,
                    selectedDate = selectedDate,
                    events = events,
                    onClick = { onMonthSelected(month) }
                )
            }
        }
    }
}

@Composable
private fun MonthCard(
    yearMonth: CalendarYearMonth,
    selectedDate: CalendarDate,
    events: List<CalendarEvent>,
    onClick: () -> Unit
) {
    val isCurrentMonth = yearMonth.year == CalendarDate.now().year && 
                        yearMonth.month == CalendarDate.now().month
    val hasEvents = events.any { 
        it.date.year == yearMonth.year && it.date.month == yearMonth.month 
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrentMonth) Color(0xFFE3F2FD) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Month name
            Text(
                text = yearMonth.format("MM月"),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (isCurrentMonth) Color(0xFF1976D2) else Color.Black
            )
            
            // Mini calendar grid (simplified)
            MiniMonthGrid(
                yearMonth = yearMonth,
                selectedDate = selectedDate,
                hasEvents = hasEvents
            )
            
            // Event indicator
            if (hasEvents) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color(0xFF07C160))
                )
            } else {
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@Composable
private fun MiniMonthGrid(
    yearMonth: CalendarYearMonth,
    selectedDate: CalendarDate,
    hasEvents: Boolean
) {
    val firstDayOfMonth = yearMonth.atDay(1)
    val firstDayOfWeek = firstDayOfMonth.getDayOfWeek()
    val daysInMonth = yearMonth.lengthOfMonth()
    
    // Create a simplified 6x7 grid for the mini calendar
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.size(60.dp),
        userScrollEnabled = false
    ) {
        // Add empty cells for days before the first day of month
        val startOffset = if (firstDayOfWeek == 1) 0 else firstDayOfWeek - 1
        items(startOffset) {
            Spacer(modifier = Modifier.size(2.dp))
        }
        
        // Add days of the month
        items(daysInMonth) { dayIndex ->
            val day = dayIndex + 1
            val date = yearMonth.atDay(day)
            val isSelected = date == selectedDate
            val isToday = date == CalendarDate.now()
            
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(RoundedCornerShape(1.dp))
                    .background(
                        when {
                            isSelected -> Color(0xFF07C160)
                            isToday -> Color(0xFF1976D2)
                            else -> Color.LightGray
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (day <= 31) { // Only show valid days
                    Box(
                        modifier = Modifier
                            .size(1.dp)
                            .background(Color.White)
                    )
                }
            }
        }
    }
}