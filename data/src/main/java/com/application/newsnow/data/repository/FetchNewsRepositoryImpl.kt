package com.application.newsnow.data.repository

import com.application.newsnow.data.extension.toListNewsDomain
import com.application.newsnow.data.retrofit.Api
import com.application.newsnow.domain.model.ListNewsDomain
import com.application.newsnow.domain.repository.FetchNewsRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FetchNewsRepositoryImpl @Inject constructor(private val api: Api) : FetchNewsRepository {

    override suspend fun fetchNews(): ListNewsDomain =
        api.getNewsForTopNewsScreen().toListNewsDomain()

    override fun fetchSearchedNews(): Observable<ListNewsDomain> =
        api.getNewsForSearchScreen().map {
            it.toListNewsDomain()
        }

    override suspend fun getNewsByCategory(category: String): ListNewsDomain =
        api.getNewsByCategory(category = category).toListNewsDomain()

}