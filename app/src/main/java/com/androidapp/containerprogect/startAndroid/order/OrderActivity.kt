package com.androidapp.containerprogect.startAndroid.order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.App
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.startAndroid.example1.di.subModule.OrderSubComponent

class OrderActivity : AppCompatActivity() {

    lateinit var orderPresenter1: OrderPresenter
    lateinit var orderPresenter2: OrderPresenter

    lateinit var orderSubComponent: OrderSubComponent

    lateinit var orderRepository1: OrderRepository
    lateinit var orderRepository2: OrderRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        orderSubComponent = App.appComponent.orderSubModule()
        orderPresenter1 = orderSubComponent.getOrderPresenter()
        orderPresenter2 = orderSubComponent.getOrderPresenter()
        orderRepository1 = orderSubComponent.getOrderRepository()
        orderRepository2 = orderSubComponent.getOrderRepository()
    }

    override fun onStart() {
        super.onStart()
        orderPresenter1.getLog()
        orderPresenter2.getLog()
        orderRepository1.getLog()
        orderRepository2.getLog()
    }
}