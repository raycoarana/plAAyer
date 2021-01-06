package com.google.android.apps.auto.sdk.notification;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Extender;
import android.text.TextUtils;

public class CarNotificationExtender implements Extender {
    private boolean a;
    /* access modifiers changed from: private */
    public Long b;
    /* access modifiers changed from: private */
    public CharSequence c;
    /* access modifiers changed from: private */
    public CharSequence d;
    /* access modifiers changed from: private */
    public Bitmap e;
    /* access modifiers changed from: private */
    @DrawableRes
    public int f;
    /* access modifiers changed from: private */
    @DrawableRes
    public Intent g;
    /* access modifiers changed from: private */
    @ColorInt
    public int h;
    /* access modifiers changed from: private */
    @ColorInt
    public int i;
    /* access modifiers changed from: private */
    public boolean j;

    public static class Builder {
        @NonNull
        private final CarNotificationExtender a = new CarNotificationExtender(0);

        public CarNotificationExtender build() {
            if (TextUtils.isEmpty(this.a.c)) {
                throw new IllegalArgumentException("A title is required.");
            } else if (this.a.f == 0) {
                throw new IllegalArgumentException("An action icon is required");
            } else if (!this.a.j || this.a.e != null) {
                return this.a;
            } else {
                throw new IllegalArgumentException("A thumbnail icon is required for heads up notification.");
            }
        }

        public Builder setActionIconResId(@DrawableRes int i) {
            this.a.f = i;
            return this;
        }

        public Builder setActionIntent(Intent intent) {
            this.a.g = intent;
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int i) {
            this.a.h = i;
            return this;
        }

        public Builder setContentId(long j) {
            this.a.b = Long.valueOf(j);
            return this;
        }

        public Builder setNightBackgroundColor(@ColorInt int i) {
            this.a.i = i;
            return this;
        }

        public Builder setShouldShowAsHeadsUp(boolean z) {
            this.a.j = z;
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.a.d = charSequence;
            return this;
        }

        public Builder setThumbnail(Bitmap bitmap) {
            this.a.e = bitmap;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.a.c = charSequence;
            return this;
        }
    }

    private CarNotificationExtender() {
        this.h = 0;
        this.i = 0;
    }

    /* synthetic */ CarNotificationExtender(byte b2) {
        this();
    }

    public CarNotificationExtender(@NonNull Notification notification) {
        this.h = 0;
        this.i = 0;
        Bundle bundle = NotificationCompat.getExtras(notification) == null ? null : NotificationCompat.getExtras(notification).getBundle("android.car.EXTENSIONS");
        if (bundle != null) {
            this.a = bundle.getBoolean("com.google.android.gms.car.support.CarNotificationExtender.EXTENDED");
            this.b = (Long) bundle.getSerializable("content_id");
            this.c = bundle.getCharSequence("android.title");
            this.d = bundle.getCharSequence("subtitle");
            this.e = (Bitmap) bundle.getParcelable("thumbnail");
            this.f = bundle.getInt("action_icon");
            this.g = (Intent) bundle.getParcelable("content_intent");
            this.h = bundle.getInt("app_color", 0);
            this.i = bundle.getInt("app_night_color", 0);
            this.j = bundle.getBoolean("heads_up_visibility");
        }
    }

    public android.support.v4.app.NotificationCompat.Builder extend(android.support.v4.app.NotificationCompat.Builder builder) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.car.support.CarNotificationExtender.EXTENDED", true);
        bundle.putSerializable("content_id", this.b);
        bundle.putCharSequence("android.title", this.c);
        bundle.putCharSequence("subtitle", this.d);
        bundle.putParcelable("thumbnail", this.e);
        bundle.putInt("action_icon", this.f);
        bundle.putParcelable("content_intent", this.g);
        bundle.putInt("app_color", this.h);
        bundle.putInt("app_night_color", this.i);
        bundle.putBoolean("heads_up_visibility", this.j);
        builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
        return builder;
    }

    @DrawableRes
    public int getActionIconResId() {
        return this.f;
    }

    @Nullable
    public Intent getActionIntent() {
        return this.g;
    }

    @ColorInt
    public int getBackgroundColor() {
        return this.h;
    }

    @Nullable
    public Long getContentId() {
        return this.b;
    }

    @ColorInt
    public int getNightBackgroundColor() {
        return this.i;
    }

    public boolean getShouldShowAsHeadsUp() {
        return this.j;
    }

    @Nullable
    public CharSequence getSubtitle() {
        return this.d;
    }

    public Bitmap getThumbnail() {
        return this.e;
    }

    @Nullable
    public CharSequence getTitle() {
        return this.c;
    }

    public boolean isExtended() {
        return this.a;
    }
}
