package com.google.android.apps.auto.sdk.service.a.b;

import android.support.annotation.VisibleForTesting;
import android.support.car.navigation.CarNavigationInstrumentCluster;
import android.support.car.navigation.CarNavigationStatusManager.CarNavigationCallback;
import com.google.android.gms.car.CarNavigationStatusManager.CarNavigationStatusListener;

@VisibleForTesting
final class b implements CarNavigationStatusListener {
    CarNavigationCallback a;
    CarNavigationInstrumentCluster b;
    a c;

    b(a aVar) {
        this.c = aVar;
    }

    public final void onStart(int i, int i2, int i3, int i4, int i5) {
        if (i2 == 1) {
            this.b = CarNavigationInstrumentCluster.createCustomImageCluster(i, i3, i4, i5);
        } else {
            this.b = CarNavigationInstrumentCluster.createCluster(i);
        }
        if (this.a != null) {
            this.a.onInstrumentClusterStarted(this.c, this.b);
        }
    }

    public final void onStop() {
        if (this.a != null) {
            this.a.onInstrumentClusterStopped(this.c);
        }
    }
}
