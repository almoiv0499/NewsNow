package com.application.newsnow.domain.model

data class NewsDomain(
    val publishedAt: String,
    val title: String,
    val description: String,
    val multimedia: List<MultimediaDomain>
)