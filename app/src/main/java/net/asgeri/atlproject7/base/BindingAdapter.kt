package net.asgeri.atlproject7.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("load_image_local")
fun setImageSrc(imageView: ImageView, resId: Int) {
    imageView.setImageResource(resId)
}