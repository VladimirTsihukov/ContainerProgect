package com.androidapp.containerprogect.recycler.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.*
import com.androidapp.containerprogect.recycler.OnListenerClickListener
import com.androidapp.containerprogect.recycler.data.DataPlanet
import kotlinx.android.synthetic.main.activiry_recycler_item_earth.view.*
import kotlinx.android.synthetic.main.activiry_recycler_item_mars.view.*

class PlanetsAdapter(private val click: OnListenerClickListener) :
    RecyclerView.Adapter<BaseViewHolder>() {

    var listData = mutableListOf<DataPlanet>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (listData[position].name == PLANET_HEADER) return TYPE_HEADER
        return if (listData[position].name == PLANET_EARTH) TYPE_EARTH else TYPE_MARS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TYPE_HEADER -> HeaderHolder(inflater.inflate(R.layout.activity_recycler_item_header, parent, false))
            TYPE_EARTH -> EarthHolder(inflater.inflate(R.layout.activiry_recycler_item_earth, parent, false))
            TYPE_MARS -> MarsHolder(inflater.inflate(R.layout.activiry_recycler_item_mars, parent, false))
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
        holder.itemView.setOnClickListener {
            click.onItemClick(listData[position])
        }
    }

    override fun getItemCount(): Int = listData.size

    fun appendItem() {
        listData.add(generateItem())
        notifyDataSetChanged()
    }

    private fun generateItem(): DataPlanet {
        return DataPlanet(id = ID++, name = PLANET_MARS)
    }

    //////////////////////////////////////////////////////////////////////////////////////////

   inner class MarsHolder(private val view: View) : BaseViewHolder(view) {
       @SuppressLint("SetTextI18n")
       override fun bind(data: DataPlanet) {
           view.marsTextView.text = "${data.name} - ${data.id}"

           itemView.addItemImageView.setOnClickListener {addItem()}
           itemView.removeItemImageView.setOnClickListener { deleteItem() }
       }

       private fun addItem() {
           listData.add(layoutPosition, generateItem())
           notifyItemInserted(layoutPosition)
       }

       private fun deleteItem() {
           listData.removeAt(layoutPosition)
           notifyItemRemoved(layoutPosition)
       }
    }

    inner class EarthHolder(private val view: View) : BaseViewHolder(view) {
        override fun bind(data: DataPlanet) {
            view.earth_textView.text = data.name
        }
    }

    inner class HeaderHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: DataPlanet) {
        }
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_EARTH = 1
        private const val TYPE_MARS = 2
    }
}