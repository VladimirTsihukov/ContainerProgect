package com.androidapp.containerprogect.startAndroid.example1.di

import com.androidapp.containerprogect.MainActivity
import com.androidapp.containerprogect.startAndroid.example1.di.modul.*
import dagger.Component

@Component(
    modules = [
        NetWorkUtilsModule::class,
        StorageModule::class,
        MainModule::class,
        ServerModule::class,
        EventModule::class,
        AppModule::class,
    ]
)
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}