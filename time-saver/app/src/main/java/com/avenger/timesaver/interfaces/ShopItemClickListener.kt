package com.avenger.timesaver.interfaces

import com.avenger.timesaver.models.ItemForSale

interface ShopItemClickListener {
    fun shopItemClicked(item:ItemForSale,position:Int)
}