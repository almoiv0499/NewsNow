package com.application.newsnow.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.newsnow.domain.usecase.GetNewsByCategoryUseCase
import com.application.newsnow.viewmodel.SectionNewsViewModel

class SectionNewsViewModelFactory(
    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SectionNewsViewModel(getNewsByCategoryUseCase) as T
    }
}