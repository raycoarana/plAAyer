package com.google.android.apps.auto.sdk.service.a;

import android.support.car.hardware.CarSensorManager.OnSensorChangedListener;
import com.google.android.apps.auto.sdk.service.a.c.a;
import com.google.android.apps.auto.sdk.service.a.c.d;

final class g implements d<OnSensorChangedListener, h> {
    private /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public final /* synthetic */ a a(Object obj) {
        return new h(this.a, (OnSensorChangedListener) obj);
    }
}
