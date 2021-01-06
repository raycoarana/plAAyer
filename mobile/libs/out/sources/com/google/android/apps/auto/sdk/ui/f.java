package com.google.android.apps.auto.sdk.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

final class f extends OnScrollListener {
    private /* synthetic */ PagedListView a;

    f(PagedListView pagedListView) {
        this.a = pagedListView;
    }

    public final void onScrollStateChanged(RecyclerView recyclerView, int i) {
        if (this.a.mOnScrollListener != null) {
            this.a.mOnScrollListener.onScrollStateChanged(recyclerView, i);
        }
        if (i == 0) {
            this.a.mHandler.postDelayed(this.a.mPaginationRunnable, 400);
        }
    }

    public final void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (this.a.mOnScrollListener != null) {
            this.a.mOnScrollListener.onScrolled(recyclerView, i, i2);
            if (!this.a.mLayoutManager.isAtTop() && this.a.mLayoutManager.isAtBottom()) {
                this.a.mOnScrollListener.onReachBottom();
            } else if (this.a.mLayoutManager.isAtTop() || !this.a.mLayoutManager.isAtBottom()) {
                this.a.mOnScrollListener.onLeaveBottom();
            }
        }
        this.a.updatePaginationButtons(false);
    }
}
