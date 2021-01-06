package com.google.android.apps.auto.sdk.ui;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

final class c implements AnimatorUpdateListener {
    private /* synthetic */ FabDrawable a;

    c(FabDrawable fabDrawable) {
        this.a = fabDrawable;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.a();
    }
}
