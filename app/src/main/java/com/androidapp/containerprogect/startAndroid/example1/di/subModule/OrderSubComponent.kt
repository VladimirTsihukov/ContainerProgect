package com.androidapp.containerprogect.startAndroid.example1.di.subModule

import com.androidapp.containerprogect.startAndroid.example1.classes.UiHelper
import com.androidapp.containerprogect.startAndroid.example1.di.OrderScope
import com.androidapp.containerprogect.startAndroid.example1.di.modul.OrderModule
import com.androidapp.containerprogect.startAndroid.order.OrderActivity
import com.androidapp.containerprogect.startAndroid.order.OrderPresenter
import com.androidapp.containerprogect.startAndroid.order.OrderRepository
import dagger.BindsInstance
import dagger.Subcomponent

@OrderScope
@Subcomponent (modules = [OrderModule::class])
interface OrderSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: OrderActivity): OrderSubComponent
    }

    fun getOrderPresenter(): OrderPresenter

    fun getOrderRepository(): OrderRepository

    fun getOrderUiHelper(): UiHelper
}