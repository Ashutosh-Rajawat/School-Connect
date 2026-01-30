package com.atr.schoolconnect.data.controller

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.atr.schoolconnect.R

class Rest {
    var ctx: Context? = null
    var dialog: Dialog? = null
    var animationView: LottieAnimationView? = null

    constructor(context: Context?) {
        this.ctx = context
        init()
    }

    private fun init() {
        ctx?.let {
            dialog = Dialog(it)
            animationView = LottieAnimationView(it)
            dialog?.setCancelable(false)
        }
    }

    fun showDialogue() {
        dialog?.setContentView(R.layout.progress_dialog)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)

        animationView = dialog?.findViewById(R.id.loading_bar)
        animationView?.setAnimation(R.raw.loader)
        animationView?.playAnimation()

        val window = dialog?.window ?: return
        val params = window.attributes
        params.gravity = Gravity.CENTER
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        window.attributes = params

        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog?.show()
    }


    fun dismissProgressDialog() {
        try {
            if (dialog?.isShowing == true) {
                animationView?.cancelAnimation()
                dialog?.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
