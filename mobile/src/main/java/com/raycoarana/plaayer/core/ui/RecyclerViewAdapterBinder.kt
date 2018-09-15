package com.raycoarana.plaayer.core.ui

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raycoarana.plaayer.BR

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bindToList")
fun bindRecyclerAdapter(recyclerView: RecyclerView, items: List<ItemViewModel>) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = BindableAdapter<ItemViewModel>()
    }
    val bindableAdapter = recyclerView.adapter as BindableAdapter<ItemViewModel>
    bindableAdapter.swapItems(items)
}

class BindableAdapter<T : ItemViewModel> : RecyclerView.Adapter<BindableViewHolder>() {
    private var items: List<T> = emptyList()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].layoutId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val view = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), viewType, parent, false)
        return BindableViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)!!.setVariable(BR.viewModel, items[position])
    }

    fun swapItems(newItems: List<T>) {
        val diffUtil = DiffUtil.calculateDiff(BindableDiffUtilCallback(items, newItems), true)
        items = newItems
        diffUtil.dispatchUpdatesTo(this)
    }
}

class BindableDiffUtilCallback<T : ItemViewModel> constructor(
        private val oldList: List<T>,
        private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
}

class BindableViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

open class ItemViewModel(val layoutId: Int)