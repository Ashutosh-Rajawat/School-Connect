package com.atr.schoolconnect.presentation.utilities

import android.Manifest
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresPermission
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Objects
import java.util.regex.Pattern

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

        @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val activeNetwork = connectivityManager.activeNetwork ?: return false
                val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
                return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                return activeNetworkInfo != null && activeNetworkInfo.isConnected
            }
        }
        /**
         * validate your email address format. Ex-a@gmail.com
         */
        fun emailValidator(email: String): Boolean {
            val pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
            return pattern.matcher(email).matches()
        }
/*
        *//**
         * Show progress dialog for API loading
         *//*
        fun showLoader() {
            dimmer?.visibility = View.VISIBLE

            dialog?.setContentView(R.layout.progress_dialog)
            dialog?.setCancelable(false)

            animationView =
                dialog?.findViewById(R.id.loading_bar)

            animationView?.setAnimation(R.raw.loader)

            animationView?.playAnimation()
            val window: Window? = dialog?.window
            val layoutParams = Objects.requireNonNull(window)?.attributes
            layoutParams?.gravity = Gravity.CENTER
            if (window != null) {
                window.attributes = layoutParams
            }
            dialog?.setCanceledOnTouchOutside(false)
            dialog?.window?.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (dialog != null && dialog!!.isShowing) {
                dialog?.dismiss()
            }

            dialog?.show()
        }
        *//**
         * Dismiss progress dialog for API loading
         *//*
        fun dismissLoader() {
            try {
                if (dialog != null && dialog!!.isShowing) {
                    animationView?.cancelAnimation()
                    dialog?.dismiss()
                }
            } catch (e: Exception) {
                animationView?.cancelAnimation()
                dialog?.dismiss()
            }
        }*/
    }


}