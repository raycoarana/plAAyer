package com.google.android.gms.car;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.car.CarApiConnection.ApiConnectionCallback;

public final class a {
    private static Class<?> a;

    public a() {
        Log.i("ApiFactory", "Initialized ApiFactory to load from remote APK");
    }

    private static Class<?> b(Context context) throws Exception {
        if (a == null) {
            Context b = g.b(context.getApplicationContext());
            a = b.getClassLoader().loadClass("com.google.android.gms.car.DynamicApiFactory");
            a.getMethod("initialize", new Class[]{Context.class}).invoke(null, new Object[]{b});
        }
        return a;
    }

    public final CarActivityServiceProxy a(Context context) throws b {
        try {
            return (CarActivityServiceProxy) b(context).getMethod("newCarActivityServiceProxy", new Class[]{Context.class}).invoke(null, new Object[]{context});
        } catch (Exception e) {
            throw new b(e);
        }
    }

    public final CarApiConnection a(Context context, ApiConnectionCallback apiConnectionCallback, Looper looper) throws b {
        try {
            return (CarApiConnection) b(context).getMethod("newCarApiConnection", new Class[]{Context.class, Object.class, Looper.class}).invoke(null, new Object[]{context, apiConnectionCallback, looper});
        } catch (Exception e) {
            throw new b(e);
        }
    }
}
