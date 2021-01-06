package com.google.android.apps.auto.sdk.nav;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Extender;

public class CarNavExtender implements Extender {
    public static final int TYPE_HERO = 0;
    public static final int TYPE_NORMAL = 1;
    private boolean a;
    private Long b;
    private int c = 1;
    private CharSequence d;
    private CharSequence e;
    private CharSequence f;
    private CharSequence g;
    private CharSequence h;
    private CharSequence i;
    private Bitmap j;
    @DrawableRes
    private int k;
    @DrawableRes
    private int l;
    private Intent m;
    private PendingIntent n;
    private int o = 0;
    private int p = 0;
    private boolean q = true;
    private boolean r;
    private boolean s;

    public CarNavExtender() {
    }

    public CarNavExtender(@NonNull Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        if (extras != null) {
            Bundle bundle = extras.getBundle("android.car.EXTENSIONS");
            if (bundle != null) {
                this.a = bundle.getBoolean("com.google.android.gms.car.support.CarNavExtender.EXTENDED");
                this.b = (Long) bundle.getSerializable("content_id");
                this.c = bundle.getInt("type", 1) == 0 ? 0 : 1;
                this.d = bundle.getCharSequence("android.title");
                this.e = bundle.getCharSequence("android.title.night");
                this.f = bundle.getCharSequence("android.text");
                this.g = bundle.getCharSequence("android.text.night");
                this.h = bundle.getCharSequence("sub_text");
                this.i = bundle.getCharSequence("sub_text.night");
                this.j = (Bitmap) bundle.getParcelable("android.largeIcon");
                this.k = bundle.getInt("action_icon");
                this.l = bundle.getInt("action_icon.night");
                this.m = (Intent) bundle.getParcelable("content_intent");
                this.n = (PendingIntent) bundle.getParcelable("content_pending_intent");
                this.o = bundle.getInt("app_color", 0);
                this.p = bundle.getInt("app_night_color", 0);
                this.q = bundle.getBoolean("stream_visibility", true);
                this.r = bundle.getBoolean("heads_up_visibility");
                this.s = bundle.getBoolean("ignore_in_stream");
            }
        }
    }

    public static int getType(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        if (extras == null) {
            return 1;
        }
        Bundle bundle = extras.getBundle("android.car.EXTENSIONS");
        return (bundle == null || bundle.getInt("type", 1) != 0) ? 1 : 0;
    }

    public static boolean isExtended(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        if (extras != null) {
            Bundle bundle = extras.getBundle("android.car.EXTENSIONS");
            if (bundle != null && bundle.getBoolean("com.google.android.gms.car.support.CarNavExtender.EXTENDED")) {
                return true;
            }
        }
        return false;
    }

    public Builder extend(Builder builder) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.car.support.CarNavExtender.EXTENDED", true);
        bundle.putSerializable("content_id", this.b);
        bundle.putInt("type", this.c);
        bundle.putCharSequence("android.title", this.d);
        bundle.putCharSequence("android.title.night", this.e);
        bundle.putCharSequence("android.text", this.f);
        bundle.putCharSequence("android.text.night", this.g);
        bundle.putCharSequence("sub_text", this.h);
        bundle.putCharSequence("sub_text.night", this.i);
        bundle.putParcelable("android.largeIcon", this.j);
        bundle.putInt("action_icon", this.k);
        bundle.putInt("action_icon.night", this.l);
        bundle.putParcelable("content_intent", this.m);
        bundle.putParcelable("content_pending_intent", this.n);
        bundle.putInt("app_color", this.o);
        bundle.putInt("app_night_color", this.p);
        bundle.putBoolean("stream_visibility", this.q);
        bundle.putBoolean("heads_up_visibility", this.r);
        bundle.putBoolean("ignore_in_stream", this.s);
        builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
        return builder;
    }

    @Deprecated
    @DrawableRes
    public int getActionIcon() {
        return getActionIconDay();
    }

    @DrawableRes
    public int getActionIconDay() {
        return this.k;
    }

    @DrawableRes
    public int getActionIconNight() {
        return this.l;
    }

    @Deprecated
    public int getColor() {
        return getColorDay();
    }

    public int getColorDay() {
        return this.o;
    }

    public int getColorNight() {
        return this.p;
    }

    @Nullable
    public Long getContentId() {
        return this.b;
    }

    public Intent getContentIntent() {
        return this.m;
    }

    public PendingIntent getContentPendingIntent() {
        return this.n;
    }

    @Nullable
    @Deprecated
    public CharSequence getContentText() {
        return getContentTextDay();
    }

    @Nullable
    public CharSequence getContentTextDay() {
        return this.f;
    }

    @Nullable
    public CharSequence getContentTextNight() {
        return this.g;
    }

    @Nullable
    @Deprecated
    public CharSequence getContentTitle() {
        return getContentTitleDay();
    }

    @Nullable
    public CharSequence getContentTitleDay() {
        return this.d;
    }

    @Nullable
    public CharSequence getContentTitleNight() {
        return this.e;
    }

    public boolean getIgnoreInStream() {
        return this.s;
    }

    public Bitmap getLargeIcon() {
        return this.j;
    }

    @Deprecated
    public int getNightColor() {
        return getColorNight();
    }

    public boolean getShowAsHeadsUp() {
        return this.r;
    }

    public boolean getShowInStream() {
        return this.q;
    }

    @Nullable
    @Deprecated
    public CharSequence getSubText() {
        return getSubTextDay();
    }

    @Nullable
    public CharSequence getSubTextDay() {
        return this.h;
    }

    @Nullable
    public CharSequence getSubTextNight() {
        return this.i;
    }

    public int getType() {
        return this.c;
    }

    public boolean isExtended() {
        return this.a;
    }

    @Deprecated
    public CarNavExtender setActionIcon(@DrawableRes int i2) {
        return setActionIconDay(i2);
    }

    public CarNavExtender setActionIconDay(@DrawableRes int i2) {
        this.k = i2;
        return this;
    }

    public CarNavExtender setActionIconNight(@DrawableRes int i2) {
        this.l = i2;
        return this;
    }

    @Deprecated
    public CarNavExtender setColor(int i2) {
        return setColorDay(i2);
    }

    public CarNavExtender setColorDay(int i2) {
        this.o = i2;
        return this;
    }

    public CarNavExtender setColorNight(int i2) {
        this.p = i2;
        return this;
    }

    public CarNavExtender setContentId(long j2) {
        this.b = Long.valueOf(j2);
        return this;
    }

    public CarNavExtender setContentIntent(Intent intent) {
        this.m = intent;
        return this;
    }

    public CarNavExtender setContentPendingIntent(PendingIntent pendingIntent) {
        this.n = pendingIntent;
        return this;
    }

    @Deprecated
    public CarNavExtender setContentText(CharSequence charSequence) {
        return setContentTextDay(charSequence);
    }

    public CarNavExtender setContentTextDay(CharSequence charSequence) {
        this.f = charSequence;
        return this;
    }

    public CarNavExtender setContentTextNight(CharSequence charSequence) {
        this.g = charSequence;
        return this;
    }

    @Deprecated
    public CarNavExtender setContentTitle(CharSequence charSequence) {
        return setContentTitleDay(charSequence);
    }

    public CarNavExtender setContentTitleDay(CharSequence charSequence) {
        this.d = charSequence;
        return this;
    }

    public CarNavExtender setContentTitleNight(CharSequence charSequence) {
        this.e = charSequence;
        return this;
    }

    public CarNavExtender setIgnoreInStream(boolean z) {
        this.s = z;
        return this;
    }

    public CarNavExtender setLargeIcon(Bitmap bitmap) {
        this.j = bitmap;
        return this;
    }

    @Deprecated
    public CarNavExtender setNightColor(int i2) {
        return setColorNight(i2);
    }

    public CarNavExtender setShowAsHeadsUp(boolean z) {
        this.r = z;
        return this;
    }

    public CarNavExtender setShowInStream(boolean z) {
        this.q = z;
        return this;
    }

    @Deprecated
    public CarNavExtender setSubText(CharSequence charSequence) {
        return setSubTextDay(charSequence);
    }

    public CarNavExtender setSubTextDay(CharSequence charSequence) {
        this.h = charSequence;
        return this;
    }

    public CarNavExtender setSubTextNight(CharSequence charSequence) {
        this.i = charSequence;
        return this;
    }

    public CarNavExtender setType(int i2) {
        this.c = i2;
        return this;
    }
}
