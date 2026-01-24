package com.atr.schoolconnect.presentation.utilities

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.atr.schoolconnect.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.ContentViewCallback

class CustomSnackBarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyle: Int = 0
) : LinearLayout(context, attributeSet, defaultStyle), ContentViewCallback {

    init {
        inflate(context, R.layout.item_custom_snackbar, this)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        // TODO("Use some animation")
    }

    override fun animateContentOut(delay: Int, duration: Int) {
        // TODO("Use some animation")
    }
}



class CustomSnackbar(
    parent: ViewGroup,
    content: CustomSnackBarView
) : BaseTransientBottomBar<CustomSnackbar>(parent, content, content) {

    init {
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {

        fun make(viewGroup: ViewGroup, message: String): CustomSnackbar {
            val customView = LayoutInflater.from(viewGroup.context).inflate(
                R.layout.layout_custom_snackbar,
                viewGroup,
                false
            ) as CustomSnackBarView

            customView.findViewById<TextView>(R.id.tvMessage).text = message

            return CustomSnackbar(viewGroup, customView)
        }

    }

}