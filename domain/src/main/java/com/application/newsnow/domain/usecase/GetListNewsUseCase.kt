package com.application.newsnow.domain.usecase

import com.application.newsnow.domain.model.ListNewsDomain
import com.application.newsnow.domain.repository.FetchNewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetListNewsUseCase @Inject constructor(
    private val repository: FetchNewsRepository,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchNews(): ListNewsDomain = withContext(ioDispatcher) {
        repository.fetchNews()
    }
}