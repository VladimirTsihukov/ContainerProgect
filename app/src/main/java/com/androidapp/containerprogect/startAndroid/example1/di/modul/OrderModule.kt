package com.androidapp.containerprogect.startAndroid.example1.di.modul

import android.content.Context
import com.androidapp.containerprogect.startAndroid.example1.classes.NetWorkUtils
import com.androidapp.containerprogect.startAndroid.order.OrderPresenter
import dagger.Module
import dagger.Provides

@Module
class OrderModule {

    @Provides
    fun provideOrderPresenter(
        context: Context,
        netWorkUtils: NetWorkUtils
    ): OrderPresenter {
        return OrderPresenter(context, netWorkUtils)
    }
}