package com.google.android.gms.car;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.car.CarActivityServiceProxy.ServiceCallbacks;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class d extends Service implements ServiceCallbacks {
    private CarActivityServiceProxy a;

    /* access modifiers changed from: protected */
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.a.dump(fileDescriptor, printWriter, strArr);
    }

    public abstract Class<? extends c> getCarActivity();

    public int getHandledConfigChanges() {
        return 0;
    }

    public IBinder onBind(Intent intent) {
        return this.a.onBind(intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.a.onConfigurationChanged(configuration);
    }

    public void onCreate() {
        super.onCreate();
        if (this.a == null) {
            try {
                this.a = new a().a(this);
            } catch (b e) {
                Log.e("CAR.PROJECTION", "Error loading car activity host", e);
                throw new RuntimeException(e);
            }
        }
        this.a.onCreate(this, this);
    }

    public void onDestroy() {
        this.a.onDestroy();
        super.onDestroy();
    }

    public void onLowMemory() {
        this.a.onLowMemory();
    }

    public boolean onUnbind(Intent intent) {
        return this.a.onUnbind(intent);
    }
}
