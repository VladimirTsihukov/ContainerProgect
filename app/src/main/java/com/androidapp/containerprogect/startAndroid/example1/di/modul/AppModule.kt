package com.androidapp.containerprogect.startAndroid.example1.di.modul

import com.androidapp.containerprogect.App
import dagger.Module
import dagger.Provides

@Module
class AppModule (private val app: App) {

    @Provides
    fun provideContext(): App {
        return app
    }
}