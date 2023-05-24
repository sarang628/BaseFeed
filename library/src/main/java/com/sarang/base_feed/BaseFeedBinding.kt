package com.sarang.base_feed

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("formatText")
fun setText(textView: TextView?, str: String?) {
    textView?.setText(Html.fromHtml(str, 0))
}