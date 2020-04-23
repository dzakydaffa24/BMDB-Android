package com.example.bmdb

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("showImage")
fun showImage(imageView: ImageView, url: String?) {
    Picasso.get().load("http://10.0.2.2:8000/api/movies/image/$url").into(imageView)
}

@BindingAdapter("loadStatus")
fun loadStatus(textView: TextView, status: String?) {
    if (status == "OK") {
        textView.visibility = View.GONE
    }
}