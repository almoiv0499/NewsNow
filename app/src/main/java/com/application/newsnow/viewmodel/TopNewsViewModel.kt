package com.application.newsnow.viewmodel

import androidx.lifecycle.*
import com.application.newsnow.domain.model.ListNews
import com.application.newsnow.domain.usecase.GetListNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TopNewsViewModel(private val getListNewsUseCase: GetListNewsUseCase) : ViewModel() {

    private val _news = MutableLiveData<ListNews>()
    val news: LiveData<ListNews> = _news

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    _news.postValue(getListNewsUseCase.fetchNews())
                }
            } catch (exception: HttpException) {
                _error.postValue(exception.message)
            }
        }
    }

}