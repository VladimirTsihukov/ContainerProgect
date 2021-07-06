package com.androidapp.containerprogect.startAndroid.example1.di.subModule

import com.androidapp.containerprogect.startAndroid.example1.di.modul.MainModule
import com.androidapp.containerprogect.startAndroid.presenter.MainPresenter
import dagger.Subcomponent

@Subcomponent (modules = [
    MainModule::class,
])
interface MainSubComponent {

    fun getMainActivityPresenter() : MainPresenter
}