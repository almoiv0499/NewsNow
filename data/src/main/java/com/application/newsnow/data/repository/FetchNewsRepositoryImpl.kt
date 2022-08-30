package com.application.newsnow.data.repository

import com.application.newsnow.data.retrofit.Api
import com.application.newsnow.domain.model.ListNews
import com.application.newsnow.domain.repository.FetchNewsRepository
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call

class FetchNewsRepositoryImpl(private val api: Api) : FetchNewsRepository {

    override suspend fun fetchNews(): ListNews = api.getNewsForTopNewsScreen()

    override fun fetchNewsByCategory(category: String): Call<ListNews> =
        api.getNewsByCategory(category = category)

    override fun fetchNewsForSearchScreen(): Observable<ListNews> = api.getNewsForSearchScreen()

}