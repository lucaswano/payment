package com.project.ui.screens.home.model

class ItemWallet(val type: Int = -1,
                 val itemName: String = "",
                 val companyName: String = "",
                 val time: Long = 0,
                 val subTotal: Int = 0,
                 val fee: Int = 0
): WalletObj()