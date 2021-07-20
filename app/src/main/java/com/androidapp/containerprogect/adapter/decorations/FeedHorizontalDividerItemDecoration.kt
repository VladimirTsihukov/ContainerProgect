package com.androidapp.containerprogect.adapter.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FeedHorizontalDividerItemDecoration(
    private val divider: Int,
    private val excludeViewTypes: List<Int> //ViewType для которых не нужно делать отступ
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,  // позволяет нам задать нужные отступы без лишних пересчетов
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val viewType = parent.getChildViewHolder(view).itemViewType
        if (excludeViewTypes.contains(viewType)) return //если это horiz recView то отспупы не делаем

        val oneSideHorizontalDivider = divider / 2

        with(outRect) {
            left = oneSideHorizontalDivider
            right = oneSideHorizontalDivider
        }
    }
}