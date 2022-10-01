package com.application.newsnow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.newsnow.domain.usecase.GetNewsByCategoryUseCase
import com.application.newsnow.extension.toListNewsView
import com.application.newsnow.fragment.NewsDetailFragment
import com.application.newsnow.model.ListNewsView
import com.application.newsnow.model.NewsView
import com.application.newsnow.model.Section
import com.application.newsnow.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SectionNewsViewModel(
    private val getNewsByCategoryUseCase: GetNewsByCategoryUseCase
) : BaseViewModel() {

    private val _liveDataNews = MutableLiveData<ListNewsView>()
    val liveDataNews: LiveData<ListNewsView> = _liveDataNews

    private val _liveDataException = MutableLiveData<String>()
    val liveDataException: LiveData<String> = _liveDataException

    fun fetchNewsByCategory(section: Section) {
        viewModelScope.launch {
            try {
                _liveDataNews.value =
                    getNewsByCategoryUseCase.getNewsByCategory(section.section).toListNewsView()

            } catch (exception: HttpException) {
                _liveDataException.value = exception.message
            }
        }
    }

    fun navigateToDetails(newsView: NewsView) {
        navigate(NewsDetailFragment.getInstance(newsView))
    }

}