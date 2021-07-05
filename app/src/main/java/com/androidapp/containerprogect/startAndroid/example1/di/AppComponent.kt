package com.androidapp.containerprogect.startAndroid.example1.di

import com.androidapp.containerprogect.MainActivity
import com.androidapp.containerprogect.startAndroid.example1.di.modul.MainModule
import com.androidapp.containerprogect.startAndroid.example1.di.modul.NetWorkUtils
import com.androidapp.containerprogect.startAndroid.example1.di.modul.StorageModule
import dagger.Component

@Component(
    modules = [
        NetWorkUtils::class,
        StorageModule::class,
        MainModule::class,
    ]
)
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}