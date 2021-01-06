package com.google.android.apps.auto.sdk;

import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class ac {
    protected String a;
    private Object b;

    public ac(ad adVar, String str, Object... objArr) {
        Constructor constructor;
        int i = 0;
        try {
            int a2 = ad.a(adVar.b());
            int a3 = ad.a(adVar.a());
            if (a2 > a3) {
                String packageName = adVar.a().getPackageName();
                String packageName2 = adVar.b().getPackageName();
                String str2 = adVar.a().getPackageManager().getPackageInfo(packageName, 0).versionName;
                Log.w("CSL.RemoteClass", new StringBuilder(String.valueOf(packageName).length() + 78 + String.valueOf(str2).length() + String.valueOf(packageName2).length()).append("Older version of Android Auto detected.").append(packageName).append("(").append(str2).append(", api=").append(a3).append(")\n").append(packageName2).append("(api=  ").append(a2).append(")").toString());
            }
        } catch (Exception e) {
            Log.e("CSL.RemoteClass", "Error extracting SDK version, you may face runtime errors", e);
        }
        this.a = str;
        Class a4 = adVar.a(str);
        Constructor[] constructors = a4.getConstructors();
        int length = constructors.length;
        while (true) {
            if (i >= length) {
                constructor = null;
                break;
            }
            constructor = constructors[i];
            if (objArr.length == constructor.getParameterTypes().length) {
                break;
            }
            i++;
        }
        if (constructor == null) {
            throw new IllegalStateException("Cannot find SDK entry constructor.");
        }
        try {
            this.b = constructor.newInstance(objArr);
            a(a4.getDeclaredMethods());
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            Log.wtf("CSL.RemoteClass", "Unable to load SDK entry class.", e2);
            throw new IllegalStateException("Unable to load SDK entry class.", e2);
        }
    }

    /* access modifiers changed from: protected */
    public final Object a(Method method, Object... objArr) {
        if (method == null) {
            Log.e("CSL.RemoteClass", "Error invoking a null method.  Ignored.", new Exception());
            return null;
        }
        try {
            return method.invoke(this.b, objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            String valueOf = String.valueOf(method.getName());
            Log.e("CSL.RemoteClass", valueOf.length() != 0 ? "Error invoking: ".concat(valueOf) : new String("Error invoking: "), e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(Method[] methodArr);
}
