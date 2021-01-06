package com.google.android.apps.auto.sdk.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class MaxWidthLayout extends FrameLayout {
    private int a;

    public MaxWidthLayout(Context context) {
        super(context);
        a(context.obtainStyledAttributes(R.styleable.MaxWidthLayout));
    }

    public MaxWidthLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context.obtainStyledAttributes(attributeSet, R.styleable.MaxWidthLayout));
    }

    public MaxWidthLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context.obtainStyledAttributes(attributeSet, R.styleable.MaxWidthLayout, i, 0));
    }

    private final void a(TypedArray typedArray) {
        this.a = typedArray.getDimensionPixelSize(R.styleable.MaxWidthLayout_carMaxWidth, 0);
        typedArray.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.a != 0) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = getChildAt(childCount);
                if (childAt.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    if (layoutParams.width == -1 && childAt.getMeasuredWidth() > this.a) {
                        childAt.measure(MeasureSpec.makeMeasureSpec((this.a - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824), MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), 1073741824));
                    }
                }
            }
        }
    }
}
