package com.androidlearning.flowtest.sharedflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

/**
 * SharedFlow 的基本用法
 */
class SharedFlowViewModel : ViewModel() {

    val timeFlow = flow {

        var time = 0

        while (true) {
            emit(time)
            delay(1000)
            time++
        }
    }
}