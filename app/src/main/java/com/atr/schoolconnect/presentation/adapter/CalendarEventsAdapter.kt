package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.databinding.SingleCalendarEventsLayoutBinding

class CalendarEventsAdapter(private val context: Context) :
    RecyclerView.Adapter<CalendarEventsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = SingleCalendarEventsLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.binding.header.visibility = View.VISIBLE
            holder.binding.eventsContainer.visibility = View.GONE
        }else {
            holder.binding.header.visibility = View.GONE
            holder.binding.eventsContainer.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    class ViewHolder(var binding: SingleCalendarEventsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}