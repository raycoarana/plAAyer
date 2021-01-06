package com.google.android.apps.auto.sdk;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SearchItem extends a {
    public static final Creator<SearchItem> CREATOR = new b(SearchItem.class);
    /* access modifiers changed from: private */
    public int a;
    /* access modifiers changed from: private */
    @Nullable
    public Bundle b;
    /* access modifiers changed from: private */
    @NonNull
    public CharSequence c;
    /* access modifiers changed from: private */
    @Nullable
    public CharSequence d;
    /* access modifiers changed from: private */
    @Nullable
    public CharSequence e;
    /* access modifiers changed from: private */
    @Nullable
    public CharSequence f;
    /* access modifiers changed from: private */
    @DrawableRes
    public int g;
    /* access modifiers changed from: private */
    public Bitmap h;

    public static class Builder {
        private SearchItem a = new SearchItem();

        public SearchItem build() {
            if (TextUtils.isEmpty(this.a.c)) {
                throw new IllegalArgumentException("SearchItem title must be non-empty");
            }
            boolean z = !TextUtils.isEmpty(this.a.d) || !TextUtils.isEmpty(this.a.e) || !TextUtils.isEmpty(this.a.f) || this.a.g != 0;
            if (this.a.a == 1 && z) {
                throw new IllegalArgumentException("Search suggestion can only contain title");
            } else if (this.a.getIconBitmap() == null || this.a.getIconResId() == 0) {
                return this.a;
            } else {
                throw new IllegalArgumentException("Cannot set both icon resId and bitmap");
            }
        }

        public Builder setDescription(CharSequence charSequence) {
            this.a.e = charSequence;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.a.b = bundle;
            return this;
        }

        public Builder setIconBitmap(Bitmap bitmap) {
            this.a.h = bitmap;
            return this;
        }

        public Builder setIconResId(@DrawableRes int i) {
            this.a.g = i;
            return this;
        }

        public Builder setSubDescription(CharSequence charSequence) {
            this.a.f = charSequence;
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.a.d = charSequence;
            return this;
        }

        public Builder setTitle(@NonNull CharSequence charSequence) {
            this.a.c = charSequence;
            return this;
        }

        public Builder setType(int i) {
            this.a.a = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
        public static final int SEARCH_RESULT = 0;
        public static final int SUGGESTION = 1;
    }

    SearchItem() {
    }

    @Nullable
    public CharSequence getDescription() {
        return this.e;
    }

    @Nullable
    public Bundle getExtras() {
        return this.b;
    }

    @Nullable
    public Bitmap getIconBitmap() {
        return this.h;
    }

    @DrawableRes
    public int getIconResId() {
        return this.g;
    }

    @Nullable
    public CharSequence getSubDescription() {
        return this.f;
    }

    @Nullable
    public CharSequence getSubtitle() {
        return this.d;
    }

    @NonNull
    public CharSequence getTitle() {
        return this.c;
    }

    public int getType() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        this.a = bundle.getInt("type");
        this.b = bundle.getBundle("extras");
        this.c = bundle.getCharSequence("title");
        if (this.c != null) {
            this.c = this.c.toString();
        }
        this.d = bundle.getCharSequence("subtitle");
        if (this.d != null) {
            this.d = this.d.toString();
        }
        this.e = bundle.getCharSequence("description");
        this.f = bundle.getCharSequence("sub_description");
        this.g = bundle.getInt("icon_res_id");
        this.h = (Bitmap) bundle.getParcelable("icon_bitmap_id");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[SearchItem type ").append(this.a).append(", extras ").append(this.b).append(", title ").append(this.c).append(", subtitle ").append(this.d).append(", description ").append(this.e).append(", sub-description ").append(this.f).append(", iconResId ").append(this.g).append(", iconBitmap ").append(this.h).append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        bundle.putInt("type", this.a);
        bundle.putBundle("extras", this.b);
        bundle.putCharSequence("title", this.c);
        bundle.putCharSequence("subtitle", this.d);
        bundle.putCharSequence("description", this.e);
        bundle.putCharSequence("sub_description", this.f);
        bundle.putInt("icon_res_id", this.g);
        bundle.putParcelable("icon_bitmap_id", this.h);
    }
}
