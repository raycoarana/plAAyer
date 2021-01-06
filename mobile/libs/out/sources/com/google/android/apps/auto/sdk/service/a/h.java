package com.google.android.apps.auto.sdk.service.a;

import android.support.annotation.VisibleForTesting;
import android.support.car.hardware.CarSensorEvent;
import android.support.car.hardware.CarSensorManager.OnSensorChangedListener;
import com.google.android.apps.auto.sdk.service.a.c.a;
import com.google.android.gms.car.CarSensorManager.CarSensorEventListener;

@VisibleForTesting
final class h implements a<OnSensorChangedListener>, CarSensorEventListener {
    private OnSensorChangedListener a;
    private f b;

    public h(f fVar, OnSensorChangedListener onSensorChangedListener) {
        this.a = onSensorChangedListener;
        this.b = fVar;
    }

    public final /* synthetic */ Object a() {
        return this.a;
    }

    public final void onSensorChanged(int i, long j, float[] fArr, byte[] bArr) {
        this.a.onSensorChanged(this.b, new CarSensorEvent(i, j, fArr, bArr, (long[]) null));
    }
}
