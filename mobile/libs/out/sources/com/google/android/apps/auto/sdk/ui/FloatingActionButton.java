package com.google.android.apps.auto.sdk.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.BaseSavedState;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Checkable;
import android.widget.ImageButton;

public class FloatingActionButton extends ImageButton implements Checkable {
    private static final int[] h = {16842912};
    private Animation a;
    private Animation b;
    private Drawable c;
    private b d;
    private boolean e;
    private boolean f;
    private OnCheckedChangeListener g;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(FloatingActionButton floatingActionButton, boolean z);
    }

    static final class a extends BaseSavedState {
        public static final Creator<a> CREATOR = new d();
        boolean a;

        private a(Parcel parcel) {
            super(parcel);
            this.a = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        /* synthetic */ a(Parcel parcel, byte b) {
            this(parcel);
        }

        a(Parcelable parcelable) {
            super(parcelable);
        }

        public final String toString() {
            String hexString = Integer.toHexString(System.identityHashCode(this));
            return new StringBuilder(String.valueOf(hexString).length() + 47).append("FloatingActionButton.SavedState{").append(hexString).append(" checked=").append(this.a).append("}").toString();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.a));
        }
    }

    enum b {
        Custom(0, 0),
        Oval(1, R.drawable.car_oval_button_ripple),
        Rectangular(2, R.drawable.car_rectangular_button_ripple),
        Toggle(3, R.drawable.car_toggle_button_ripple);
        
        final int b;

        private b(int i, int i2) {
            this.b = i2;
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null, R.attr.carButtonStyle, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.carButtonStyle, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, i, i2);
        try {
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.FloatingActionButton_focusGainedAnimation, 0);
            if (resourceId != 0) {
                this.a = AnimationUtils.loadAnimation(getContext(), resourceId);
            }
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.FloatingActionButton_focusLostAnimation, 0);
            if (resourceId2 != 0) {
                this.b = AnimationUtils.loadAnimation(getContext(), resourceId2);
            }
            this.d = b.values()[obtainStyledAttributes.getInt(R.styleable.FloatingActionButton_buttonType, 0)];
            b bVar = this.d;
            this.c = bVar.b == 0 ? null : getContext().getDrawable(bVar.b);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public boolean isChecked() {
        return this.e;
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(h.length + i);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, h);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z) {
            if (this.a != null) {
                startAnimation(this.a);
            }
        } else if (this.b != null) {
            startAnimation(this.b);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        a aVar = (a) parcelable;
        super.onRestoreInstanceState(aVar.getSuperState());
        setChecked(aVar.a);
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        a aVar = new a(super.onSaveInstanceState());
        aVar.a = isChecked();
        return aVar;
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            if (this.b != null) {
                startAnimation(this.b);
            }
            if (this.c != null) {
                setBackground(this.c);
            }
            String valueOf = String.valueOf(getResources().getResourceEntryName(getId()));
            Log.i("FloatingActionButton", valueOf.length() != 0 ? "id: ".concat(valueOf) : new String("id: "));
            String valueOf2 = String.valueOf(this.d);
            Log.i("FloatingActionButton", new StringBuilder(String.valueOf(valueOf2).length() + 7).append("mType: ").append(valueOf2).toString());
            String valueOf3 = String.valueOf(this.a);
            Log.i("FloatingActionButton", new StringBuilder(String.valueOf(valueOf3).length() + 23).append("mFocusGainedAnimation: ").append(valueOf3).toString());
            String valueOf4 = String.valueOf(this.b);
            Log.i("FloatingActionButton", new StringBuilder(String.valueOf(valueOf4).length() + 21).append("mFocusLostAnimation: ").append(valueOf4).toString());
            String valueOf5 = String.valueOf(this.c);
            Log.i("FloatingActionButton", new StringBuilder(String.valueOf(valueOf5).length() + 13).append("mBackground: ").append(valueOf5).toString());
            Log.i("FloatingActionButton", "mIsChecked: " + this.e);
            Log.i("FloatingActionButton", "mIsBroadcasting: " + this.f);
            String valueOf6 = String.valueOf(this.g);
            Log.i("FloatingActionButton", new StringBuilder(String.valueOf(valueOf6).length() + 26).append("mOnCheckedChangeListener: ").append(valueOf6).toString());
        }
    }

    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    public void setChecked(boolean z) {
        if (this.e != z) {
            this.e = z;
            refreshDrawableState();
            if (!this.f) {
                this.f = true;
                if (this.g != null) {
                    this.g.onCheckedChanged(this, this.e);
                }
                this.f = false;
            }
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.g = onCheckedChangeListener;
    }

    public void toggle() {
        if (this.d == b.Toggle) {
            setChecked(!this.e);
        }
    }
}
