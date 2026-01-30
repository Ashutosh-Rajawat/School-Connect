package com.atr.schoolconnect.data.controller

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RestService {

    @POST(ApiUrls.LOGIN)
    suspend fun login(@Body params: JsonObject): JsonElement

    @GET(ApiUrls.POSTS)
    suspend fun getPosts(@Header("Authorization")token: String, @Query("page") page: Int): JsonElement

    @GET(ApiUrls.BANNERS)
    suspend fun getBanners(): JsonElement
}