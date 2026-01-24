package com.atr.schoolconnect.presentation.utilities

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceConnector(context: Context) {
    private val preferenceName = "School_Connect_Student_0.1"

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    var isLogin: Boolean
        get() {
            return sharedPref.getBoolean(IS_LOGIN, false)
        }
        set(isLogin) {
            sharedPref.edit {
                putBoolean(IS_LOGIN, isLogin)
            }
        }

    var authToken: String
        get() {
            return sharedPref.getString(AUTH_TOKEN, "")!!
        }
        set(token) {
            sharedPref.edit { putString(AUTH_TOKEN, token) }
        }

    var baseUrl: String
        get() {
            return sharedPref.getString(BASE_URL_API, "")!!
        }
        set(baseUrl) {
            sharedPref.edit { putString(BASE_URL_API, baseUrl) }
        }

    var userType: String
        get() {
            return sharedPref.getString(USER_TYPE, "")!!
        }
        set(userType) {
            sharedPref.edit { putString(USER_TYPE, userType) }
        }

    var id: String
        get() {
            return sharedPref.getString(ID, "")!!
        }
        set(id) {
            sharedPref.edit { putString(ID, id) }
        }

    var name: String
        get() {
            return sharedPref.getString(NAME, "")!!
        }
        set(name) {
            sharedPref.edit { putString(NAME, name) }
        }

    var mobile: String
        get() {
            return sharedPref.getString(MOBILE, "")!!
        }
        set(mobile) {
            sharedPref.edit { putString(MOBILE, mobile) }
        }

    var email: String
        get() {
            return sharedPref.getString(EMAIL, "")!!
        }
        set(email) {
            sharedPref.edit { putString(EMAIL, email) }
        }




    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
    companion object {
        const val IS_LOGIN = "is_user_logged_in"
        const val AUTH_TOKEN = "auth_token"

        const val BASE_URL_API = "base_url"
        const val USER_TYPE = "userType"
        const val ID = "id"
        const val NAME = "name"
        const val MOBILE = "mobile"
        const val EMAIL = "email"


    }
}