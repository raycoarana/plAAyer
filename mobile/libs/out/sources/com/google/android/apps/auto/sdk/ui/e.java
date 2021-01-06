package com.google.android.apps.auto.sdk.ui;

import android.util.Log;
import com.google.android.apps.auto.sdk.ui.PagedScrollBarView.PaginationListener;

final class e implements PaginationListener {
    private /* synthetic */ PagedListView a;

    e(PagedListView pagedListView) {
        this.a = pagedListView;
    }

    public final void onPaginate(int i) {
        if (i == 0) {
            this.a.mRecyclerView.pageUp();
            if (this.a.mOnScrollListener != null) {
                this.a.mOnScrollListener.onScrollUpButtonClicked();
            }
        } else if (i == 1) {
            this.a.mRecyclerView.pageDown();
            if (this.a.mOnScrollListener != null) {
                this.a.mOnScrollListener.onScrollDownButtonClicked();
            }
        } else {
            Log.e("PagedListView", "Unknown pagination direction (" + i + ")");
        }
    }
}
