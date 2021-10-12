package com.androidapp.containerprogect

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.recycler.OnListenerClickListener
import com.androidapp.containerprogect.recycler.adapter.PlanetsAdapter
import com.androidapp.containerprogect.recycler.data.DataPlanet
import kotlinx.android.synthetic.main.activity_main.*

var ID = 0

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: PlanetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.container)
        adapter = PlanetsAdapter(click)
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val list = mutableListOf(
            DataPlanet(id = ID++, PLANET_HEADER),
            DataPlanet(id = ID++, PLANET_MARS),
        )

        adapter.listData = list

        res_activity_fab.setOnClickListener {
            adapter.appendItem()
        }
    }

    private val click = object : OnListenerClickListener {
        override fun onItemClick(data: DataPlanet) {
            Toast.makeText(
                applicationContext,
                "id - ${data.id}, name - ${data.name} ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}