package com.atr.schoolconnect.presentation.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atr.schoolconnect.databinding.ItemBannerBinding
import com.atr.schoolconnect.domain.BannerModelData
import com.atr.schoolconnect.presentation.utilities.PreferenceConnector
import com.bumptech.glide.Glide

class BannerAdapter(
    private val bannerList: List<BannerModelData>,
    private val requireContext: Context
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    class BannerViewHolder(val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = bannerList[position]
        val url = PreferenceConnector(requireContext).baseUrl+"/"+banner.sliderImage

        val thumb = Glide.with(holder.itemView)
            .load(url)
            .override(
                holder.binding.banner.width / 10,
                holder.binding.banner.height / 10
            )

        Glide.with(holder.itemView)
            .load(url)
            .thumbnail(thumb)
            .into(holder.binding.banner)

    }

    override fun getItemCount(): Int = bannerList.size
}
