package com.atr.schoolconnect.data.controller

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

interface RestService {

    @POST(ApiUrls.LOGIN)
    suspend fun login(@Body params: JsonObject): JsonElement
}