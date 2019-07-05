package com.raycoarana.awex

import com.raycoarana.awex.state.PoolState
import java.lang.Exception

class FakeAwex : Awex(
        object : UIThread {
            override fun post(runnable: Runnable?) { runnable?.run() }
            override fun isCurrentThread(): Boolean = true
        },
        object : Logger {
            override fun isEnabled(): Boolean = false
            override fun v(message: String?) { }
            override fun e(message: String?, ex: Exception?) { }

        },
        object : PoolPolicy() {
            override fun onStartUp() { }
            override fun onTaskAdded(poolState: PoolState?, task: Task<*, *>?) { task?.execute() }
            override fun onTaskExecutionTimeout(poolState: PoolState?, task: Task<*, *>?) { }
            override fun onTaskQueueTimeout(poolState: PoolState?, task: Task<*, *>?) { }
            override fun onTaskFinished(poolState: PoolState?, task: Task<*, *>?) { }
        }
)
