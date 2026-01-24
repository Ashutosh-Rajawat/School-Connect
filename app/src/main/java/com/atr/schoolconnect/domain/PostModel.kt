package com.atr.schoolconnect.domain

import com.google.gson.annotations.SerializedName

data class PostModel(

    @SerializedName("status") var status: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("base_url") var baseUrl: String? = null,
    @SerializedName("data") var data: MutableList<PostModelData> = mutableListOf(),
    @SerializedName("current_page") var currentPage: Int? = null

)
data class PostModelData(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("admin_id") var adminId: Int? = null,
    @SerializedName("post_title") var postTitle: String? = null,
    @SerializedName("post_description") var postDescription: String? = null,
    @SerializedName("post_image") var postImage: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null

)

