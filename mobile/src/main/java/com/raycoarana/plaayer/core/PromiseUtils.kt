package com.raycoarana.plaayer.core

import com.raycoarana.awex.Promise
import com.raycoarana.awex.callbacks.UIDoneCallback
import com.raycoarana.awex.callbacks.UIFailCallback
import com.raycoarana.awex.callbacks.UIProgressCallback

fun <R, P> Promise<R, P>.uiDone(callback: UIDoneCallback<R>) = this.done(callback)!!
fun <R, P> Promise<R, P>.uiFail(callback: UIFailCallback) = this.fail(callback)!!
fun <R, P> Promise<R, P>.uiProgress(callback: UIProgressCallback<P>) = this.progress(callback)!!
