package com.example.sellhousesinfo.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sellhousesinfo.R

fun ImageView.setImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(context).load(url).placeholder(R.drawable.ic_baseline_refresh_24).error(R.drawable.ic_baseline_refresh_24)
            .into(this)
    } else {
        setImageResource(R.drawable.ic_baseline_refresh_24)
    }
}