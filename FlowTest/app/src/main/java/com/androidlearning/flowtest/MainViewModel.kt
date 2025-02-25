package com.androidlearning.flowtest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {

    val timeFlow = flow {

        var time = 0

        while (true) {
            emit(time)
            delay(1000)
            time++
        }
    }
}