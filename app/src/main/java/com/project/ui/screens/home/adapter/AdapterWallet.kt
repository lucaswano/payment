package com.project.ui.screens.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.R
import com.project.ui.Const
import com.project.ui.screens.home.model.ItemHeader
import com.project.ui.screens.home.model.ItemWallet
import com.project.ui.screens.home.model.WalletObj
import kotlinx.android.synthetic.main.item_time_wallet.view.*
import kotlinx.android.synthetic.main.item_wallet.view.*

class AdapterWallet: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        const val TYPE_HEADER       = 0
        const val TYPE_ITEM         = 1
    }
    private var listWallet = arrayListOf<WalletObj>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType){

        TYPE_ITEM -> ItemWalletHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wallet, parent, false))
        else -> ItemTime(LayoutInflater.from(parent.context).inflate(R.layout.item_time_wallet, parent, false))
    }

    override fun getItemCount() = listWallet.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemWalletHolder -> holder.bindData(listWallet[position] as ItemWallet, position)
            is ItemTime -> holder.bindData(listWallet[position] as ItemHeader)
        }
    }

    fun setData(list: ArrayList<WalletObj>){
        this.listWallet = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (listWallet[position]){
            is ItemHeader -> TYPE_HEADER
            is ItemWallet -> TYPE_ITEM
            else -> TYPE_HEADER
        }
    }
    inner class ItemWalletHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindData(item: ItemWallet, position: Int){
            if (position == 0) itemView.setBackgroundResource(R.drawable.bg_round_payment)

            itemView.imgWallet.setImageResource(when(item.type){
                Const.TYPE_GOOGLE_PLAY -> R.drawable.icon_google_play
                Const.TYPE_COREL -> R.drawable.icon_corel
                Const.TYPE_DUELIST -> R.drawable.icon_duelist
                Const.TYPE_HELEN -> R.drawable.icon_helen
                else -> R.drawable.icon_steam
            })

            itemView.itemName.text = item.itemName
            itemView.companyName.text = item.companyName
            itemView.paymentAmount.text = itemView.context.getString(R.string.payment_amount, item.subTotal + item.fee)
        }
    }

    inner class ItemTime(view: View): RecyclerView.ViewHolder(view){
        fun bindData(item: ItemHeader){
            itemView.timeWallet.text = item.time
        }
    }
}