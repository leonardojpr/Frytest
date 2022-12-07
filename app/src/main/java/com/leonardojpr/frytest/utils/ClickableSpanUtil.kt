package com.leonardojpr.frytest.utils

import android.os.Build
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import com.leonardojpr.frytest.R
import com.leonardojpr.frytest.TestApp

class ClickableSpanUtil(
    private val action: Int,
    private val onClick: ((event: Int) -> Unit)? = null,
) :
    ClickableSpan() {

    override fun onClick(view: View) {
        onClick?.invoke(action)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = true
        ds.color = getColorForContext(R.color.teal_200)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ds.underlineColor = getColorForContext(R.color.teal_200)
        }
    }

    fun getColorForContext(color: Int): Int = TestApp.appContext.getColor(color)
}