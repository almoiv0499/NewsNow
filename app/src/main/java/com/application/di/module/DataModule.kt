package com.application.di.module

import com.application.newsnow.data.repository.FetchNewsRepositoryImpl
import com.application.newsnow.data.retrofit.Api
import com.application.newsnow.data.retrofit.RetrofitInstance
import com.application.newsnow.domain.repository.FetchNewsRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideApi(): Api = RetrofitInstance.getInstance().api

    @Provides
    fun provideFetchNewsRepository(api: Api): FetchNewsRepository = FetchNewsRepositoryImpl(api = api)

}