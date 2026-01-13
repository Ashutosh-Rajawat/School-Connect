package com.atr.schoolconnect.presentation.utilities

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QsConstants {
    companion object {
        /**
         * Json to Map convertor
         */
        fun jsonToMap(jsonString: String): Map<String, Any>? {
            return try {
                val gson = Gson()
                val type = object : TypeToken<Map<String, Any>>() {}.type
                gson.fromJson<Map<String, Any>>(jsonString, type)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}