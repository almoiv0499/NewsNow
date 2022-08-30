package com.application.newsnow.domain.repository

import com.application.newsnow.domain.model.ListNews
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call

interface FetchNewsRepository {

    suspend fun fetchNews(): ListNews

    fun fetchNewsByCategory(category: String): Call<ListNews>

    fun fetchNewsForSearchScreen(): Observable<ListNews>

}