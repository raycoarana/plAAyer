package com.google.android.apps.auto.sdk.nav;

import android.os.Bundle;
import android.os.Parcelable.Creator;
import com.google.android.apps.auto.sdk.a;
import com.google.android.apps.auto.sdk.b;

public class NavigationProviderConfig extends a {
    public static final Creator<NavigationProviderConfig> CREATOR = new b(NavigationProviderConfig.class);
    private int a;
    private int b;

    public NavigationProviderConfig() {
    }

    public NavigationProviderConfig(int i, int i2) throws IllegalArgumentException {
        if (i > i2) {
            throw new IllegalArgumentException("Min version was greater than max version. Min version must be less than max version");
        } else if (i <= 0) {
            throw new IllegalArgumentException("Min version was <= 0. Min version must be > 0");
        } else {
            this.a = i;
            this.b = i2;
        }
    }

    public int getMaxVersion() {
        return this.b;
    }

    public int getMinVersion() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        this.a = bundle.getInt("MIN_VERSION");
        this.b = bundle.getInt("MAX_VERSION");
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        bundle.putInt("MIN_VERSION", this.a);
        bundle.putInt("MAX_VERSION", this.b);
    }
}
