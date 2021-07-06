package com.androidapp.containerprogect.startAndroid.example1.di.modul

import com.androidapp.containerprogect.startAndroid.example1.classes.Repository
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideRepository() = Repository()

/*    @Provides
    fun provideDatabaseHelper(repository: Repository) : DatabaseHelper {
        return DatabaseHelper(repository)
    }*/
}