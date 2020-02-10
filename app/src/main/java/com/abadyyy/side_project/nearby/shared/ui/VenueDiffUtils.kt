package com.abadyyy.side_project.nearby.shared.ui

import androidx.recyclerview.widget.DiffUtil
import com.abadyyy.side_project.nearby.shared.store.model.VenuesUIModel

class VenueDiffUtils constructor(
    private val oldItems: List<VenuesUIModel>,
    private val newItems: List<VenuesUIModel>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition] &&
                oldItems[oldItemPosition].address == newItems[newItemPosition].address &&
                oldItems[oldItemPosition].name == newItems[newItemPosition].name
    }
}