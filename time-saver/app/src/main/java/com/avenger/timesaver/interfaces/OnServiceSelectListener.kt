package com.avenger.timesaver.interfaces

import com.avenger.timesaver.models.ShopServicesModel

interface OnServiceSelectListener {
    fun onServiceClick(service: ShopServicesModel)
}