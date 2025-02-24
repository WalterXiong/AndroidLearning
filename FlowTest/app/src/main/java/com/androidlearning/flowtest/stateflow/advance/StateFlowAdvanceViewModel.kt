package com.androidlearning.flowtest.stateflow.advance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

/**
 * StateFlow 的基本用法
 */
class StateFlowAdvanceViewModel : ViewModel() {

    val timeFlow = flow {

        var count = 0

        while (true) {
            emit(count)
            delay(1000)
            count++
        }
    }


    /**
     * 借助stateIn函数，可以将其他的Flow转换成StateFlow
     *
     * stateIn 函数接收 3 个参数
     * 第一个参数：是作用域，传入 viewModelScope
     * 第二个参数：可以指定一个 X 秒的超时时长（当前场景中作为超时机制的超时时长）
     * 第三个参数：是初始值，计数器传入 0 即可
     *
     * 用于解决屏幕旋转时长在超时时长的区间内那么 Flow 不会停止工作
     */
    val stateFlow = timeFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        0
    )
}