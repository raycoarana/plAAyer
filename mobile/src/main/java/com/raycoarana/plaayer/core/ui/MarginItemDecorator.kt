package com.raycoarana.plaayer.core.ui

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View

class MarginItemDecorator constructor(
        context: Context,
        @DimenRes horizontalSpaceResId: Int,
        @DimenRes verticalSpaceResId: Int
) : RecyclerView.ItemDecoration() {

    private val horizontalSpace: Int
    private val verticalSpace: Int

    init {
        val resources = context.resources
        horizontalSpace = resources.getDimensionPixelOffset(horizontalSpaceResId) / 2
        verticalSpace = resources.getDimensionPixelOffset(verticalSpaceResId)
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.set(horizontalSpace, 0, horizontalSpace, verticalSpace)
    }
}
