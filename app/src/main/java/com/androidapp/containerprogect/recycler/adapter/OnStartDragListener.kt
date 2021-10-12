package com.androidapp.containerprogect.recycler.adapter

import androidx.recyclerview.widget.RecyclerView

interface OnStartDragListener {

    //сообщает что пользователь намерен переместить элемент
    fun onStartDragListener(viewHolder: RecyclerView.ViewHolder)
}