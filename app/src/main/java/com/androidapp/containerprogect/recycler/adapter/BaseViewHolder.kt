package com.androidapp.containerprogect.recycler.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.recycler.data.DataPlanet

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: DataPlanet)
}