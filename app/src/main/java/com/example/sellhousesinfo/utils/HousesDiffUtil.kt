package com.example.sellhousesinfo.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.sellhousesinfo.model.Content
import com.example.sellhousesinfo.model.HousesModel

class HousesDiffUtil(
    private val newList: List<Content>,
    private val oldList: List<Content>
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}