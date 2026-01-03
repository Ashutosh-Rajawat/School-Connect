package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.databinding.SingleChatLayoutBinding

class ChatAdapter(private val context: Context) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var binding =
            SingleChatLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

    }

    override fun getItemCount(): Int {
        return 2
    }

    class ViewHolder(val binding: SingleChatLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}