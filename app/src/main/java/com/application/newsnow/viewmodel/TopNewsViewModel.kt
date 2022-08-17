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
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    _news.postValue(RetrofitInstance.getInstance().api.getNewsForTopNewsScreen())
                }
            } catch (exception: HttpException) {
                _error.postValue(exception.message)
            }
        }
    }

}