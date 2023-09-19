package com.androidapp.containerprogect.testing

import kotlinx.coroutines.CoroutineScope

val CoroutineScope.threadName
    get() = Thread.currentThread().name ?: "Null"

const val bomb = "\uD83D\uDCA3"
const val candy = "\uD83C\uDF6C"
const val success = "✅"

fun now(): Long = System.currentTimeMillis()
val Long.passed: Long
    get() = System.currentTimeMillis() - this
