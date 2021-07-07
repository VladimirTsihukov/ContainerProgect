package com.androidapp.containerprogect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.containerprogect.startAndroid.example1.classes.DatabaseHelper
import com.androidapp.containerprogect.startAndroid.example1.classes.NetWorkUtils
import com.androidapp.containerprogect.startAndroid.example1.classes.ServerApi
import com.androidapp.containerprogect.startAndroid.example1.classes.UiHelper
import com.androidapp.containerprogect.startAndroid.example1.di.Dev
import com.androidapp.containerprogect.startAndroid.example1.di.Prod
import com.androidapp.containerprogect.startAndroid.example1.di.subModule.MainSubComponent
import com.androidapp.containerprogect.startAndroid.order.OrderActivity
import com.androidapp.containerprogect.startAndroid.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    @Inject
    lateinit var netWorkUtils: NetWorkUtils

    lateinit var mainPresenter: MainPresenter

/*    @field:[Inject Named("prod")]
    lateinit var serverApiProv: ServerApi

    @field:[Inject Named("dev")]
    lateinit var serverApiDev: ServerApi*/

    @field:[Inject Prod]
    lateinit var serverApiProv: ServerApi

    @field:[Inject Dev]
    lateinit var serverApiDev: ServerApi

    lateinit var mainSubComponent: MainSubComponent

    lateinit var uiHelper: UiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.injectMainActivity(this)

     /*   mainSubComponent = App.appComponent.mainSubModule()
            .activity(this)
            .build()*/

        mainSubComponent = App.appComponent.mainSubModuleFactory().create(this)

        mainPresenter = mainSubComponent.getMainActivityPresenter()

        uiHelper = mainSubComponent.getMainUiHelper()

        initView()
    }

    private fun initView() {
        button.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        /*databaseHelper.getLog()
        netWorkUtils.getLog()
        mainPresenter.getLog()
        serverApiProv.getApi()
        serverApiDev.getApi()*/

        uiHelper.getLog()
    }
}