package com.androidapp.containerprogect.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.R
import com.androidapp.containerprogect.recycler.data.DataMars
import kotlinx.android.synthetic.main.activiry_recycler_item_mars.view.*

class PlanetsAdapter : RecyclerView.Adapter<PlanetsAdapter.MarsHolder>() {

    var listData = listOf<DataMars>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsHolder {
        return MarsHolder(LayoutInflater.from(parent.context).inflate(R.layout.activiry_recycler_item_mars, parent, false))
    }

    override fun onBindViewHolder(holder: MarsHolder, position: Int) {
        holder.onBind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

   inner class MarsHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(dataMars: DataMars) {
            view.marsTextView.text = dataMars.name
        }
    }
}