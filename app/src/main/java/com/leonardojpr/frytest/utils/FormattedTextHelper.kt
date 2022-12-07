package com.leonardojpr.frytest.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan

object FormattedTextHelper {

    fun setCustomText(
        specifications: List<SpannableModelText>,
        onClick: ((event: Int) -> Unit)? = null,
    ): SpannableStringBuilder {

        val builder = SpannableStringBuilder()

        for (specification in specifications) {
            setSpecificationsText(
                builder,
                specification.text,
                specification.underline,
                specification.clickable,
                specification.isBold,
                specification.action,
                onClick = onClick
            )
            builder.append(" ")
        }

        return builder
    }

    private fun setSpecificationsText(
        builder: SpannableStringBuilder,
        text: String,
        underline: Boolean,
        clickable: Boolean,
        isBold: Boolean,
        action: Int,
        onClick: ((event: Int) -> Unit)? = null,
    ) {
        SpannableString(text).apply {
            if (underline) setSpan(UnderlineSpan(), 0, this.length, 0)
            if (isBold) setSpan(StyleSpan(Typeface.BOLD), 0, this.length, 0)
            if (clickable) setSpan(
                ClickableSpanUtil(action, onClick),
                0,
                this.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            builder.append(this)
        }
    }
}