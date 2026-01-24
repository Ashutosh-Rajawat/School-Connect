package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.R
import com.atr.schoolconnect.databinding.SingleSocialLayoutBinding
import com.atr.schoolconnect.domain.PostModel
import com.atr.schoolconnect.domain.PostModelData

class HomeSocialAdapter(private val context: Context, private var postList: MutableList<PostModelData>) :
    RecyclerView.Adapter<HomeSocialAdapter.ViewHolder>() {

    fun submitList(list: List<PostModelData>) {
        postList = list.toMutableList()
        notifyDataSetChanged()
    }


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
            HtmlCompat.fromHtml(context.getString(R.string.post_str), HtmlCompat.FROM_HTML_MODE_LEGACY)


    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(var binding: SingleSocialLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}