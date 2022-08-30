package com.application.newsnow.domain.usecase

import com.application.newsnow.domain.model.ListNews
import com.application.newsnow.domain.repository.FetchNewsRepository

class GetListNewsUseCase(private val repository: FetchNewsRepository) {

    suspend fun fetchNews(): ListNews = repository.fetchNews()

}