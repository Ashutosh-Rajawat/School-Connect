package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.SingleSocialLayoutBinding

class HomeSocialAdapter(private val context: Context) :
    RecyclerView.Adapter<HomeSocialAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            SingleSocialLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.binding.tvCaption.text =
            HtmlCompat.fromHtml(
                context.getString(R.string.post_str),
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )


    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(var binding: SingleSocialLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}