package com.application.newsnow.data.repository

import com.application.newsnow.data.retrofit.Api
import com.application.newsnow.domain.model.ListNews
import com.application.newsnow.domain.repository.FetchNewsRepository

class FetchNewsRepositoryImpl(private val api: Api) : FetchNewsRepository {

    override suspend fun fetchNews(): ListNews = api.getNewsForTopNewsScreen()

}