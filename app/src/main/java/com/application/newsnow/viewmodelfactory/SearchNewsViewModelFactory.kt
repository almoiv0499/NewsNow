package com.application.newsnow.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.newsnow.domain.usecase.GetSearchedNewsUseCase
import com.application.newsnow.viewmodel.SearchNewsViewModel

class SearchNewsViewModelFactory(
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchNewsViewModel(getSearchedNewsUseCase) as T
    }
}