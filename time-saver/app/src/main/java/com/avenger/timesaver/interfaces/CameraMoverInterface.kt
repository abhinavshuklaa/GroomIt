package com.avenger.timesaver.interfaces

import com.avenger.timesaver.models.Shop

interface CameraMoverInterface {
    fun moveCamera(shop: Shop)
    fun startStoreDetails(shop: Shop)
}