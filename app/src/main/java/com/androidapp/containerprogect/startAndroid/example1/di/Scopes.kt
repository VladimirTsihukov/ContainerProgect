package com.androidapp.containerprogect.startAndroid.example1.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope