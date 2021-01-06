package com.google.android.apps.auto.sdk.ui;

final class h implements Runnable {
    private /* synthetic */ PagedListView a;

    h(PagedListView pagedListView) {
        this.a = pagedListView;
    }

    public final void run() {
        this.a.updatePaginationButtons(true);
    }
}
