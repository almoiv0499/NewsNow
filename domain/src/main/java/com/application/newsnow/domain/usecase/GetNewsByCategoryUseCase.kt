package com.application.newsnow.domain.usecase

import com.application.newsnow.domain.model.ListNewsDomain
import com.application.newsnow.domain.repository.FetchNewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNewsByCategoryUseCase @Inject constructor(
    private val repository: FetchNewsRepository,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getNewsByCategory(category: String): ListNewsDomain = withContext(ioDispatcher) {
        repository.getNewsByCategory(category = category)
    }
}

