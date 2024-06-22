package net.asgeri.atlproject7.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import net.asgeri.atlproject7.utils.loaImageUrl

@BindingAdapter("load_image_local")
fun setImageSrc(imageView: ImageView, resId: Int) {
    imageView.setImageResource(resId)
}

@BindingAdapter("load_image_url")
fun setImageUrl(imageView: ImageView, url: String) {
    imageView.loaImageUrl(url)
}