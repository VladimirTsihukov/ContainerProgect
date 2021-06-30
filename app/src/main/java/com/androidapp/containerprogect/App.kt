package com.androidapp.containerprogect

import android.app.Application
import com.androidapp.containerprogect.startAndroid.example1.di.AppComponent
import com.androidapp.containerprogect.startAndroid.example1.di.DaggerAppComponent

class App : Application(){

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}