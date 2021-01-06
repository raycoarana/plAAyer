package com.google.android.apps.auto.sdk.service;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.car.Car;
import android.support.car.CarNotConnectedException;
import android.support.car.CarServiceLoader;
import android.support.car.CarServiceLoader.CarConnectionCallbackProxy;
import android.util.Log;
import com.google.android.apps.auto.sdk.service.a.e;
import com.google.android.apps.auto.sdk.service.a.f;
import com.google.android.gms.car.CarApi;
import com.google.android.gms.car.CarApi.CarConnectionCallback;
import com.google.android.gms.car.CarApiConnection;
import com.google.android.gms.car.CarApiConnection.ApiConnectionCallback;
import com.google.android.gms.car.CarAudioManager;
import com.google.android.gms.car.CarInfoManager;
import com.google.android.gms.car.CarMessageManager;
import com.google.android.gms.car.CarNavigationStatusManager;
import com.google.android.gms.car.CarNotSupportedException;
import com.google.android.gms.car.CarSensorManager;
import com.google.android.gms.car.g;
import javax.annotation.concurrent.GuardedBy;

@Keep
public class CarServiceLoaderGms extends CarServiceLoader {
    private static final String TAG = "CAR.SVC.LOADER.GMS";
    @VisibleForTesting
    final a mApiConnectionCallback = new a(this, 0);
    private final com.google.android.gms.car.a mApiFactory = new com.google.android.gms.car.a();
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public CarApi mCarApi;
    @VisibleForTesting
    final b mCarApiCallback = new b(this, 0);
    /* access modifiers changed from: private */
    @GuardedBy("this")
    public CarApiConnection mCarApiConnection;
    private final a mUtil = new a();

    final class a implements ApiConnectionCallback {
        private a() {
        }

        /* synthetic */ a(CarServiceLoaderGms carServiceLoaderGms, byte b) {
            this();
        }

        private final void a() {
            synchronized (CarServiceLoaderGms.this) {
                CarServiceLoaderGms.this.tearDownCarApi();
            }
            CarServiceLoaderGms.this.getConnectionCallback().onDisconnected();
        }

        public final void onConnected() {
            synchronized (CarServiceLoaderGms.this) {
                if (CarServiceLoaderGms.this.mCarApiConnection != null) {
                    CarServiceLoaderGms.this.tearDownCarApi();
                    CarServiceLoaderGms.this.mCarApi = CarServiceLoaderGms.this.mCarApiConnection.getCarApi();
                    if (!CarServiceLoaderGms.this.mCarApi.isConnectedToCar()) {
                        CarServiceLoaderGms.this.getConnectionCallback().onDisconnected();
                    }
                    CarServiceLoaderGms.this.mCarApi.registerCarConnectionListener(CarServiceLoaderGms.this.mCarApiCallback);
                }
            }
        }

        public final void onConnectionFailed() {
            a();
        }

        public final void onConnectionSuspended() {
            a();
        }
    }

    final class b implements CarConnectionCallback {
        private b() {
        }

        /* synthetic */ b(CarServiceLoaderGms carServiceLoaderGms, byte b) {
            this();
        }

        public final void onConnected(int i) {
            CarServiceLoaderGms.this.getConnectionCallback().onConnected();
        }

        public final void onDisconnected() {
            CarServiceLoaderGms.this.getConnectionCallback().onDisconnected();
        }
    }

    public CarServiceLoaderGms(@NonNull Context context, @NonNull CarConnectionCallbackProxy carConnectionCallbackProxy, @NonNull Handler handler) {
        super(context, carConnectionCallbackProxy, handler);
        try {
            g.a(context);
        } catch (Exception e) {
            throw new IllegalArgumentException("A valid Android Auto package must be installed", e);
        }
    }

    private void createApiConnectionInstance() {
        synchronized (this) {
            tearDownCarApiConnection();
            try {
                this.mCarApiConnection = this.mApiFactory.a(getContext(), this.mApiConnectionCallback, getEventHandler().getLooper());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Object getCarManagerInternal(String str) throws CarNotConnectedException {
        Object obj;
        synchronized (this) {
            char c = 65535;
            try {
                switch (str.hashCode()) {
                    case -1853877803:
                        if (str.equals("car_navigation_service")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1527548130:
                        if (str.equals(CarVendorExtensionManagerLoader.VENDOR_EXTENSION_LOADER_SERVICE)) {
                            c = 5;
                            break;
                        }
                        break;
                    case -905948230:
                        if (str.equals(Car.SENSOR_SERVICE)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 3237038:
                        if (str.equals(Car.INFO_SERVICE)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 93166550:
                        if (str.equals(Car.AUDIO_SERVICE)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1830376762:
                        if (str.equals(Car.APP_FOCUS_SERVICE)) {
                            c = 3;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        obj = new e((CarInfoManager) this.mCarApi.getCarManager(str));
                        break;
                    case 1:
                        obj = new com.google.android.apps.auto.sdk.service.a.a.a((AudioManager) getContext().getSystemService(Car.AUDIO_SERVICE), (CarAudioManager) this.mCarApi.getCarManager(str));
                        break;
                    case 2:
                        obj = new f((CarSensorManager) this.mCarApi.getCarManager(str));
                        break;
                    case 3:
                        obj = new com.google.android.apps.auto.sdk.service.a.a((CarMessageManager) this.mCarApi.getCarManager(str));
                        break;
                    case 4:
                        obj = new com.google.android.apps.auto.sdk.service.a.b.a((CarNavigationStatusManager) this.mCarApi.getCarManager(str));
                        break;
                    case 5:
                        obj = new CarVendorExtensionManagerLoader(this.mCarApi);
                        break;
                    default:
                        obj = this.mUtil.a(this.mCarApi, str);
                        break;
                }
            } catch (CarNotSupportedException e) {
                obj = null;
            } catch (com.google.android.gms.car.CarNotConnectedException e2) {
                throw new CarNotConnectedException((Exception) e2);
            }
        }
        return obj;
    }

    /* access modifiers changed from: private */
    public void tearDownCarApi() {
        synchronized (this) {
            if (!(this.mCarApi == null || this.mCarApiCallback == null)) {
                try {
                    this.mCarApi.unregisterCarConnectionListener(this.mCarApiCallback);
                } catch (IllegalArgumentException | IllegalStateException e) {
                    Log.e(TAG, "Error unregistering a listener", e);
                }
            }
            this.mCarApi = null;
        }
        return;
    }

    private void tearDownCarApiConnection() {
        synchronized (this) {
            if (this.mCarApiConnection != null) {
                this.mCarApiConnection.disconnect();
            }
            this.mCarApiConnection = null;
        }
    }

    public void connect() {
        synchronized (this) {
            if (!isConnected()) {
                createApiConnectionInstance();
                this.mCarApiConnection.connect();
            }
        }
    }

    public void disconnect() {
        synchronized (this) {
            tearDownCarApi();
            tearDownCarApiConnection();
        }
    }

    public int getCarConnectionType() throws CarNotConnectedException {
        int i;
        synchronized (this) {
            if (!isConnected()) {
                throw new CarNotConnectedException();
            }
            try {
                switch (this.mCarApi.getCarConnectionType()) {
                    case 0:
                        i = 0;
                        break;
                    case 1:
                        i = 1;
                        break;
                    case 2:
                        i = 2;
                        break;
                    case 3:
                        i = 3;
                        break;
                    case 4:
                        i = 4;
                        break;
                    default:
                        i = -1;
                        break;
                }
            } catch (com.google.android.gms.car.CarNotConnectedException e) {
                throw new CarNotConnectedException((Exception) e);
            }
        }
        return i;
    }

    public Object getCarManager(String str) throws CarNotConnectedException {
        Object carManagerInternal;
        synchronized (this) {
            if (!isConnected()) {
                throw new CarNotConnectedException();
            }
            try {
                carManagerInternal = getCarManagerInternal(str);
            } catch (CarNotConnectedException e) {
                throw new CarNotConnectedException((Exception) e);
            }
        }
        return carManagerInternal;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean isApiNull() {
        boolean z;
        synchronized (this) {
            z = this.mCarApi == null;
        }
        return z;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this) {
            z = this.mCarApi != null && this.mCarApi.isConnectedToCar();
        }
        return z;
    }
}
