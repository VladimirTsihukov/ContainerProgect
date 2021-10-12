package com.androidapp.containerprogect.recycler.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.*
import com.androidapp.containerprogect.recycler.OnListenerClickListener
import com.androidapp.containerprogect.recycler.data.DataPlanet
import com.androidapp.containerprogect.recycler.diffUtils.ChangeDataName
import com.androidapp.containerprogect.recycler.diffUtils.DiffUtilCallback
import kotlinx.android.synthetic.main.activiry_recycler_item_earth.view.*
import kotlinx.android.synthetic.main.activiry_recycler_item_mars.view.*

class PlanetsAdapter(
    private val click: OnListenerClickListener,
    private val dragListener: OnStartDragListener,
) : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    private var listData = mutableListOf<Pair<DataPlanet, Boolean>>()

    fun setItem(newList: List<Pair<DataPlanet, Boolean>>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(listData, newList))
        result.dispatchUpdatesTo(this)
        listData.clear()
        listData.addAll(newList)
    }

    override fun getItemViewType(position: Int): Int {
        if (listData[position].first.name == PLANET_HEADER) return TYPE_HEADER
        return if (listData[position].first.name == PLANET_EARTH) TYPE_EARTH else TYPE_MARS
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

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.find { it is ChangeDataName }?.let {
                holder.itemView.marsTextView.text = (it as ChangeDataName).newName
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
        holder.itemView.setOnClickListener {
            click.onItemClick(listData[position].first)
        }
    }

    override fun getItemCount(): Int = listData.size

    fun appendItem() {
        listData.add(Pair(generateItem(), false))
        notifyItemInserted(listData.size)
    }

    private fun generateItem(): DataPlanet {
        return DataPlanet(id = ID++, name = PLANET_MARS)
    }

    //перетаскиваем item
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    //удаляем элемент по свайпу
    override fun itemDismiss(position: Int) {
        listData.removeAt(position)
        notifyItemRemoved(position)
    }

    //////////////////////////////////////////////////////////////////////////////////////////

   inner class MarsHolder(private val view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
       @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
       override fun bind(data: Pair<DataPlanet, Boolean>) {
           view.marsTextView.text = "${data.first.name} - ${data.first.id}"
           itemView.marsDescriptionTextView.visibility =
               if (listData[layoutPosition].second) View.VISIBLE
               else View.GONE

           with(itemView) {
               addItemImageView.setOnClickListener { addItem() }
               removeItemImageView.setOnClickListener { deleteItem() }
               moveItemUp.setOnClickListener { movedUp() }
               moveItemDown.setOnClickListener { movedDown() }
               marsTextView.setOnClickListener { setDescription() }
               dragHandleImageView.setOnTouchListener { _, event ->
                   if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                       dragListener.onStartDragListener(this@MarsHolder)
                   }
                   false
               }
           }
       }

       private fun setDescription() {
           listData[layoutPosition] = listData[layoutPosition].let {
               it.first to !it.second
           }
           notifyItemChanged(layoutPosition)
       }

       private fun addItem() {
           listData.add(layoutPosition, Pair(generateItem(), false))
           notifyItemInserted(layoutPosition)
       }

       private fun deleteItem() {
           listData.removeAt(layoutPosition)
           notifyItemRemoved(layoutPosition)
       }

       private fun movedUp() {
           layoutPosition.takeIf { it > 1 }?.also { position ->
               listData.removeAt(position).let {
                   listData.add(position - 1, it)
               }
               notifyItemMoved(position, position - 1)
           }
       }

       private fun movedDown() {
           layoutPosition.takeIf { it < listData.size - 1 }?.also { position ->
               listData.removeAt(position).let {
                   listData.add(position + 1, it)
               }
               notifyItemMoved(position, position + 1)
           }
       }

       override fun onItemSelected() {
           itemView.setBackgroundColor(Color.LTGRAY)
       }

       override fun onItemClear() {
           itemView.setBackgroundColor(Color.WHITE)
       }

    }

    inner class EarthHolder(private val view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<DataPlanet, Boolean>) {
            view.earth_textView.text = data.first.name
        }
    }

    inner class HeaderHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<DataPlanet, Boolean>) {}
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_EARTH = 1
        private const val TYPE_MARS = 2
    }
}