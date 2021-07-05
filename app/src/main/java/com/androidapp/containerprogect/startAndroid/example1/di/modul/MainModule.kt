package com.androidapp.containerprogect.startAndroid.example1.di.modul

import com.androidapp.containerprogect.startAndroid.example1.classes.DatabaseHelper
import com.androidapp.containerprogect.startAndroid.example1.classes.NetWorkUtils
import com.androidapp.containerprogect.startAndroid.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainPresenter(databaseHelper: DatabaseHelper, netWorkUtils: NetWorkUtils) : MainPresenter {
        return MainPresenter(databaseHelper, netWorkUtils)
    }

}