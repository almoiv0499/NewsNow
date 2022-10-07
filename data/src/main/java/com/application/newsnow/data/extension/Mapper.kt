package com.application.newsnow.data.extension

import com.application.newsnow.data.model.ListNews
import com.application.newsnow.domain.model.ListNewsDomain
import com.application.newsnow.domain.model.MultimediaDomain
import com.application.newsnow.domain.model.NewsDomain

fun ListNews.toListNewsDomain(): ListNewsDomain = ListNewsDomain(
    results = results.map { news ->
        NewsDomain(
            publishedAt = news.publishedAt,
            title = news.title,
            description = news.description,
            multimedia = news.multimedia.map { multimedia ->
                MultimediaDomain(
                    image = multimedia.image,
                    author = multimedia.author
                )
            }
        )
    }
)