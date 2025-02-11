package com.androidlearning.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.androidlearning.sunnyweather.logic.Repository
import com.androidlearning.sunnyweather.logic.dao.PlaceService

class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    private val placeLiveData = searchLiveData.switchMap { query ->
        Repository.searchPlaces(query)
    }
}