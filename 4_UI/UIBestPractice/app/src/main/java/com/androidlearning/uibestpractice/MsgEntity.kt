package com.androidlearning.uibestpractice

class MsgEntity(val content: String, val type: Int) {

    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}