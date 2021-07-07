package com.androidapp.containerprogect.startAndroid.order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.App
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.startAndroid.example1.di.subModule.OrderSubComponent

class OrderActivity : AppCompatActivity() {

    lateinit var orderPresenter: OrderPresenter

    lateinit var orderSubComponent: OrderSubComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        orderSubComponent = App.appComponent.orderSubModule()
        orderPresenter = orderSubComponent.getOrderPresenter()
    }

    override fun onStart() {
        super.onStart()
        orderPresenter.getLog()
    }
}