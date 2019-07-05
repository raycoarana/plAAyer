package com.raycoarana.plaayer.core

import com.raycoarana.awex.Awex
import okhttp3.*
import java.io.IOException

fun OkHttpClient.newCallWithPromise(awex: Awex, request: Request): SimplePromise<Response> {
    val promise = awex.newAwexPromise<Response, Unit>()
    this.newCall(request).enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            promise.resolve(response)
        }

        override fun onFailure(call: Call, e: IOException) {
            promise.reject(e)
        }
    })
    return promise
}