package net.asgeri.atlproject7.model

import androidx.annotation.DrawableRes

data class BrandModel(
    @DrawableRes val image: Int,
    val title: String
)