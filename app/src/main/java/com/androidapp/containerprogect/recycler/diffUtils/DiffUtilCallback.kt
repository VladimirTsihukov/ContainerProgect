package com.androidapp.containerprogect.recycler.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.androidapp.containerprogect.recycler.data.DataPlanet

class DiffUtilCallback(
    private val oldList: List<Pair<DataPlanet, Boolean>>,
    private val newList: List<Pair<DataPlanet, Boolean>>
) : DiffUtil.Callback() {

    //2 методо сообщают какой размер списков
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    //сравниваются одниковы ли элементы, обычно сравниваются ID
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].first.id == newList[newItemPosition].first.id
    }

    //вызывется только если areItemsTheSame вернул true. Это дополнительная проверка которая сравнивает
    //переменные класса между собой, чтобы выяснить изменилась ли данные внутри.
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].first.name == newList[newItemPosition].first.name
    }

    //переопределяется ели мы хотим частично изменить контейнер, а не полностью перерысовывать
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return if (oldItem.first.name != newItem.first.name) {
            ChangeDataName(newItem.first.name)
        } else super.getChangePayload(oldItemPosition, newItemPosition)

    }
}