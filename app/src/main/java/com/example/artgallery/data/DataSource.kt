package com.example.artgallery.data

import com.example.artgallery.R
import com.example.artgallery.model.PigCard

object DataSource {
    val Pigs = listOf(
        PigCard(R.drawable.fogpigcompletev2, R.string.fog_pig_heading),
        PigCard(R.drawable.city_pig, R.string.city_pig_heading),
        PigCard(R.drawable.unknownpig, R.string.Unknown_pig_heading)
    )
}