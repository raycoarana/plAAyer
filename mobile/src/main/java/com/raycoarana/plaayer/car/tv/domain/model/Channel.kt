package com.raycoarana.plaayer.car.tv.domain.model

import android.support.annotation.DrawableRes

data class Channel constructor(
        val id: Int,
        val title: String,
        val streams: List<Stream>,
        @DrawableRes val logo: Int
)

data class Stream constructor(
        val label: String,
        val url: String
)
