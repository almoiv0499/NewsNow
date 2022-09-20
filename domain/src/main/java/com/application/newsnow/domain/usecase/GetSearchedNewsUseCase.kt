package com.application.newsnow.domain.usecase

import com.application.newsnow.domain.model.ListNewsDomain
import com.application.newsnow.domain.repository.FetchNewsRepository
import io.reactivex.rxjava3.core.Observable

class GetSearchedNewsUseCase(private val repository: FetchNewsRepository) {

    fun fetchSearchedNews(): Observable<ListNewsDomain> = repository.fetchSearchedNews()

}