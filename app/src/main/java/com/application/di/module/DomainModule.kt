package com.application.di.module

import com.application.newsnow.domain.repository.FetchNewsRepository
import com.application.newsnow.domain.usecase.GetListNewsUseCase
import com.application.newsnow.domain.usecase.GetNewsByCategoryUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DomainModule {

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideGetListNewsUseCase(
        repository: FetchNewsRepository,
        dispatcher: CoroutineDispatcher
    ) = GetListNewsUseCase(repository = repository, ioDispatcher = dispatcher)

    @Provides
    fun provideGetNewsByCategoryUseCase(
        repository: FetchNewsRepository,
        dispatcher: CoroutineDispatcher
    ) = GetNewsByCategoryUseCase(repository = repository, ioDispatcher = dispatcher)

}