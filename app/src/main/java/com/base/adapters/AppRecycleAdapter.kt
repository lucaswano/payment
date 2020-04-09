package com.base.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.View

/**
 * @author HungHN on 3/15/2018.
 */
abstract class AppRecycleAdapter<K, T : androidx.recyclerview.widget.RecyclerView.ViewHolder> : androidx.recyclerview.widget.RecyclerView.Adapter<T>() {

    protected val mItems: ArrayList<K> = ArrayList()
    protected var onItemClick: ItemClick<K>? = null

    fun setItemClick(click: ItemClick<K>) {
        this.onItemClick = click
    }

    fun addAll(elements: Collection<K>) = this.mItems.addAll(elements)

    fun add(element: K) = this.mItems.add(element)

    fun filter(predicate: (K) -> Boolean): List<K> = this.mItems.filter { predicate.invoke(it) }

    val items: ArrayList<K>
        get() = this.mItems

    fun getItem(position: Int): K? {
        if (position >= mItems.size) {
            return null
        }
        return items[position]
    }

    fun clear() = mItems.clear()

    override fun getItemCount(): Int = items.size

    fun bindItemClickListener(view: View, position: Int, item: K) {

        view.setOnClickListener({
            onItemClick?.invoke(position, item)
        })
    }

}

typealias ItemClick<T> = (position: Int, item: T) -> Unit