package com.application.app

import android.app.Application
import com.application.di.component.AppComponent
import com.application.di.component.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
    }

}