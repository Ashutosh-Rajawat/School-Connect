package com.atr.schoolconnect.domain

import com.google.gson.annotations.SerializedName

data class BannerModel(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: MutableList<BannerModelData> = mutableListOf()
)

data class BannerModelData(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("image_title") var imageTitle: String? = null,
    @SerializedName("display_order") var displayOrder: Int? = null,
    @SerializedName("slider_image") var sliderImage: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null

)