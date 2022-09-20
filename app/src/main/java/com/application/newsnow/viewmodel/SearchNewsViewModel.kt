package com.application.newsnow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.newsnow.domain.usecase.GetSearchedNewsUseCase
import com.application.newsnow.extension.toListNewsView
import com.application.newsnow.model.ListNewsView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchNewsViewModel(private val getSearchedNewsUseCase: GetSearchedNewsUseCase) : ViewModel() {

    private val disposable by lazy { CompositeDisposable() }

    private val _searchedNews = MutableLiveData<ListNewsView>()
    val searchedNews: LiveData<ListNewsView> = _searchedNews

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchSearchedNews()
    }

    private fun fetchSearchedNews() {
        disposable.add(
            getSearchedNewsUseCase.fetchSearchedNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    _searchedNews.postValue(response.toListNewsView())
                }, { throwable ->
                    _error.postValue(throwable.message)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}