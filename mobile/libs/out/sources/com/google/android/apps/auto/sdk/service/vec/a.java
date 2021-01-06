package com.google.android.apps.auto.sdk.service.vec;

import android.support.annotation.VisibleForTesting;
import android.support.car.CarNotConnectedException;
import com.google.android.apps.auto.sdk.service.vec.CarVendorExtensionManager.CarVendorExtensionListener;
import com.google.android.gms.car.CarVendorExtensionManager;
import java.io.IOException;

public final class a implements CarVendorExtensionManager {
    private final CarVendorExtensionManager a;
    @VisibleForTesting
    private b b;

    public a(CarVendorExtensionManager carVendorExtensionManager) {
        this.a = carVendorExtensionManager;
    }

    public final byte[] getServiceData() throws CarNotConnectedException {
        try {
            return this.a.getServiceData();
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final String getServiceName() throws CarNotConnectedException {
        try {
            return this.a.getServiceName();
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final void registerListener(CarVendorExtensionListener carVendorExtensionListener) {
        if (this.b == null || this.b.a != carVendorExtensionListener) {
            this.b = new b(this, carVendorExtensionListener);
            this.a.registerListener(this.b);
        }
    }

    public final void release() {
        this.a.release();
        this.b = null;
    }

    public final void sendData(byte[] bArr) throws CarNotConnectedException, IOException {
        try {
            this.a.sendData(bArr);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final void sendData(byte[] bArr, int i, int i2) throws CarNotConnectedException, IOException {
        try {
            this.a.sendData(bArr, i, i2);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final void unregisterListener() {
        this.a.unregisterListener();
        this.b = null;
    }
}
