package com.application.newsnow.extension

import com.application.newsnow.domain.model.ListNewsDomain
import com.application.newsnow.model.ListNewsView
import com.application.newsnow.model.MultimediaView
import com.application.newsnow.model.NewsView

fun ListNewsDomain.toListNewsView(): ListNewsView = ListNewsView(
    results = results.map { newsDomain ->
        NewsView(
            publishedAt = newsDomain.publishedAt,
            title = newsDomain.title,
            description = newsDomain.description,
            multimedia = newsDomain.multimedia.map { multimediaDomain ->
                MultimediaView(
                    image = multimediaDomain.image,
                    author = multimediaDomain.author
                )
            }
        )
    }
)