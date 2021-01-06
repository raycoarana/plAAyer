package com.google.android.apps.auto.sdk.ui;

import android.support.v7.widget.RecyclerView.ItemAnimator.ItemAnimatorFinishedListener;

final class b implements ItemAnimatorFinishedListener {
    private /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onAnimationsFinished() {
        this.a.a.offsetRows();
    }
}
