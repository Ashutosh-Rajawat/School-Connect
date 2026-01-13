package com.atr.schoolconnect.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.atr.schoolconnect.databinding.FragmentCalendarBinding
import com.atr.schoolconnect.domain.CalendarDay
import com.atr.schoolconnect.presentation.adapter.CalendarAdapter
import com.atr.schoolconnect.presentation.adapter.CalendarEventsAdapter
import com.atr.schoolconnect.presentation.adapter.WeekDayAdapter
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth


class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    private val weekDays = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")

    private var currentYear = YearMonth.now().year
    private var currentMonth = YearMonth.now().monthValue
    private var selectedDay: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWeekDays()
        setupCalendar()
        setupClickListeners()
        setupCalendarEvents()
    }

    /* ---------------- Week Days ---------------- */

    private fun setupWeekDays() {
        binding.rvWeek.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.rvWeek.adapter = WeekDayAdapter(weekDays)
    }

    /* ---------------- Calendar Grid ---------------- */

    private fun setupCalendar() {
        binding.tvMonth.text = Month.of(currentMonth).name.lowercase()
            .replaceFirstChar { it.uppercase() }
        binding.tvYear.text = currentYear.toString()

        val days = generateCalendarDays(currentYear, currentMonth)

        binding.rvCalendar.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.rvCalendar.adapter = CalendarAdapter(days) { day ->
            selectedDay = day.day
            setupCalendar()
        }

    }

    /* ---------------- Calendar Events Recyclerview ---------------- */
    fun setupCalendarEvents() {
        binding.recEvents.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = CalendarEventsAdapter(requireContext())
        }
    }

    /* ---------------- Clicks ---------------- */

    private fun setupClickListeners() {
        binding.btnPrev.setOnClickListener {
            val ym = YearMonth.of(currentYear, currentMonth).minusMonths(1)
            currentYear = ym.year
            currentMonth = ym.monthValue
            selectedDay = null
            setupCalendar()
        }

        binding.btnNext.setOnClickListener {
            val ym = YearMonth.of(currentYear, currentMonth).plusMonths(1)
            currentYear = ym.year
            currentMonth = ym.monthValue
            selectedDay = null
            setupCalendar()
        }
    }

    /* ---------------- Calendar Logic ---------------- */

    private fun generateCalendarDays(year: Int, month: Int): List<CalendarDay> {

        val result = mutableListOf<CalendarDay>()
        val yearMonth = YearMonth.of(year, month)
        val firstDay = yearMonth.atDay(1)
        val daysInMonth = yearMonth.lengthOfMonth()

        val startOffset = (firstDay.dayOfWeek.value + 6) % 7
        val today = LocalDate.now()

        // Previous month padding
        val prevMonth = yearMonth.minusMonths(1)
        val prevMonthDays = prevMonth.lengthOfMonth()
        for (i in startOffset downTo 1) {
            result.add(
                CalendarDay(
                    day = prevMonthDays - i + 1,
                    isCurrentMonth = false,
                    isSunday = false,
                    isSelected = false,
                    isToday = false
                )
            )
        }

        // Current month
        for (day in 1..daysInMonth) {
            val date = yearMonth.atDay(day)
            result.add(
                CalendarDay(
                    day = day,
                    isCurrentMonth = true,
                    isSunday = date.dayOfWeek == DayOfWeek.SUNDAY,
                    isSelected = day == selectedDay,
                    isToday = date == today
                )
            )
        }

        // Next month padding
        while (result.size % 7 != 0) {
            result.add(
                CalendarDay(
                    day = result.size % 7 + 1,
                    isCurrentMonth = false,
                    isSunday = false,
                    isSelected = false,
                    isToday = false
                )
            )
        }

        return result
    }
}
