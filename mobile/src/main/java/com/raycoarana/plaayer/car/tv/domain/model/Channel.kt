package com.raycoarana.plaayer.car.tv.domain.model

import android.support.annotation.DrawableRes

data class Channel constructor(
        val id: Int,
        val title: String,
        val streamUrl: String,
        @DrawableRes val logo: Int
)
