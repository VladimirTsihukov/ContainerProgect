package com.androidapp.containerprogect

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.recycler.OnListenerClickListener
import com.androidapp.containerprogect.recycler.adapter.ItemTouchHelperCallback
import com.androidapp.containerprogect.recycler.adapter.OnStartDragListener
import com.androidapp.containerprogect.recycler.adapter.PlanetsAdapter
import com.androidapp.containerprogect.recycler.data.DataPlanet
import kotlinx.android.synthetic.main.activity_main.*

var ID = 0

class MainActivity : AppCompatActivity() {

    private var isNewList = false
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: PlanetsAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.container)
        adapter = PlanetsAdapter(click, startDragListener)
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val list = mutableListOf(
            Pair(DataPlanet(id = ID++, PLANET_HEADER), false),
            Pair(DataPlanet(id = ID++, PLANET_MARS), false),
            Pair(DataPlanet(id = ID++, PLANET_MARS), false),
        )

        adapter.setItem(list)

        res_activity_fab.setOnClickListener {
            adapter.appendItem()
        }

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recycler)

        res_activity_fab_diff_util.setOnClickListener { changeAdapter() }
    }

    private fun changeAdapter() {
        adapter.setItem(createItemList(isNewList))
        isNewList = !isNewList
    }

    private fun createItemList(checkList: Boolean): List<Pair<DataPlanet, Boolean>> {
        return when (checkList) {
            true -> listOf(
                Pair(DataPlanet(id = 0, PLANET_HEADER), false),
                Pair(DataPlanet(id = 1, PLANET_MARS), false),
                Pair(DataPlanet(id = 2, PLANET_MARS), false),
                Pair(DataPlanet(id = 3, PLANET_MARS), false),
                Pair(DataPlanet(id = 4, PLANET_MARS), false),
                Pair(DataPlanet(id = 5, PLANET_MARS), false),
                Pair(DataPlanet(id = 6, PLANET_MARS), false),
            )
            false ->  listOf(
                Pair(DataPlanet(id = 0, PLANET_HEADER), false),
                Pair(DataPlanet(id = 1, PLANET_MARS), false),
                Pair(DataPlanet(id = 2, PLANET_VENUS), false),
                Pair(DataPlanet(id = 3, PLANET_MARS), false),
                Pair(DataPlanet(id = 4, PLANET_MARS), false),
                Pair(DataPlanet(id = 5, PLANET_VENUS), false),
                Pair(DataPlanet(id = 6, PLANET_VENUS), false),
            )
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

    private val startDragListener = object : OnStartDragListener {
        override fun onStartDragListener(viewHolder: RecyclerView.ViewHolder) {
            itemTouchHelper.startDrag(viewHolder)
        }
    }
}