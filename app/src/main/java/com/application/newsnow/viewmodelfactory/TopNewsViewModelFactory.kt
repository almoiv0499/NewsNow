package com.application.newsnow.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.newsnow.viewmodel.TopNewsViewModel
import com.application.newsnow.data.repository.FetchNewsRepositoryImpl
import com.application.newsnow.data.retrofit.RetrofitInstance
import com.application.newsnow.domain.usecase.GetListNewsUseCase

class TopNewsViewModelFactory : ViewModelProvider.Factory {

    private val getRepository by lazy {
        FetchNewsRepositoryImpl(api = RetrofitInstance.getInstance().api)
    }

    private val getListNewsUseCase by lazy {
        GetListNewsUseCase(repository = getRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TopNewsViewModel(getListNewsUseCase = getListNewsUseCase) as T
    }
}