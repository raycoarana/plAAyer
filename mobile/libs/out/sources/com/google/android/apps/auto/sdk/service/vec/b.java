package com.google.android.apps.auto.sdk.service.vec;

import android.support.annotation.VisibleForTesting;
import com.google.android.apps.auto.sdk.service.a.c.a;
import com.google.android.apps.auto.sdk.service.vec.CarVendorExtensionManager.CarVendorExtensionListener;
import com.google.android.gms.car.CarVendorExtensionManager;

@VisibleForTesting
final class b implements a<CarVendorExtensionListener>, CarVendorExtensionManager.CarVendorExtensionListener {
    CarVendorExtensionListener a;
    private a b;

    public b(a aVar, CarVendorExtensionListener carVendorExtensionListener) {
        this.a = carVendorExtensionListener;
        this.b = aVar;
    }

    public final /* synthetic */ Object a() {
        return this.a;
    }

    public final void onData(byte[] bArr) {
        this.a.onData(this.b, bArr);
    }
}
