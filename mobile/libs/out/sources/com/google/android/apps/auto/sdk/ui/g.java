package com.google.android.apps.auto.sdk.ui;

final class g implements Runnable {
    private /* synthetic */ PagedListView a;

    g(PagedListView pagedListView) {
        this.a = pagedListView;
    }

    public final void run() {
        boolean isUpPressed = this.a.c.isUpPressed();
        boolean isDownPressed = this.a.c.isDownPressed();
        if (isUpPressed && isDownPressed) {
            return;
        }
        if (isUpPressed) {
            this.a.mRecyclerView.pageUp();
        } else if (isDownPressed) {
            this.a.mRecyclerView.pageDown();
        }
    }
}
