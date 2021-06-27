package com.avenger.timesaver.models

data class Shop(
    val id: String?,
    val name: String?,
    val ownerId: String?,
    val addressLine: String?,
    val city: String?,
    val state: String?,
    val images: ArrayList<String?>?,
    val pinCode: String?,
    val location: String?,
    val contact_no1: String?,
    val contact_no2: String?,
    val services: String?,
    val styles: String?
)