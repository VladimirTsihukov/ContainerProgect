package com.androidapp.containerprogect.startAndroid.example1.di.subModule

import com.androidapp.containerprogect.startAndroid.example1.di.modul.OrderModule
import com.androidapp.containerprogect.startAndroid.order.OrderPresenter
import dagger.Subcomponent

@Subcomponent (modules = [OrderModule::class])
interface OrderSubComponent {

    fun getOrderPresenter(): OrderPresenter
}