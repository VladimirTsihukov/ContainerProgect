package com.androidapp.containerprogect.recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.PLANET_EARTH
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.recycler.OnListenerClickListener
import com.androidapp.containerprogect.recycler.data.DataPlanet
import kotlinx.android.synthetic.main.activiry_recycler_item_earth.view.*
import kotlinx.android.synthetic.main.activiry_recycler_item_mars.view.*

class PlanetsAdapter(private val click: OnListenerClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listData = listOf<DataPlanet>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (listData[position].name == PLANET_EARTH) TYPE_EARTH else TYPE_MARS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_EARTH) {
            EarthHolder(inflater.inflate(R.layout.activiry_recycler_item_earth, parent, false))
        } else {
            MarsHolder(inflater.inflate(R.layout.activiry_recycler_item_mars, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EarthHolder -> {
                holder.onBindEarth(listData[position])
                holder.itemView.setOnClickListener {
                    click.onItemClick(listData[position])
                }
            }
            is MarsHolder -> {
                holder.onBindMars(listData[position])
                holder.itemView.setOnClickListener {
                    click.onItemClick(listData[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    //////////////////////////////////////////////////////////////////////////////////////////

   inner class MarsHolder(private val view: View) : RecyclerView.ViewHolder(view) {

       fun onBindMars(data: DataPlanet) {
           view.marsTextView.text = data.name
       }
    }

    inner class EarthHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun onBindEarth(dataEarth: DataPlanet) {
            view.earth_textView.text = dataEarth.name
        }
    }

    companion object {
        private const val TYPE_EARTH = 0
        private const val TYPE_MARS = 1
    }
}