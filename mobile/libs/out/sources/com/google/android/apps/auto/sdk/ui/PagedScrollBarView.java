package com.google.android.apps.auto.sdk.ui;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class PagedScrollBarView extends FrameLayout implements OnClickListener, OnLongClickListener {
    private int a;
    private final ImageView b;
    private final ImageView c;
    private final ImageView d;
    private final View e;
    private final Interpolator f;
    private final int g;
    private final int h;
    private PaginationListener i;

    public interface PaginationListener {
        public static final int PAGE_DOWN = 1;
        public static final int PAGE_UP = 0;

        void onPaginate(int i);
    }

    public PagedScrollBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public PagedScrollBarView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PagedScrollBarView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f = new AccelerateDecelerateInterpolator();
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.car_paged_scrollbar_buttons, this, true);
        this.b = (ImageView) findViewById(R.id.page_up);
        this.b.setOnClickListener(this);
        this.b.setOnLongClickListener(this);
        this.c = (ImageView) findViewById(R.id.page_down);
        this.c.setOnClickListener(this);
        this.c.setOnLongClickListener(this);
        this.d = (ImageView) findViewById(R.id.scrollbar_thumb);
        this.e = findViewById(R.id.filler);
        this.g = getResources().getDimensionPixelSize(R.dimen.min_thumb_height);
        this.h = getResources().getDimensionPixelSize(R.dimen.max_thumb_height);
        if (!context.getResources().getBoolean(R.bool.car_true_for_touch)) {
            this.b.setVisibility(8);
            this.c.setVisibility(8);
        }
    }

    private final void a(View view) {
        PaginationListener paginationListener = this.i;
        if (paginationListener != null) {
            paginationListener.onPaginate(view.getId() == R.id.page_up ? 0 : 1);
        }
    }

    public boolean isDownEnabled() {
        return this.c.isEnabled();
    }

    public boolean isDownPressed() {
        return this.c.isPressed();
    }

    public boolean isUpPressed() {
        return this.b.isPressed();
    }

    public void onClick(View view) {
        a(view);
    }

    public boolean onLongClick(View view) {
        a(view);
        return true;
    }

    @Deprecated
    public void setAutoDayNightMode() {
        setDayNightStyle(0);
    }

    @Deprecated
    public void setDarkMode() {
        setDayNightStyle(3);
    }

    public void setDayNightStyle(int i2) {
        int color;
        int color2;
        int i3;
        this.a = i2;
        switch (this.a) {
            case 0:
                color = ContextCompat.getColor(getContext(), R.color.car_tint);
                color2 = ContextCompat.getColor(getContext(), R.color.car_scrollbar_thumb);
                i3 = R.drawable.car_pagination_background;
                break;
            case 1:
                color = ContextCompat.getColor(getContext(), R.color.car_tint_inverse);
                color2 = ContextCompat.getColor(getContext(), R.color.car_scrollbar_thumb_inverse);
                i3 = R.drawable.car_pagination_background_inverse;
                break;
            case 2:
                color = ContextCompat.getColor(getContext(), R.color.car_tint_night);
                color2 = ContextCompat.getColor(getContext(), R.color.car_scrollbar_thumb_night);
                i3 = R.drawable.car_pagination_background_night;
                break;
            case 3:
                color = ContextCompat.getColor(getContext(), R.color.car_tint_day);
                color2 = ContextCompat.getColor(getContext(), R.color.car_scrollbar_thumb_day);
                i3 = R.drawable.car_pagination_background_day;
                break;
            default:
                throw new IllegalArgumentException("Unknown DayNightStyle: " + this.a);
        }
        this.d.setBackgroundColor(color2);
        this.b.setColorFilter(color, Mode.SRC_IN);
        this.b.setBackgroundResource(i3);
        this.c.setColorFilter(color, Mode.SRC_IN);
        this.c.setBackgroundResource(i3);
    }

    public void setDownEnabled(boolean z) {
        this.c.setEnabled(z);
        this.c.setAlpha(z ? 1.0f : 0.2f);
    }

    @Deprecated
    public void setLightMode() {
        setDayNightStyle(2);
    }

    public void setPaginationListener(PaginationListener paginationListener) {
        this.i = paginationListener;
    }

    public void setParameters(int i2, int i3, int i4, boolean z) {
        int height = (this.e.getHeight() - this.e.getPaddingTop()) - this.e.getPaddingBottom();
        int max = Math.max(Math.min((i4 * height) / i2, this.h), this.g);
        int i5 = height - max;
        if (isDownEnabled()) {
            i5 = (i5 * i3) / i2;
        }
        LayoutParams layoutParams = this.d.getLayoutParams();
        if (layoutParams.height != max) {
            layoutParams.height = max;
            this.d.requestLayout();
        }
        this.d.animate().y((float) i5).setDuration((long) (z ? 200 : 0)).setInterpolator(this.f).start();
    }

    public void setUpEnabled(boolean z) {
        this.b.setEnabled(z);
        this.b.setAlpha(z ? 1.0f : 0.2f);
    }
}
