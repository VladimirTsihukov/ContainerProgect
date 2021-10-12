package com.androidapp.containerprogect.recycler.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val adapter: PlanetsAdapter) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {    //включает возможность свайпа
      return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {    //включает возможность перетаскивания по длинному нажатию на элемент
        return true
    }

    override fun getMovementFlags(      //определяет направления перетаскивания свайпа
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlag = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    override fun onMove(            //оповещает наш адаптер о изменения положения или удаления, чтобы адаптер обработал это действия
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(viewHolder.layoutPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.itemDismiss(viewHolder.adapterPosition)
    }


    //2 метода нужны для того чтобы ViewHolder  корретно обрабатывал выделеие элементов
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            val itemViewHolder = viewHolder as ItemTouchHelperViewHolder
            itemViewHolder.onItemSelected()
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        val itemViewHolder = viewHolder as ItemTouchHelperViewHolder
        itemViewHolder.onItemClear()
    }
}