package com.example.effectivemobile.presentation

import com.example.effectivemobile.presentation.featured_tickets.Date
import java.time.DayOfWeek
import java.util.Calendar
import java.util.Locale


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
    return when (month) {
        0 -> "янв"
        1 -> "фев"
        2 -> "мар"
        3 -> "апр"
        4 -> "май"
        5 -> "июн"
        6 -> "июл"
        7 -> "авг"
        8 -> "сен"
        9 -> "окт"
        10 -> "ноя"
        11 -> "дек"
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

fun parseDate(date: String): String {
    val day = date.substringBefore(" ").trim()
    val month = date.substringAfter(" ").trim().replace(",", "")

    val fullMonth = when (month) {
        "янв" -> "января"
        "фев" -> "февраля"
        "мар" -> "марта"
        "апр" -> "апреля"
        "май" -> "мая"
        "июн" -> "июня"
        "июл" -> "июля"
        "авг" -> "августа"
        "сен" -> "сентября"
        "окт" -> "октября"
        "ноя" -> "ноября"
        "дек" -> "декабря"
        else -> month
    }
    return String.format("%s %s", day, fullMonth)
}

fun parsePassengers(passengers: String): String {
    val parts = passengers.split(",")

    val number = parts[0].trim()
    val category = parts[1].trim()

    val categoryFixed = when (category.lowercase(Locale.ROOT)) {
        "эконом" -> "пассажир"
        "business" -> "бизнес"
        "vip" -> "VIP"
        else -> category
    }

    return String.format("%s %s", number, categoryFixed)

}