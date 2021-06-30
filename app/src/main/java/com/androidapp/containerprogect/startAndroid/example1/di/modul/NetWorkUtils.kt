package com.androidapp.containerprogect.startAndroid.example1.di.modul

import com.androidapp.containerprogect.startAndroid.example1.NetWorkUtils
import dagger.Module
import dagger.Provides

@Module
class NetWorkUtils {

    @Provides
    fun provideNetWorkUtils() : NetWorkUtils {
        return NetWorkUtils()
    }
}