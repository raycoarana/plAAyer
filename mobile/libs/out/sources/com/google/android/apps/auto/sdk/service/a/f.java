package com.google.android.apps.auto.sdk.service.a;

import android.support.annotation.VisibleForTesting;
import android.support.car.CarNotConnectedException;
import android.support.car.hardware.CarSensorConfig;
import android.support.car.hardware.CarSensorEvent;
import android.support.car.hardware.CarSensorManager;
import android.support.car.hardware.CarSensorManager.OnSensorChangedListener;
import android.util.Log;
import com.google.android.apps.auto.sdk.service.a.c.b;
import com.google.android.gms.car.CarSensorManager.RawEventData;
import java.util.ArrayList;
import java.util.List;

public final class f extends CarSensorManager {
    private final com.google.android.gms.car.CarSensorManager a;
    private ArrayList<Integer> b;
    private int[] c;
    @VisibleForTesting
    private b<OnSensorChangedListener, h> d = new b<>(new g(this));

    public f(com.google.android.gms.car.CarSensorManager carSensorManager) {
        this.a = carSensorManager;
    }

    private final List<Integer> a() throws CarNotConnectedException {
        if (this.b != null) {
            return this.b;
        }
        this.b = new ArrayList<>();
        try {
            int[] supportedSensors = this.a.getSupportedSensors();
            if (supportedSensors != null) {
                for (int valueOf : supportedSensors) {
                    this.b.add(Integer.valueOf(valueOf));
                }
            }
            return this.b;
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            Log.e("CSL.CarSensorManagerGms", "Car Not Connected", e);
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final boolean addListener(OnSensorChangedListener onSensorChangedListener, int i, int i2) throws CarNotConnectedException, IllegalArgumentException {
        if (!isSensorSupported(i)) {
            return false;
        }
        try {
            return this.a.registerListener((h) this.d.a(Integer.valueOf(i), onSensorChangedListener), i, i2);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            this.d.b(Integer.valueOf(i), onSensorChangedListener);
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final CarSensorEvent getLatestSensorEvent(int i) throws CarNotConnectedException {
        try {
            RawEventData latestSensorEvent = this.a.getLatestSensorEvent(i);
            return new CarSensorEvent(latestSensorEvent.sensorType, latestSensorEvent.timeStamp, latestSensorEvent.floatValues, latestSensorEvent.byteValues, (long[]) null);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            Log.e("CSL.CarSensorManagerGms", "Car Not Connected", e);
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public final CarSensorConfig getSensorConfig(int i) throws CarNotConnectedException {
        throw new UnsupportedOperationException("getSensorConfig not supported in projection");
    }

    public final int[] getSupportedSensors() throws CarNotConnectedException {
        if (this.c == null) {
            List a2 = a();
            this.c = new int[a2.size()];
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= a2.size()) {
                    break;
                }
                this.c[i2] = ((Integer) a2.get(i2)).intValue();
                i = i2 + 1;
            }
        }
        return this.c;
    }

    public final boolean isSensorSupported(int i) throws CarNotConnectedException {
        return a().contains(Integer.valueOf(i));
    }

    public final void onCarDisconnected() {
        b<OnSensorChangedListener, h> bVar = this.d;
        synchronized (bVar.b) {
            bVar.b.clear();
            bVar.a.clear();
        }
    }

    public final void removeListener(OnSensorChangedListener onSensorChangedListener) {
        this.a.unregisterListener((h) this.d.a(onSensorChangedListener));
    }

    public final void removeListener(OnSensorChangedListener onSensorChangedListener, int i) {
        this.a.unregisterListener((h) this.d.b(Integer.valueOf(i), onSensorChangedListener), i);
    }
}
