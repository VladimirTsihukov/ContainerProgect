package com.androidapp.containerprogect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.recycler.PlanetsAdapter
import com.androidapp.containerprogect.recycler.data.DataMars

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private val adapter by lazy { PlanetsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.container)
        recycler.adapter = adapter

        val list = listOf(
            DataMars(1, "Mars_1"),
            DataMars(2, "Mars_2"),
            DataMars(3, "Mars_3"),
            DataMars(4, "Mars_4"),
            DataMars(5, "Mars_5"),
            DataMars(6, "Mars_6"),
            DataMars(7, "Mars_7"),
        )

        adapter.listData = list
    }
}