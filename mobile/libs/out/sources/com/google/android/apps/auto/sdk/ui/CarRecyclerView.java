package com.google.android.apps.auto.sdk.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CarRecyclerView extends RecyclerView {
    private boolean a;
    private Constructor<?> b;
    private boolean c;

    public CarRecyclerView(Context context) {
        this(context, null);
    }

    public CarRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setFocusableInTouchMode(false);
        setFocusable(false);
    }

    private final void a(@NonNull View view, float f) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                a(viewGroup.getChildAt(i), f);
            }
            return;
        }
        view.setAlpha(f);
    }

    public boolean drawChild(@NonNull Canvas canvas, @NonNull View view, long j) {
        if (this.a) {
            float bottom = (view.getTop() >= getBottom() || view.getBottom() <= getBottom()) ? (view.getTop() >= getTop() || view.getBottom() <= getTop()) ? 1.0f : ((float) (view.getBottom() - getTop())) / ((float) view.getHeight()) : ((float) (getBottom() - view.getTop())) / ((float) view.getHeight());
            a(view, 1.0f - ((1.0f - bottom) * (1.0f - bottom)));
        }
        return CarRecyclerView.super.drawChild(canvas, view, j);
    }

    public boolean fling(int i, int i2) {
        this.c = true;
        return getLayoutManager().settleScrollForFling(this, i2);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Class cls;
        if (parcelable.getClass().getClassLoader() != getClass().getClassLoader()) {
            if (this.b == null) {
                Class[] declaredClasses = RecyclerView.class.getDeclaredClasses();
                int length = declaredClasses.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        cls = null;
                        break;
                    }
                    cls = declaredClasses[i];
                    if (cls.getCanonicalName().equals("android.support.v7.widget.RecyclerView.SavedState")) {
                        break;
                    }
                    i++;
                }
                if (cls == null) {
                    throw new RuntimeException("RecyclerView$SavedState not found!");
                }
                Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
                int length2 = declaredConstructors.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        break;
                    }
                    Constructor<?> constructor = declaredConstructors[i2];
                    Class[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0].getCanonicalName().equals("android.os.Parcel")) {
                        this.b = constructor;
                        this.b.setAccessible(true);
                        break;
                    }
                    i2++;
                }
                if (this.b == null) {
                    throw new RuntimeException("RecyclerView$SavedState constructor not found!");
                }
                this.b = this.b;
            }
            Parcel obtain = Parcel.obtain();
            parcelable.writeToParcel(obtain, 0);
            try {
                CarRecyclerView.super.onRestoreInstanceState((Parcelable) this.b.newInstance(new Object[]{obtain}));
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            CarRecyclerView.super.onRestoreInstanceState(parcelable);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = CarRecyclerView.super.onTouchEvent(motionEvent);
        if (motionEvent.getActionMasked() == 1) {
            if (!this.c) {
                getLayoutManager().settleScrollForFling(this, 0);
            }
            this.c = false;
        }
        return onTouchEvent;
    }

    public void pageDown() {
        int pageDownPosition = getLayoutManager().getPageDownPosition();
        if (pageDownPosition != -1) {
            smoothScrollToPosition(pageDownPosition);
        }
    }

    public void pageUp() {
        int pageUpPosition = getLayoutManager().getPageUpPosition();
        if (pageUpPosition != -1) {
            smoothScrollToPosition(pageUpPosition);
        }
    }

    public void setFadeLastItem(boolean z) {
        this.a = z;
    }
}
