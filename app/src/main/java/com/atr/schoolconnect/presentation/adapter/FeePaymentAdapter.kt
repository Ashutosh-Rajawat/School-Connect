package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.SingleRecepitsBinding

class FeePaymentAdapter(private var context: Context,private var onItemClick:(Int)->Unit) :
    RecyclerView.Adapter<FeePaymentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            SingleRecepitsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        if (position % 2 == 0) {
            holder.binding.tvStatus.setTextColor(context.getColor(R.color.green))
            holder.binding.tvStatus.text = "Paid"
        }else {

            holder.binding.tvStatus.setTextColor(context.getColor(R.color.red))
            holder.binding.tvStatus.text = "Due"
        }
        holder.binding.root.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    class ViewHolder(var binding: SingleRecepitsBinding) : RecyclerView.ViewHolder(binding.root)
}