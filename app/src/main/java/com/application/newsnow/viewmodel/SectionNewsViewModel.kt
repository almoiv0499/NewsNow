package com.application.newsnow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.newsnow.domain.usecase.GetNewsByCategoryUseCase
import com.application.newsnow.extension.toListNewsView
import com.application.newsnow.model.ListNewsView
import com.application.newsnow.model.Section
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SectionNewsViewModel(
    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase
) : ViewModel() {

    private val _news = MutableLiveData<ListNewsView>()
    val news: LiveData<ListNewsView> = _news

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchNewsByCategory(section: Section) {
        viewModelScope.launch {
            try {
                _news.value =
                    getNewsByCategoryUseCase.getNewsByCategory(section.section).toListNewsView()

            } catch (exception: HttpException) {
                _error.postValue(exception.message())
            }
        }
    }

}