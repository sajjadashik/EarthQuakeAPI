package com.sajjad.earthquakeapi.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sajjad.earthquakeapi.network.getFormattedDate

@BindingAdapter("app:setDateTime")
fun setDateTime(textView: TextView, dt: Long) {
    textView.text = getFormattedDate(dt, "MMM dd, hh:mm a")
}