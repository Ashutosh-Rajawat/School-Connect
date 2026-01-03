package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.databinding.SinglePaymentDetailLayoutBinding

class FeeDetailAdapter(private var context: Context) : RecyclerView.Adapter<FeeDetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            SinglePaymentDetailLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
//        if (position % 2 == 0) {
//            holder.binding.tvStatus.setTextColor(context.getColor(R.color.green))
//            holder.binding.tvStatus.text = "Paid"
//        }else {
//
//            holder.binding.tvStatus.setTextColor(context.getColor(R.color.red))
//            holder.binding.tvStatus.text = "Due"
//        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    class ViewHolder(var binding: SinglePaymentDetailLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}