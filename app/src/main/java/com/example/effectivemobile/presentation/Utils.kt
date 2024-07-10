package com.example.effectivemobile.presentation

import com.example.effectivemobile.presentation.featured_tickets.Date
import java.time.DayOfWeek
import java.util.Calendar


fun parseCalendar(calendar: Calendar): Date {
    val day = calendar.get(Calendar.DAY_OF_MONTH).toString()

    val monthString = getMonthStringByInt(calendar.get(Calendar.MONTH))

    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    val dayOfWeekString = when (dayOfWeek) {
        Calendar.SUNDAY -> "вс"
        Calendar.MONDAY -> "пн"
        Calendar.TUESDAY -> "вт"
        Calendar.WEDNESDAY -> "ср"
        Calendar.THURSDAY -> "чт"
        Calendar.FRIDAY -> "пт"
        Calendar.SATURDAY -> "сб"
        else -> "Unknown"
    }
    return Date(
        day = day,
        month = monthString,
        dayOfWeek = dayOfWeekString,
    )
}

fun getMonthStringByInt(month: Int): String {
    month + 1
    return when (month) {
        1 -> "янв"
        2 -> "фев"
        3 -> "мар"
        4 -> "апр"
        5 -> "май"
        6 -> "июн"
        7 -> "июл"
        8 -> "авг"
        9 -> "сен"
        10 -> "окт"
        11 -> "ноя"
        12 -> "дек"
        else -> "Unknown"
    }
}

fun getDayOfWeekByDayOffWeek(dayOfWeek: DayOfWeek): String {
    return when (dayOfWeek) {
        DayOfWeek.SUNDAY -> "вс"
        DayOfWeek.MONDAY -> "пн"
        DayOfWeek.TUESDAY -> "вт"
        DayOfWeek.WEDNESDAY -> "ср"
        DayOfWeek.THURSDAY -> "чт"
        DayOfWeek.FRIDAY -> "пт"
        DayOfWeek.SATURDAY -> "сб"
        else -> "Unknown"
    }
}