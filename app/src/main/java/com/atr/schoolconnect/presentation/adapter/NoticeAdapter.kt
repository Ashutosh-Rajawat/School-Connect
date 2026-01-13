package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.databinding.SingleNoticeLayoutBinding

class NoticeAdapter(private val context: Context) :
    RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {
    private val itemCount = 8
    private val lastPosition = itemCount - 1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var binding =
            SingleNoticeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        if (position != 0) {
            holder.binding.image.visibility = View.GONE
        }

        val params =
            holder.binding.messageContainer.layoutParams as ViewGroup.MarginLayoutParams

        if (position == itemCount - 1) {
            params.bottomMargin = 50   // space from bottom
        }
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    class ViewHolder(var binding: SingleNoticeLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    fun View.setMargin(left: Int, top: Int, right: Int, bottom: Int) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(left, top, right, bottom)
        layoutParams = params
    }

}