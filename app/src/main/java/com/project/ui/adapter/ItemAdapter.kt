package com.project.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.base.R
import com.base.adapters.AppRecycleAdapter
import com.project.extensions.inflate
import kotlinx.android.synthetic.main.item_list.view.*

/**
 * @author HungHN on 3/15/2018.
 */
class ItemAdapter : AppRecycleAdapter<String, ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent.inflate(R.layout.item_list))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position) ?: ""
        holder.bindData(item)
        bindItemClickListener(holder.itemView, position, item)
    }

    class ItemViewHolder(containerView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView) {

        fun bindData(item: String) {
            itemView.textView.text = item
        }
    }
}