package com.application.newsnow.viewmodel

import androidx.lifecycle.*
import com.application.newsnow.model.ListNews
import com.application.newsnow.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TopNewsViewModel : ViewModel() {

    private val _news = MutableLiveData<ListNews>()
    val news: LiveData<ListNews> = _news

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        viewModelScope.launch {
            fetchNews()
        }
    }

    private suspend fun fetchNews() = withContext(Dispatchers.IO) {
        try {
            _news.postValue(RetrofitInstance.getInstance().api.getNewsForTopNewsScreen())
        } catch (exception: HttpException) {
            _error.postValue(exception.message)
        }
    }

}