package com.androidlearning.jetpacktest.lifecycles

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class MyObserver(val lifecycle: Lifecycle) : DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
        Log.d("MyObserver", "activityStart ${lifecycle.currentState}")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d("MyObserver", "activityStop  ${lifecycle.currentState}")
    }
}