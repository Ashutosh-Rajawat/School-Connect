package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.SingleSubjectLayoutBinding

class SubjectAdapter(private val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            SingleSubjectLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        if (position % 2 == 0) {
            holder.binding.container.setBackgroundColor(context.getColor(R.color.light_pink))
            holder.binding.tvSub1.setTextColor(context.getColor(R.color.green))
            holder.binding.tvTeach1.setTextColor(context.getColor(R.color.green))
        } else {
            holder.binding.container.setBackgroundColor(context.getColor(R.color.white))
            holder.binding.tvSub1.setTextColor(context.getColor(R.color.purple))
            holder.binding.tvTeach1.setTextColor(context.getColor(R.color.purple))

        }
    }

    override fun getItemCount(): Int {
        return 8
    }


    class ViewHolder(var binding: SingleSubjectLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}