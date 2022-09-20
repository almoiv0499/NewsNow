package com.application.newsnow.domain.repository

import com.application.newsnow.domain.model.ListNewsDomain
import io.reactivex.rxjava3.core.Observable

interface FetchNewsRepository {

    suspend fun fetchNews(): ListNewsDomain

    fun fetchSearchedNews(): Observable<ListNewsDomain>

    suspend fun getNewsByCategory(category: String): ListNewsDomain

}