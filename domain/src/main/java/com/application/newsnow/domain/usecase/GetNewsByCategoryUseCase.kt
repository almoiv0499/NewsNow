package com.application.newsnow.domain.usecase

import com.application.newsnow.domain.repository.FetchNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNewsByCategoryUseCase(private val repository: FetchNewsRepository) {

    suspend fun getNewsByCategory(category: String) = withContext(Dispatchers.IO) {
        repository.getNewsByCategory(category = category)
    }

}

