package com.leonardojpr.frytest.utils

data class SpannableModelText(
    val text: String,
    val underline: Boolean = false,
    val clickable: Boolean = false,
    val isBold: Boolean = false,
    val action: Int = -1,
)