package com.androidapp.containerprogect.startAndroid.example1.di.modul

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.androidapp.containerprogect.App
import com.androidapp.containerprogect.startAndroid.example1.classes.DatabaseHelper
import com.androidapp.containerprogect.startAndroid.example1.classes.Repository
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideRepository() = Repository()

    @Provides
    fun provideDatabaseHelper(repository: Repository) : DatabaseHelper {
        return DatabaseHelper(repository)
    }

    @Provides
    fun getPreference(app: App) : SharedPreferences {
        return app.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }

    @Provides
    fun getResources(app: App): Resources {
        return app.resources
    }
}