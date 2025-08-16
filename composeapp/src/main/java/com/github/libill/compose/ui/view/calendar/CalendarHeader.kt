package com.github.libill.compose.ui.view.calendar

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarHeader(
    currentViewMode: CalendarViewMode,
    currentDate: CalendarDate,
    onViewModeChange: (CalendarViewMode) -> Unit,
    onDateChange: (CalendarDate) -> Unit,
    onTodayClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Top row with navigation and today button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Navigation buttons
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        onDateChange(
                            when (currentViewMode) {
                                CalendarViewMode.WEEK -> currentDate.minusWeeks(1)
                                CalendarViewMode.MONTH -> currentDate.minusMonths(1)
                                CalendarViewMode.YEAR -> currentDate.minusYears(1)
                            }
                        )
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Previous")
                    }

                    Text(
                        text = when (currentViewMode) {
                            CalendarViewMode.WEEK -> {
                                val calendar = currentDate.toCalendar()
                                val weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH)
                                "${currentDate.format("yyyy年MM月")} 第${weekOfMonth}周"
                            }
                            CalendarViewMode.MONTH -> currentDate.format("yyyy年MM月")
                            CalendarViewMode.YEAR -> currentDate.format("yyyy年")
                        },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    IconButton(onClick = {
                        onDateChange(
                            when (currentViewMode) {
                                CalendarViewMode.WEEK -> currentDate.plusWeeks(1)
                                CalendarViewMode.MONTH -> currentDate.plusMonths(1)
                                CalendarViewMode.YEAR -> currentDate.plusYears(1)
                            }
                        )
                    }) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Next")
                    }
                }

                // Today button
                TextButton(onClick = onTodayClick) {
                    Text("今天", color = Color(0xFF07C160))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // View mode switcher
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CalendarViewMode.values().forEach { mode ->
                    FilterChip(
                        onClick = { onViewModeChange(mode) },
                        label = {
                            Text(
                                when (mode) {
                                    CalendarViewMode.WEEK -> "周"
                                    CalendarViewMode.MONTH -> "月"
                                    CalendarViewMode.YEAR -> "年"
                                }
                            )
                        },
                        selected = currentViewMode == mode,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}