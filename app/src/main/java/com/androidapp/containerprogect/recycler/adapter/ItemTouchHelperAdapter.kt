package com.androidapp.containerprogect.recycler.adapter

interface ItemTouchHelperAdapter {

    //будет вызываться когда элемент будет
    // перетянут на достаточное расстояние, чтобы запустить анимацию перемещения
    fun onItemMove(fromPosition: Int, toPosition: Int)

    // будет вызываться во время свайпа по элементу
    fun itemDismiss(position: Int)
}