package com.androidapp.containerprogect.startAndroid.example1.di

import com.androidapp.containerprogect.MainActivity
import com.androidapp.containerprogect.startAndroid.example1.di.modul.*
import com.androidapp.containerprogect.startAndroid.example1.di.subModule.MainSubComponent
import dagger.Component

@Component(
    modules = [
        NetWorkUtilsModule::class,
        StorageModule::class,
        ServerModule::class,
        EventModule::class,
        AppModule::class,
    ]
)

interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)

    fun mainSubModule() : MainSubComponent.Builder
}