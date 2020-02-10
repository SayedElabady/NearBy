package com.abadyyy.side_project.nearby.shared.ui.bindingadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abadyyy.side_project.nearby.BR
import com.abadyyy.side_project.nearby.R
import com.abadyyy.side_project.nearby.databinding.ItemVenueBinding
import com.abadyyy.side_project.nearby.shared.store.model.VenuesUIModel
import com.abadyyy.side_project.nearby.shared.ui.VenueDiffUtils


open class VenuesRecyclerAdapter(
    private val clickListener: ((VenuesUIModel) -> Unit)? = null,
    _items: List<VenuesUIModel> = listOf()
) : RecyclerView.Adapter<VenuesRecyclerAdapter.ViewHolder<ItemVenueBinding>>() {

    private var items: MutableList<VenuesUIModel> = _items.toMutableList()

    fun setItems(value: List<VenuesUIModel>) {
        val diff = DiffUtil.calculateDiff(VenueDiffUtils(this.items, value))
        diff.dispatchUpdatesTo(this)
        this.items = value.toMutableList()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder<ItemVenueBinding> {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding =
            DataBindingUtil.inflate<ItemVenueBinding>(
                inflater,
                R.layout.item_venue,
                viewGroup,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder<ItemVenueBinding>, i: Int) {
        val model = items[i]
        viewHolder.binding.setVariable(BR.model, model)
        viewHolder.itemView.setOnClickListener {
            clickListener?.invoke(model)
        }

    }

    override fun getItemCount() = items.size

    class ViewHolder<Binding : ViewDataBinding>(val binding: Binding) :
        RecyclerView.ViewHolder(binding.root)

}