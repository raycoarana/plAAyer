package com.google.android.apps.auto.sdk.nav.state;

import android.os.Bundle;
import android.os.Parcelable.Creator;
import com.google.android.apps.auto.sdk.a;
import com.google.android.apps.auto.sdk.b;

public class CarInstrumentClusterConfig extends a {
    public static final Creator<CarInstrumentClusterConfig> CREATOR = new b(CarInstrumentClusterConfig.class);
    private int a;
    private int b;
    private int c;
    private int d;
    private boolean e;

    public int getImageDepthBits() {
        return this.d;
    }

    public int getImageHeight() {
        return this.c;
    }

    public int getImageWidth() {
        return this.b;
    }

    public int getMinMessageIntervalMs() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        this.a = bundle.getInt("min_interval");
        this.b = bundle.getInt("img_width");
        this.c = bundle.getInt("img_height");
        this.d = bundle.getInt("img_depth");
        this.e = bundle.getBoolean("supports_images");
    }

    public boolean supportsImages() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        bundle.putInt("min_interval", this.a);
        bundle.putInt("img_width", this.b);
        bundle.putInt("img_height", this.c);
        bundle.putInt("img_depth", this.d);
        bundle.putBoolean("supports_images", this.e);
    }
}
