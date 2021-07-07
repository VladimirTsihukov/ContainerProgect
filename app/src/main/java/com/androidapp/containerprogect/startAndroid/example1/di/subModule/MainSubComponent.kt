package com.androidapp.containerprogect.startAndroid.example1.di.subModule

import com.androidapp.containerprogect.MainActivity
import com.androidapp.containerprogect.startAndroid.example1.classes.UiHelper
import com.androidapp.containerprogect.startAndroid.example1.di.MainScope
import com.androidapp.containerprogect.startAndroid.example1.di.modul.MainModule
import com.androidapp.containerprogect.startAndroid.presenter.MainPresenter
import dagger.BindsInstance
import dagger.Subcomponent


@MainScope
@Subcomponent (modules = [
    MainModule::class,
])
interface MainSubComponent {

/*    @Subcomponent.Builder
    interface Builder {
        @BindsInstance fun activity(activity: MainActivity): Builder
        fun build(): MainSubComponent
    }*/

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: MainActivity): MainSubComponent
    }

    fun getMainActivityPresenter() : MainPresenter

    fun getMainUiHelper(): UiHelper
}