package com.application.newsnow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.newsnow.domain.usecase.GetSearchedNewsUseCase
import com.application.newsnow.extension.toListNewsView
import com.application.newsnow.fragment.NewsDetailFragment
import com.application.newsnow.model.ListNewsView
import com.application.newsnow.model.NewsView
import com.application.newsnow.viewmodel.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchNewsViewModel(private val getSearchedNewsUseCase: GetSearchedNewsUseCase) : BaseViewModel() {

    private val disposable by lazy { CompositeDisposable() }

    private val _liveDataNews = MutableLiveData<ListNewsView>()
    val liveDataNews: LiveData<ListNewsView> = _liveDataNews

    private val _liveDataException = MutableLiveData<String>()
    val liveDataException: LiveData<String> = _liveDataException

    init {
        fetchNews()
    }

    private fun fetchNews() {
        disposable.add(
            getSearchedNewsUseCase.fetchSearchedNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    _liveDataNews.value = response.toListNewsView()
                }, { throwable ->
                    _liveDataException.value = throwable.message
                })
        )
    }

    fun navigateToDetails(newsView: NewsView) {
        navigate(NewsDetailFragment.getInstance(newsView))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}