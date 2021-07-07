package com.androidapp.containerprogect.startAndroid.example1.di.modul

import android.content.Context
import com.androidapp.containerprogect.startAndroid.example1.classes.NetWorkUtils
import com.androidapp.containerprogect.startAndroid.example1.classes.UiHelper
import com.androidapp.containerprogect.startAndroid.example1.di.OrderScope
import com.androidapp.containerprogect.startAndroid.order.OrderActivity
import com.androidapp.containerprogect.startAndroid.order.OrderPresenter
import dagger.Module
import dagger.Provides

@Module
class OrderModule {

    @OrderScope
    @Provides
    fun provideOrderPresenter(
        context: Context,
        netWorkUtils: NetWorkUtils
    ): OrderPresenter {
        return OrderPresenter(context, netWorkUtils)
    }

    @OrderScope
    @Provides
    fun provideOrderUiHelper(activity: OrderActivity): UiHelper {
        return UiHelper(activity)
    }
 }