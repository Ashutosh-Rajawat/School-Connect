package com.atr.schoolconnect.domain

data class CalendarDay(
    val day: Int,
    val isCurrentMonth: Boolean,
    val isSunday: Boolean,
    val isSelected: Boolean,
    val isToday: Boolean
)
