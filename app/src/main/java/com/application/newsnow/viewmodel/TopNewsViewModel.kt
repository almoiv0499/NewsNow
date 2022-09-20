package com.application.newsnow.viewmodel

import androidx.lifecycle.*
import com.application.newsnow.domain.usecase.GetListNewsUseCase
import com.application.newsnow.extension.toListNewsView
import com.application.newsnow.model.ListNewsView
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TopNewsViewModel(private val getListNewsUseCase: GetListNewsUseCase) : ViewModel() {

    private val _news = MutableLiveData<ListNewsView>()
    val news: LiveData<ListNewsView> = _news

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                _news.value = getListNewsUseCase.fetchNews().toListNewsView()
            } catch (exception: HttpException) {
                _error.postValue(exception.message)
            }
        }
    }

}