package com.google.android.apps.auto.sdk.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.DecelerateInterpolator;

public class FabDrawable extends Drawable {
    private final int a;
    private final int b;
    private final Paint c;
    private final Paint d;
    private final ValueAnimator e;
    private boolean f;
    private int g;
    private int h;
    private Outline i;

    public FabDrawable(int i2, int i3, int i4) {
        this.c = new Paint(1);
        this.d = new Paint(1);
        if (i2 < 0) {
            throw new IllegalArgumentException("Fab growth must be >= 0.");
        } else if (i2 > i3) {
            throw new IllegalArgumentException("Fab growth must be <= strokeWidth.");
        } else if (i3 < 0) {
            throw new IllegalArgumentException("Stroke width must be >= 0.");
        } else {
            this.a = i2;
            this.b = i3;
            this.e = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration((long) i4);
            this.e.setInterpolator(new DecelerateInterpolator());
            this.e.addUpdateListener(new c(this));
        }
    }

    public FabDrawable(Context context) {
        this(context.getResources().getDimensionPixelSize(R.dimen.car_fab_focused_growth), context.getResources().getDimensionPixelSize(R.dimen.car_fab_focused_stroke_width), context.getResources().getInteger(R.integer.car_fab_animation_duration));
    }

    /* access modifiers changed from: private */
    public final void a() {
        int min = (Math.min(getBounds().width(), getBounds().height()) / 2) - this.b;
        float animatedFraction = this.e.getAnimatedFraction();
        this.h = (int) (((float) min) + (((float) this.b) * animatedFraction));
        this.g = (int) (((float) min) + (animatedFraction * ((float) this.a)));
        b();
        invalidateSelf();
    }

    private final void b() {
        int width = getBounds().width() / 2;
        int height = getBounds().height() / 2;
        if (this.i != null) {
            this.i.setRoundRect(width - this.h, height - this.h, width + this.h, height + this.h, (float) this.h);
        }
    }

    public void draw(Canvas canvas) {
        int width = canvas.getWidth() / 2;
        int height = canvas.getHeight() / 2;
        canvas.drawCircle((float) width, (float) height, (float) this.h, this.d);
        canvas.drawCircle((float) width, (float) height, (float) this.g, this.c);
    }

    public int getOpacity() {
        return -1;
    }

    public void getOutline(Outline outline) {
        this.i = outline;
        b();
    }

    public boolean isStateful() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        a();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        boolean z = false;
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 == 16842908) {
                z = true;
            }
            if (i2 == 16842919) {
                z2 = true;
            }
        }
        if ((z || z2) && this.f) {
            this.e.start();
            this.f = false;
        } else if (!z && !z2 && !this.f) {
            this.e.reverse();
            this.f = true;
        }
        return onStateChange || z;
    }

    public void setAlpha(int i2) {
        this.c.setAlpha(i2);
        this.d.setAlpha(i2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.c.setColorFilter(colorFilter);
        this.d.setColorFilter(colorFilter);
    }

    public void setFabAndStrokeColor(int i2) {
        setFabAndStrokeColor(i2, 0.9f);
    }

    public void setFabAndStrokeColor(int i2, float f2) {
        setFabColor(i2);
        float[] fArr = new float[3];
        Color.colorToHSV(i2, fArr);
        fArr[2] = fArr[2] * f2;
        setStrokeColor(Color.HSVToColor(fArr));
    }

    public void setFabColor(int i2) {
        this.c.setColor(i2);
    }

    public void setStrokeColor(int i2) {
        this.d.setColor(i2);
    }
}
