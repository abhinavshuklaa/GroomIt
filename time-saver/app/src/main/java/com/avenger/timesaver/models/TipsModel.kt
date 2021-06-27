package com.avenger.timesaver.models

import com.google.gson.annotations.SerializedName

data class TipsModel(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("youtube_url")
    val youtube_url: String? = null,


    )
