package com.androidlearning.flowtest.sharedflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * SharedFlow 的基本用法
 */
class SharedFlowViewModel : ViewModel() {

    /**
     * 粘性 flow
     */
    /*private val _clickCountFlow = MutableStateFlow(0)

    val clickCountFlow = _clickCountFlow.asStateFlow()

    fun increaseClickCount() {
        _clickCountFlow.value += 1
    }*/

    /**
     * 非粘性 flow
     */

    private val _loginFlow = MutableSharedFlow<String>()

    val loginFlow = _loginFlow.asSharedFlow()

    fun signIn() {
        viewModelScope.launch {
            _loginFlow.emit("Login Success")
        }
    }
}