package com.androidapp.containerprogect.startAndroid.example1.di.modul

import com.androidapp.containerprogect.startAndroid.example1.classes.ServerApi
import com.androidapp.containerprogect.startAndroid.example1.di.Dev
import com.androidapp.containerprogect.startAndroid.example1.di.Prod
import dagger.Module
import dagger.Provides

@Module
class ServerModule {

/*    @Named("prod")
    @Provides
    fun provideServerApiProd(): ServerApi {
        return ServerApi("prod.server.com")
    }

    @Named("dev")
    @Provides
    fun provideServerApiDev(): ServerApi {
        return ServerApi("dev.server.com")
    }*/

    @Prod
    @Provides
    fun provideServerApiProd(): ServerApi {
        return ServerApi("prod.server.com")
    }

    @Dev
    @Provides
    fun provideServerApiDev(): ServerApi {
        return ServerApi("dev.server.com")
    }

}