package com.avenger.timesaver.models

import com.google.gson.annotations.SerializedName

data class ShoppingItemModel(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("price")
    val price: Any? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("imageUrl")
    val imageUrl: Any? = null,


    )