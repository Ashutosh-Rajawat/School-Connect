package com.atr.schoolconnect.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.ItemCalendarDayBinding
import com.atr.schoolconnect.domain.CalendarDay

class CalendarAdapter(
    private val days: List<CalendarDay>,
    private val onDayClick: (CalendarDay) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.DayVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayVH {
        val view = ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context)
         ,parent, false)
        return DayVH(view)
    }

    override fun onBindViewHolder(holder: DayVH, position: Int) {
        val item = days[position]

       holder.binding.tvDay.text = item.day.toString()

        when {
            item.isSelected -> {
               holder.binding.tvDay.setBackgroundResource(R.drawable.bg_selected)
               holder.binding.tvDay.setTextColor(Color.WHITE)
            }
            item.isToday -> {
               holder.binding.tvDay.setBackgroundResource(R.drawable.bg_today)
               holder.binding.tvDay.setTextColor(Color.WHITE)
            }
            !item.isCurrentMonth -> {
               holder.binding.tvDay.setTextColor(Color.LTGRAY)
            }
            item.isSunday -> {
               holder.binding.tvDay.setTextColor(Color.RED)
            }
            else -> {
               holder.binding.tvDay.setBackgroundResource(R.drawable.bg_day_default)
               holder.binding.tvDay.setTextColor(Color.BLACK)
            }
        }

        holder.binding.tvDay.setOnClickListener {
            if (item.isCurrentMonth) {
                onDayClick(item)
            }
        }
    }


    override fun getItemCount() = days.size

    class DayVH(var binding: ItemCalendarDayBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}
