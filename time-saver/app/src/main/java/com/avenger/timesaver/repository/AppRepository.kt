package com.avenger.timesaver.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.avenger.timesaver.models.UserModel


class AppRepository {

    val dbRootReference = FirebaseDatabase.getInstance()
    val userModel = MutableLiveData<UserModel>()

    init {

    }



}