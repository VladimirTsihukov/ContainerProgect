package com.androidapp.containerprogect.startAndroid.example1.di.modul

import com.androidapp.containerprogect.startAndroid.example1.classes.ConnectionManager
import com.androidapp.containerprogect.startAndroid.example1.classes.NetWorkUtils
import dagger.Module
import dagger.Provides

@Module
class NetWorkUtils {

    @Provides
    fun provideConnectionManager() = ConnectionManager()

    @Provides
    fun provideNetWorkUtils(connectionManager: ConnectionManager) : NetWorkUtils {
        return NetWorkUtils(connectionManager)
    }
}