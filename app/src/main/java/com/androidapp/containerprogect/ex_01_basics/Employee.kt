package com.androidapp.containerprogect.ex_01_basics

data class Employee(
    val id: Long,
    val name: String,
    val address: String? = null,
    var salary: Int,
)