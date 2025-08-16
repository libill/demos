package com.github.libill.compose.ui.view.calendar

import java.text.SimpleDateFormat
import java.util.*

// Calendar view modes
enum class CalendarViewMode {
    WEEK, MONTH, YEAR
}

// Custom CalendarDate class to replace LocalDate (API 24+ compatible)
data class CalendarDate(val year: Int, val month: Int, val day: Int) : Comparable<CalendarDate> {
    
    override fun compareTo(other: CalendarDate): Int {
        return when {
            year != other.year -> year.compareTo(other.year)
            month != other.month -> month.compareTo(other.month)
            else -> day.compareTo(other.day)
        }
    }
    
    operator fun plus(other: CalendarDate): CalendarDate = this
    operator fun minus(other: CalendarDate): CalendarDate = this
    
    companion object {
        fun now(): CalendarDate {
            val calendar = Calendar.getInstance()
            return CalendarDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1, // Calendar.MONTH is 0-based
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        }
        
        fun of(year: Int, month: Int, day: Int): CalendarDate {
            return CalendarDate(year, month, day)
        }
    }
    
    fun plusDays(days: Int): CalendarDate {
        val calendar = toCalendar()
        calendar.add(Calendar.DAY_OF_MONTH, days)
        return fromCalendar(calendar)
    }
    
    fun minusDays(days: Int): CalendarDate {
        val calendar = toCalendar()
        calendar.add(Calendar.DAY_OF_MONTH, -days)
        return fromCalendar(calendar)
    }
    
    fun plusWeeks(weeks: Int): CalendarDate {
        val calendar = toCalendar()
        calendar.add(Calendar.WEEK_OF_YEAR, weeks)
        return fromCalendar(calendar)
    }
    
    fun minusWeeks(weeks: Int): CalendarDate {
        val calendar = toCalendar()
        calendar.add(Calendar.WEEK_OF_YEAR, -weeks)
        return fromCalendar(calendar)
    }
    
    fun plusMonths(months: Int): CalendarDate {
        val calendar = toCalendar()
        calendar.add(Calendar.MONTH, months)
        return fromCalendar(calendar)
    }
    
    fun minusMonths(months: Int): CalendarDate {
        val calendar = toCalendar()
        calendar.add(Calendar.MONTH, -months)
        return fromCalendar(calendar)
    }
    
    fun plusYears(years: Int): CalendarDate {
        val calendar = toCalendar()
        calendar.add(Calendar.YEAR, years)
        return fromCalendar(calendar)
    }
    
    fun minusYears(years: Int): CalendarDate {
        val calendar = toCalendar()
        calendar.add(Calendar.YEAR, -years)
        return fromCalendar(calendar)
    }
    
    fun getDayOfWeek(): Int {
        val calendar = toCalendar()
        return calendar.get(Calendar.DAY_OF_WEEK)
    }
    
    fun getDayOfMonth(): Int = day
    
    fun format(pattern: String): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(toCalendar().time)
    }
    
    fun withDayOfWeek(dayOfWeek: Int): CalendarDate {
        val calendar = toCalendar()
        val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val diff = dayOfWeek - currentDayOfWeek
        calendar.add(Calendar.DAY_OF_MONTH, diff)
        return fromCalendar(calendar)
    }
    
    fun toCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day) // Calendar.MONTH is 0-based
        return calendar
    }
    
    private fun fromCalendar(calendar: Calendar): CalendarDate {
        return CalendarDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1, // Calendar.MONTH is 0-based
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
}

// Custom CalendarYearMonth class to replace YearMonth (API 24+ compatible)
data class CalendarYearMonth(val year: Int, val month: Int) : Comparable<CalendarYearMonth> {
    
    override fun compareTo(other: CalendarYearMonth): Int {
        return when {
            year != other.year -> year.compareTo(other.year)
            else -> month.compareTo(other.month)
        }
    }
    
    companion object {
        fun from(date: CalendarDate): CalendarYearMonth {
            return CalendarYearMonth(date.year, date.month)
        }
        
        fun of(year: Int, month: Int): CalendarYearMonth {
            return CalendarYearMonth(year, month)
        }
        
        fun now(): CalendarYearMonth {
            val calendar = Calendar.getInstance()
            return CalendarYearMonth(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH is 0-based
            )
        }
    }
    
    fun atDay(day: Int): CalendarDate {
        return CalendarDate(year, month, day)
    }
    
    fun lengthOfMonth(): Int {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, 1) // Calendar.MONTH is 0-based
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }
    
    fun format(pattern: String): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, 1) // Calendar.MONTH is 0-based
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(calendar.time)
    }
    
    fun plusMonths(months: Int): CalendarYearMonth {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, 1) // Calendar.MONTH is 0-based
        calendar.add(Calendar.MONTH, months)
        return CalendarYearMonth(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH is 0-based
        )
    }
    
    fun minusMonths(months: Int): CalendarYearMonth {
        val calendar = Calendar.getInstance()
        calendar.set(year, month - 1, 1) // Calendar.MONTH is 0-based
        calendar.add(Calendar.MONTH, -months)
        return CalendarYearMonth(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH is 0-based
        )
    }
}

// Calendar event data class
data class CalendarEvent(
    val id: String,
    val title: String,
    val description: String,
    val date: CalendarDate,
    val time: String
)