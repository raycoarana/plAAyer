package com.google.android.apps.auto.sdk.service.a.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.car.CarNotConnectedException;
import android.support.car.navigation.CarNavigationStatusManager;
import android.support.car.navigation.CarNavigationStatusManager.CarNavigationCallback;
import java.io.ByteArrayOutputStream;

public final class a extends CarNavigationStatusManager {
    private final com.google.android.gms.car.CarNavigationStatusManager a;
    @VisibleForTesting
    private b b = new b(this);

    public a(com.google.android.gms.car.CarNavigationStatusManager carNavigationStatusManager) throws CarNotConnectedException {
        this.a = carNavigationStatusManager;
        try {
            this.a.registerListener(this.b);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final void addListener(CarNavigationCallback carNavigationCallback) throws CarNotConnectedException {
        b bVar = this.b;
        bVar.a = carNavigationCallback;
        if (bVar.b != null) {
            bVar.a.onInstrumentClusterStarted(bVar.c, bVar.b);
        }
    }

    public final void onCarDisconnected() {
    }

    public final void removeListener() {
        this.a.unregisterListener();
    }

    public final void sendEvent(int i, Bundle bundle) throws CarNotConnectedException {
        throw new UnsupportedOperationException("use deprecated sendNavigationTurn*Event methods");
    }

    public final void sendNavigationStatus(int i) throws CarNotConnectedException {
        try {
            this.a.sendNavigationStatus(i);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final void sendNavigationTurnDistanceEvent(int i, int i2, int i3, int i4) throws CarNotConnectedException {
        int i5;
        switch (i4) {
            case 1:
                i5 = 1;
                break;
            case 2:
                i5 = 2;
                break;
            case 4:
                i5 = 4;
                break;
            case 6:
                i5 = 6;
                break;
            case 7:
                i5 = 7;
                break;
            default:
                throw new IllegalArgumentException("The units provided are not supported.");
        }
        try {
            this.a.sendNavigationTurnDistanceEvent(i, i2, i3, i5);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final void sendNavigationTurnEvent(int i, CharSequence charSequence, int i2, int i3, int i4) throws CarNotConnectedException {
        sendNavigationTurnEvent(i, charSequence == null ? null : charSequence.toString(), i2, i3, null, i4);
    }

    public final void sendNavigationTurnEvent(int i, CharSequence charSequence, int i2, int i3, Bitmap bitmap, int i4) throws CarNotConnectedException {
        byte[] bArr;
        String str = null;
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
            bArr = byteArrayOutputStream.toByteArray();
        } else {
            bArr = null;
        }
        if (charSequence != null) {
            str = charSequence.toString();
        }
        try {
            this.a.sendNavigationTurnEvent(i, str, i2, i3, bArr, i4);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }
}
