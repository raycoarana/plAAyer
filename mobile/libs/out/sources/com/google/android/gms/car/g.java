package com.google.android.gms.car;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.a.a.a.a.a.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class g {
    private static final List<String> a = Collections.unmodifiableList(Arrays.asList(new String[]{"com.google.android.projection.bumblebee", "com.google.android.projection.gearhead"}));

    public static String a(Context context) {
        return a(context, !"user".equals(Build.TYPE));
    }

    private static String a(Context context, boolean z) {
        ArrayList arrayList = null;
        if (a.contains(context.getPackageName())) {
            return context.getPackageName();
        }
        ComponentName resolveActivity = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.CAR_DOCK").resolveActivity(context.getPackageManager());
        if (resolveActivity != null && a.contains(resolveActivity.getPackageName())) {
            try {
                String packageName = resolveActivity.getPackageName();
                i.a(context, packageName, z);
                return packageName;
            } catch (NameNotFoundException | SecurityException e) {
            }
        }
        for (String str : a) {
            try {
                i.a(context, str, z);
                return str;
            } catch (NameNotFoundException e2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(e2);
            }
        }
        IllegalStateException illegalStateException = new IllegalStateException("Android Auto is not installed!");
        if (arrayList != null && !arrayList.isEmpty()) {
            if (VERSION.SDK_INT >= 19) {
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    a.a(illegalStateException, (Exception) obj);
                }
            } else {
                illegalStateException.initCause((Throwable) arrayList.get(arrayList.size() - 1));
            }
        }
        throw illegalStateException;
    }

    public static Context b(Context context) {
        return b(context, !"user".equals(Build.TYPE));
    }

    private static Context b(Context context, boolean z) {
        String a2 = a(context, z);
        if (context.getPackageName().equals(a2)) {
            return context;
        }
        try {
            return context.createPackageContext(a2, 3);
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(a2);
            throw new IllegalStateException(valueOf.length() != 0 ? "NameNotFoundException looking up ".concat(valueOf) : new String("NameNotFoundException looking up "), e);
        }
    }
}
