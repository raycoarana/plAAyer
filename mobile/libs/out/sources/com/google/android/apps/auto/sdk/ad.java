package com.google.android.apps.auto.sdk;

import android.content.Context;
import com.google.android.gms.car.g;
import java.lang.reflect.InvocationTargetException;

public final class ad {
    private Context a;
    private Context b;

    public ad(Context context) {
        this.a = context;
        this.b = g.b(context);
    }

    public static int a(Context context) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return ((Integer) context.getClassLoader().loadClass("com.google.android.apps.auto.sdk.SdkVersion").getDeclaredMethod("getVersion", new Class[0]).invoke(null, new Object[0])).intValue();
    }

    public final Context a() {
        return this.b;
    }

    public final Class<?> a(String str) {
        try {
            return this.b.getClassLoader().loadClass(str);
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = e;
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? "Unable to load SDK class ".concat(valueOf) : new String("Unable to load SDK class "), classNotFoundException);
        }
    }

    public final Context b() {
        return this.a;
    }
}
