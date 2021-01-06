package com.google.android.apps.auto.sdk.ui;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView.ItemAnimator.ItemAnimatorFinishedListener;
import android.support.v7.widget.RecyclerView.ViewHolder;

public final class a extends DefaultItemAnimator {
    /* access modifiers changed from: private */
    public final CarLayoutManager a;
    private final ItemAnimatorFinishedListener b = new b(this);

    public a(CarLayoutManager carLayoutManager) {
        this.a = carLayoutManager;
    }

    public final boolean animateChange(ViewHolder viewHolder, ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        float f = 0.0f;
        if (viewHolder2 != null) {
            f = viewHolder2.itemView.getAlpha();
        }
        boolean animateChange = a.super.animateChange(viewHolder, viewHolder2, i, i2, i3, i4);
        if (viewHolder2 != null) {
            viewHolder2.itemView.setAlpha(f);
        }
        return animateChange;
    }

    public final void onMoveFinished(ViewHolder viewHolder) {
        isRunning(this.b);
    }
}
