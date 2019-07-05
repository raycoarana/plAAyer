package com.raycoarana.plaayer.core

import com.raycoarana.awex.Promise
import com.raycoarana.awex.callbacks.UIDoneCallback
import com.raycoarana.awex.callbacks.UIFailCallback
import com.raycoarana.awex.callbacks.UIProgressCallback

fun <R, P> Promise<R, P>.uiDone(callback: UIDoneCallback<R>): Promise<R, P> = this.done(callback)
fun <R, P> Promise<R, P>.uiFail(callback: UIFailCallback): Promise<R, P> = this.fail(callback)
fun <R, P> Promise<R, P>.uiProgress(callback: UIProgressCallback<P>): Promise<R, P> = this.progress(callback)

fun <R, P> Promise<R?, P>.filterNullable(): Promise<R, P> = this.filterSingle { v -> v != null }.mapSingle { v -> v ?: throw IllegalStateException() }

typealias SimplePromise<R> = Promise<R, Unit>