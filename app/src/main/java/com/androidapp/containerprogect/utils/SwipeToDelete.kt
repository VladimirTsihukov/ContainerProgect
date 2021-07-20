package com.androidapp.containerprogect.utils

import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.androidapp.containerprogect.R

class SwipeToDelete(
    val onItemDelete: (Int) -> Unit
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.ACTION_STATE_IDLE, //не будет работать перетаскивание
    ItemTouchHelper.LEFT               //удаление holder перетаскивание в лево
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false // заглушка для перетаскивания

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onItemDelete(viewHolder.bindingAdapterPosition)     //bindingAdapterPosition - указывает позицию в конкретном адаптере
    }

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (viewHolder.itemViewType != R.layout.item_post) {
            ItemTouchHelper.ACTION_STATE_IDLE
        } else {
            return super.getSwipeDirs(recyclerView, viewHolder)
        }
    }

    //указываем какой процент перетаскивания для удаления holder
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float = 0.3f

    //рисуем view
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView: View = viewHolder.itemView

        val p = Paint().also { it.color = Color.RED }
        val icon: Bitmap = BitmapFactory.decodeResource(
            recyclerView.context.resources,
            android.R.drawable.ic_menu_delete
        )

        //Draw background
        c.drawRect(
            itemView.right.toFloat() + dX,
            itemView.top.toFloat(),
            itemView.right.toFloat(),
            itemView.bottom.toFloat(),
            p
        )

        //Draw icon
        val iconMarginRight = (dX * -0.1f).coerceAtLeast(70f).coerceAtLeast(0f)
        c.drawBitmap(
            icon,
            itemView.right.toFloat() - iconMarginRight - icon.width,
            itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height) / 2,
                p
        )

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}