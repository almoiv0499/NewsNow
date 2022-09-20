package com.application.newsnow.model

import java.io.Serializable

data class NewsView(
    val publishedAt: String,
    val title: String,
    val description: String,
    val multimedia: List<MultimediaView>
) : Serializable