package com.example.artgallery.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PigCard(
    @DrawableRes val imageResourceId: Int,
    @StringRes val stringResourceId: Int
)
