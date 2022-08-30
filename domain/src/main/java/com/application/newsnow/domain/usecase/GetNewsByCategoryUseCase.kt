package com.application.newsnow.domain.usecase

import com.application.newsnow.domain.model.ListNews
import com.application.newsnow.domain.repository.FetchNewsRepository
import retrofit2.Call

class GetNewsByCategoryUseCase(private val repository: FetchNewsRepository) {

    fun fetchNewsByCategory(category: String): Call<ListNews> =
        repository.fetchNewsByCategory(category = category)

}