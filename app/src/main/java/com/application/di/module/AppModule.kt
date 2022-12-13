package com.application.di.module

import androidx.lifecycle.ViewModel
import com.application.di.annotation.ViewModelKey
import com.application.newsnow.viewmodel.SectionNewsViewModel
import com.application.newsnow.viewmodel.TopNewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppModule {

    @Binds
    @[IntoMap ViewModelKey(TopNewsViewModel::class)]
    fun bindTopNewsViewModel(viewModel: TopNewsViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(SectionNewsViewModel::class)]
    fun bindSectionNewsViewModel(viewModel: SectionNewsViewModel): ViewModel

}