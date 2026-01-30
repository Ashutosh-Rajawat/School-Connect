package com.atr.schoolconnect.presentation.utilities

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit

class PreferenceConnector(context: Context) {

    private val preferenceName = "School_Connect_Student_0.1.1"

    private val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    private val sharedPref: SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            preferenceName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    var isLogin: Boolean
        get() = sharedPref.getBoolean(IS_LOGIN, false)
        set(value) = sharedPref.edit { putBoolean(IS_LOGIN, value) }

    var authToken: String
        get() = sharedPref.getString(AUTH_TOKEN, "") ?: ""
        set(value) = sharedPref.edit { putString(AUTH_TOKEN, value) }

    var baseUrl: String
        get() = sharedPref.getString(BASE_URL_API, "") ?: ""
        set(value) = sharedPref.edit { putString(BASE_URL_API, value) }

    var userType: String
        get() = sharedPref.getString(USER_TYPE, "") ?: ""
        set(value) = sharedPref.edit { putString(USER_TYPE, value) }

    var id: String
        get() = sharedPref.getString(ID, "") ?: ""
        set(value) = sharedPref.edit().putString(ID, value).apply()

    var name: String
        get() = sharedPref.getString(NAME, "") ?: ""
        set(value) = sharedPref.edit { putString(NAME, value) }

    var mobile: String
        get() = sharedPref.getString(MOBILE, "") ?: ""
        set(value) = sharedPref.edit { putString(MOBILE, value) }

    var email: String
        get() = sharedPref.getString(EMAIL, "") ?: ""
        set(value) = sharedPref.edit { putString(EMAIL, value) }

    fun clearSharedPreference() {
        sharedPref.edit { clear() }
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
