package com.application.newsnow.viewmodel

import androidx.lifecycle.*
import com.application.newsnow.domain.usecase.GetListNewsUseCase
import com.application.newsnow.extension.toListNewsView
import com.application.newsnow.fragment.NewsDetailFragment
import com.application.newsnow.model.ListNewsView
import com.application.newsnow.model.NewsView
import com.application.newsnow.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class TopNewsViewModel @Inject constructor(
    private val getListNewsUseCase: GetListNewsUseCase
) : BaseViewModel() {

    private val _liveDataNews = MutableLiveData<ListNewsView>()
    val liveDataNews: LiveData<ListNewsView> = _liveDataNews

    private val _liveDataException = MutableLiveData<String>()
    val liveDataException: LiveData<String> = _liveDataException

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                _liveDataNews.value = getListNewsUseCase.fetchNews().toListNewsView()
            } catch (exception: HttpException) {
                _liveDataException.value = exception.message
            }
        }
    }

    fun navigateToDetails(newsView: NewsView) {
        navigate(NewsDetailFragment.getInstance(newsView))
    }

}