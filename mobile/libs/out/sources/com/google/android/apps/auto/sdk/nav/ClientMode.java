package com.google.android.apps.auto.sdk.nav;

import android.os.Bundle;
import android.os.Parcelable.Creator;
import com.google.android.apps.auto.sdk.a;
import com.google.android.apps.auto.sdk.b;

public class ClientMode extends a {
    public static final Creator<ClientMode> CREATOR = new b(ClientMode.class);
    private int a;

    public ClientMode() {
    }

    public ClientMode(int i) throws IllegalArgumentException {
        if (i == 1 || i == 2) {
            this.a = i;
            return;
        }
        throw new IllegalArgumentException("Invalid value for mode. Must be AndroidAutoMode.CAR or AndroidAutoMode.PHONE");
    }

    public int getMode() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        this.a = bundle.getInt("mode");
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        bundle.putInt("mode", this.a);
    }
}
