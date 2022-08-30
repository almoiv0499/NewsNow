package com.application.newsnow.domain.usecase

import com.application.newsnow.domain.model.ListNews
import com.application.newsnow.domain.repository.FetchNewsRepository
import io.reactivex.rxjava3.core.Observable

class GetNewsSearchScreenUseCase(private val repository: FetchNewsRepository) {

    fun fetchNewsForSearchScreen(): Observable<ListNews> = repository.fetchNewsForSearchScreen()

}