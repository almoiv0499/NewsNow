package com.application.newsnow.viewmodel.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.newsnow.util.Event
import com.application.newsnow.util.Navigation

abstract class BaseViewModel : ViewModel() {

    private val _liveDataNavigation = MutableLiveData<Event<Navigation>>()
    val liveDataNavigation: LiveData<Event<Navigation>> = _liveDataNavigation

    fun navigate(fragment: Fragment) {
        _liveDataNavigation.value = Event(Navigation.ToFragment(fragment))
    }

}