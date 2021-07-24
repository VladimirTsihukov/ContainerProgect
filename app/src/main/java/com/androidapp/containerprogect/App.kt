package com.androidapp.containerprogect

import android.app.Application
import com.androidapp.containerprogect.room.AppDataBase

class App : Application() {

    companion object {
        lateinit var db: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()

        db = AppDataBase.instance(this)
    }
}