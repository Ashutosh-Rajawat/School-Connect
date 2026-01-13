package com.atr.schoolconnect.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.databinding.ItemWeekDayBinding

class WeekDayAdapter(private val days: List<String>) :
    RecyclerView.Adapter<WeekDayAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val tv = ItemWeekDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VH(tv)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvWeekDay.text = days[position]
        holder.binding.tvWeekDay.setTextColor(
            if (days[position] == "Su") Color.RED else Color.parseColor("#1E8E8E")
        )
    }

    override fun getItemCount() = days.size

    class VH(val binding: ItemWeekDayBinding) : RecyclerView.ViewHolder(binding.root)
}
