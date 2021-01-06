package com.google.android.apps.auto.sdk;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MenuItem extends a {
    public static final Creator<MenuItem> CREATOR = new b(MenuItem.class);
    private int a = -1;
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    @Nullable
    public Bundle c;
    /* access modifiers changed from: private */
    @NonNull
    public CharSequence d;
    /* access modifiers changed from: private */
    @Nullable
    public CharSequence e;
    /* access modifiers changed from: private */
    @DrawableRes
    public int f;
    @ColorInt
    private int g;
    /* access modifiers changed from: private */
    @Nullable
    public Bitmap h;
    @Nullable
    private Uri i;
    /* access modifiers changed from: private */
    @DrawableRes
    public int j;
    @ColorInt
    private int k;
    private boolean l;
    private boolean m;
    /* access modifiers changed from: private */
    public boolean n;
    /* access modifiers changed from: private */
    @Nullable
    public RemoteViews o;
    private char p;

    public static class Builder {
        private MenuItem a = new MenuItem();

        public Builder() {
        }

        public Builder(MenuItem menuItem) {
            Bundle bundle = new Bundle();
            menuItem.writeToBundle(bundle);
            this.a.readFromBundle(bundle);
        }

        public MenuItem build() {
            int i = 0;
            if (TextUtils.isEmpty(this.a.d)) {
                Log.w("CSL.MenuItem", "MenuItem title should be non-empty");
            }
            if (this.a.b != 1 && this.a.n) {
                throw new IllegalArgumentException("MenuItem is not a checkbox type but is checked");
            } else if (this.a.b == 3 || this.a.o == null) {
                int i2 = this.a.getIconBitmap() != null ? 1 : 0;
                int i3 = this.a.getIconResId() != 0 ? 1 : 0;
                if (this.a.b() != null) {
                    i = 1;
                }
                if (i2 + 0 + i3 + i > 1) {
                    throw new IllegalArgumentException("Cannot set multiple icon types.");
                } else if (this.a.j == 0 || this.a.b == 0) {
                    return this.a;
                } else {
                    throw new IllegalArgumentException("Cannot set right icon on non ITEM types.");
                }
            } else {
                throw new IllegalArgumentException("The menu is not a special view, but has remote views");
            }
        }

        public Builder setChecked(boolean z) {
            this.a.n = z;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.a.c = bundle;
            return this;
        }

        public Builder setIconBitmap(Bitmap bitmap) {
            this.a.h = bitmap;
            return this;
        }

        public Builder setIconResId(@DrawableRes int i) {
            this.a.f = i;
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.a.e = charSequence;
            return this;
        }

        public Builder setTitle(@NonNull CharSequence charSequence) {
            this.a.d = charSequence;
            return this;
        }

        public Builder setType(int i) {
            this.a.b = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
        public static final int CHECKBOX = 1;
        public static final int ITEM = 0;
        public static final int SUBMENU = 2;
    }

    MenuItem() {
    }

    public final int a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i2) {
        this.a = i2;
    }

    /* access modifiers changed from: 0000 */
    public final void a(boolean z) {
        if (this.b != 1) {
            throw new IllegalArgumentException("MenuItem is not a checkbox type");
        }
        this.n = z;
    }

    @Nullable
    public final Uri b() {
        return this.i;
    }

    @Nullable
    public Bundle getExtras() {
        return this.c;
    }

    @Nullable
    public Bitmap getIconBitmap() {
        return this.h;
    }

    @DrawableRes
    public int getIconResId() {
        return this.f;
    }

    @Nullable
    public CharSequence getSubtitle() {
        return this.e;
    }

    @NonNull
    public CharSequence getTitle() {
        return this.d;
    }

    public int getType() {
        return this.b;
    }

    public boolean isChecked() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        this.a = bundle.getInt("position", -2);
        this.b = bundle.getInt("type");
        this.c = bundle.getBundle("extras");
        this.d = bundle.getCharSequence("title");
        if (this.d != null) {
            this.d = this.d.toString();
        }
        this.e = bundle.getCharSequence("subtitle");
        if (this.e != null) {
            this.e = this.e.toString();
        }
        this.f = bundle.getInt("icon_res_id");
        this.l = bundle.containsKey("has_icon_tint_color") ? bundle.getBoolean("has_icon_tint_color") : bundle.containsKey("icon_tint_color");
        this.g = bundle.getInt("icon_tint_color");
        this.h = (Bitmap) bundle.getParcelable("icon_bitmap_id");
        this.i = (Uri) bundle.getParcelable("icon_uri");
        this.j = bundle.getInt("right_icon_uri_res_id");
        this.m = bundle.containsKey("has_right_icon_tint_color") ? bundle.getBoolean("has_right_icon_tint_color") : bundle.containsKey("right_icon_tint_color");
        this.k = bundle.getInt("right_icon_tint_color");
        this.n = bundle.getBoolean("is_checked");
        this.o = (RemoteViews) bundle.getParcelable("remote_views");
        this.p = bundle.getChar("normalized_title_initial");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[MenuItem ").append(this.a).append(", type ").append(this.b).append(", extras ").append(this.c).append(", title ").append(this.d).append(", subtitle ").append(this.e).append(", iconResId ").append(this.f).append(", hasIconTintColor").append(this.l).append(", iconTintColor ").append(this.g).append(", iconBitmap ").append(this.h).append(", iconUri ").append(this.i).append(", rightIconResId ").append(this.j).append(", hasRightIconTintColor").append(this.m).append(", rightIconTintColor ").append(this.k).append(", isChecked ").append(this.n).append(", remoteViews ").append(this.o).append(", normalizedTitleInitial ").append(this.p).append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        bundle.putInt("position", this.a);
        bundle.putInt("type", this.b);
        bundle.putBundle("extras", this.c);
        bundle.putCharSequence("title", this.d);
        bundle.putCharSequence("subtitle", this.e);
        bundle.putInt("icon_res_id", this.f);
        bundle.putBoolean("has_icon_tint_color", this.l);
        bundle.putInt("icon_tint_color", this.g);
        bundle.putParcelable("icon_bitmap_id", this.h);
        bundle.putParcelable("icon_uri", this.i);
        bundle.putInt("right_icon_uri_res_id", this.j);
        bundle.putBoolean("has_right_icon_tint_color", this.m);
        bundle.putInt("right_icon_tint_color", this.k);
        bundle.putBoolean("is_checked", this.n);
        bundle.putParcelable("remote_views", this.o);
        bundle.putChar("normalized_title_initial", this.p);
    }
}
