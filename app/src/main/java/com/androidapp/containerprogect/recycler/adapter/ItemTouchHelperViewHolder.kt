package com.androidapp.containerprogect.recycler.adapter

interface ItemTouchHelperViewHolder {

    fun onItemSelected()  //будет вызываться в процессе смахивания
    fun onItemClear()      //когда процесс закончится
}