package com.androidapp.containerprogect.startAndroid.example1.di.modul

import com.androidapp.containerprogect.startAndroid.example1.classes.Analytics
import com.androidapp.containerprogect.startAndroid.example1.classes.EventHandler
import com.androidapp.containerprogect.startAndroid.example1.classes.Logger
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class EventModule {

    @IntoSet
    @Provides
    fun provideAnalytics() : EventHandler {
        return Analytics()
    }

    @IntoSet
    @Provides
    fun provideLogger() : EventHandler {
        return Logger()
    }
}