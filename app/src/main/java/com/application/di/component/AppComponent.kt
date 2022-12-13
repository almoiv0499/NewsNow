package com.application.di.component

import com.application.di.annotation.AppScope
import com.application.di.module.AppModule
import com.application.di.module.DataModule
import com.application.di.module.DomainModule
import com.application.newsnow.fragment.SectionNewsFeedFragment
import com.application.newsnow.fragment.TopNewsFragment
import dagger.Component

@AppScope
@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {

    fun inject(fragment: TopNewsFragment)

    fun inject(fragment: SectionNewsFeedFragment)

}