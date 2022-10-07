package com.application.newsnow.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.newsnow.viewmodel.TopNewsViewModel
import com.application.newsnow.domain.usecase.GetListNewsUseCase

class TopNewsViewModelFactory(
    private val getListNewsUseCase: GetListNewsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TopNewsViewModel(getListNewsUseCase = getListNewsUseCase) as T
    }
}