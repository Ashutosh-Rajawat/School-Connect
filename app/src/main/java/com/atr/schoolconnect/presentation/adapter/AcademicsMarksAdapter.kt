package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.SingleAcademicsMarksLayoutBinding

class AcademicsMarksAdapter(private val context: Context) :
    RecyclerView.Adapter<AcademicsMarksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = SingleAcademicsMarksLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.binding.container.setBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                if (position % 2 == 0)
                    R.color.light_pink
                else
                    R.color.white
            )
        )
    }

    override fun getItemCount(): Int = 6

    class ViewHolder(val binding: SingleAcademicsMarksLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
