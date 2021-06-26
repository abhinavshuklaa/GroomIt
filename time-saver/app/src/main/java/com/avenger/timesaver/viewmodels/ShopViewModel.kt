package com.avenger.timesaver.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShopViewModel : ViewModel() {
    private val repository = TopicsRepository(this)

    private val mutableLiveData = MutableLiveData<UserUIModel>()

    val liveData: LiveData<UserUIModel> = mutableLiveData


}