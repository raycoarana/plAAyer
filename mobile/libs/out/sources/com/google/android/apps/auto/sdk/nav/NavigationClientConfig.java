package com.google.android.apps.auto.sdk.nav;

import android.os.Bundle;
import android.os.Parcelable.Creator;
import com.google.android.apps.auto.sdk.a;
import com.google.android.apps.auto.sdk.b;

public class NavigationClientConfig extends a {
    public static final Creator<NavigationClientConfig> CREATOR = new b(NavigationClientConfig.class);
    private int a;

    public NavigationClientConfig() {
    }

    public NavigationClientConfig(int i) throws IllegalArgumentException {
        if (i <= 0) {
            throw new IllegalArgumentException("Invalid version. Must be > 0. Version = " + i);
        }
        this.a = i;
    }

    public int getClientVersion() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        this.a = bundle.getInt("version");
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        bundle.putInt("version", this.a);
    }
}
