package com.application.newsnow.domain.usecase

import com.application.newsnow.domain.model.ListNewsDomain
import com.application.newsnow.domain.repository.FetchNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetListNewsUseCase(private val repository: FetchNewsRepository) {

    suspend fun fetchNews(): ListNewsDomain = withContext(Dispatchers.IO) {
        repository.fetchNews()
    }

}