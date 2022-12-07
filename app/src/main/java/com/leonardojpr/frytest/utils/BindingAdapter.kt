package com.leonardojpr.frytest.utils

import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.leonardojpr.frytest.ui.home.adapter.MealsAdapter


@BindingAdapter("mutableLoadImage")
fun setMutableLoadImage(view: ImageView, url: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && view != null && url != null) {
        view.load(url)
    }
}

@BindingAdapter("mutableRvAdapter")
fun setMutableRvAdapter(view: RecyclerView, adapter: MealsAdapter) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && view != null) {
        if (view.adapter == null) {
            view.adapter = adapter
        }
    }
}

@BindingAdapter("mutableOpenLink")
fun setMutableOpenLink(view: TextView, url: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && !url.isNullOrBlank()) {
        val content = SpannableString(url)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        view.text = content
        view.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            parentActivity.startActivity(browserIntent)
        }
    }
}

