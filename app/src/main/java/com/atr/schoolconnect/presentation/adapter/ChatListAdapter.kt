package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.databinding.SingleChatListLayoutBinding

class ChatListAdapter(private val context: Context, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            SingleChatListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.binding.root.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return 15
    }

    class ViewHolder(var binding: SingleChatListLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}