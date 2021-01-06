package com.google.android.apps.auto.sdk.vanagon;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.car.g;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PhoneSysUiClient {
    public static final int FACET_HOME = 1;
    public static final int FACET_MAPS = 4;
    public static final int FACET_MEDIA = 2;
    public static final int FACET_OTHER = 5;
    public static final int FACET_PHONE = 3;
    public static final int FACET_UNKNOWN = 0;
    public static String sTestOnlyPackageNameOverride = null;
    public static String sTestOnlySystemUIClassName = null;
    public static boolean sTestOnlyUseThreadClassLoader = false;
    private Activity a;
    private Object b;
    private Class<?> c;
    /* access modifiers changed from: private */
    public Class<?> d;
    private Method e;
    private Method f;
    private Method g;
    private Method h;
    private Method i;
    private Method j;
    private Method k;
    private Method l;
    private Method m;
    private Method n;
    private Method o;
    private Method p;
    private Method q;
    private Method r;
    private Method s;
    private Method t;
    private Method u;
    private Method v;
    private Method w;
    /* access modifiers changed from: private */
    public AndroidAutoStateCallback x;
    private final BroadcastReceiver y = new c(this);

    public interface AndroidAutoStateCallback {
        void onEnterPhoneMode();

        void onExitPhoneMode();
    }

    public interface ScreenshotProvider {

        public interface OnCompleteListener {
            void onScreenshotComplete(@Nullable Bitmap bitmap);
        }

        void getScreenshot(OnCompleteListener onCompleteListener);
    }

    public PhoneSysUiClient(Activity activity) {
        this.a = activity;
    }

    private final ViewGroup a() {
        ViewGroup activityRootView = getActivityRootView();
        if (this.b != null) {
            throw new IllegalStateException("Install can only be called once.");
        }
        this.b = b();
        if (this.b == null) {
            a("CarModeSysUI could not be created. fallback to regular Android system UI");
            return activityRootView;
        }
        a(this.e, Boolean.valueOf(true));
        return (ViewGroup) a(this.o, new Object[0]);
    }

    private final Object a(Method method, Object... objArr) {
        if (method == null) {
            Log.d("GH.PhoneSysUiClient", "Method is not loaded. no op and return null.");
            return null;
        }
        if (c()) {
            try {
                return method.invoke(this.b, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
                a(e2);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static RuntimeException a(Exception exc) {
        String valueOf = String.valueOf(exc.toString());
        Log.e("GH.PhoneSysUiClient", valueOf.length() != 0 ? "PhoneSysUiClient failure: ".concat(valueOf) : new String("PhoneSysUiClient failure: "));
        if (exc instanceof InvocationTargetException) {
            String valueOf2 = String.valueOf(((InvocationTargetException) exc).getCause());
            Log.e("GH.PhoneSysUiClient", new StringBuilder(String.valueOf(valueOf2).length() + 32).append("Invocation exception caused by: ").append(valueOf2).toString());
        }
        String valueOf3 = String.valueOf(exc.toString());
        throw new IllegalStateException(valueOf3.length() != 0 ? "Fatal failure interacting with VnClient: ".concat(valueOf3) : new String("Fatal failure interacting with VnClient: "));
    }

    private static String a(Context context) {
        return sTestOnlyPackageNameOverride != null ? sTestOnlyPackageNameOverride : g.a(context);
    }

    private static void a(String str) {
        if (Log.isLoggable("GH.PhoneSysUiClient", 2)) {
            Log.v("GH.PhoneSysUiClient", str);
        }
    }

    private final Object b() {
        boolean z = false;
        if (VERSION.SDK_INT >= 21) {
            z = true;
        }
        if (!z) {
            a("OS not supported.");
            return null;
        }
        try {
            Context createPackageContext = this.a.createPackageContext(a((Context) this.a), 3);
            ClassLoader classLoader = sTestOnlyUseThreadClassLoader ? Thread.currentThread().getContextClassLoader() : createPackageContext.getClassLoader();
            try {
                Class loadClass = classLoader.loadClass(sTestOnlySystemUIClassName != null ? sTestOnlySystemUIClassName : "com.google.android.gearhead.vanagon.thirdparty.CarModeSysUI");
                try {
                    this.e = loadClass.getMethod("onCreate", new Class[]{Boolean.TYPE});
                    this.f = loadClass.getMethod("onDestroy", new Class[0]);
                    this.g = loadClass.getMethod("onStart", new Class[0]);
                    this.h = loadClass.getMethod("onStop", new Class[0]);
                    this.i = loadClass.getMethod("onResume", new Class[0]);
                    this.l = loadClass.getMethod("onWindowFocusChanged", new Class[]{Boolean.TYPE});
                    this.k = loadClass.getMethod("onConfigurationChanged", new Class[]{Configuration.class});
                    this.j = loadClass.getMethod("onPause", new Class[0]);
                    this.n = loadClass.getMethod("getSystemUIView", new Class[0]);
                    this.o = loadClass.getMethod("getAppRootViewGroup", new Class[0]);
                    this.m = loadClass.getMethod("setEnabled", new Class[]{Boolean.TYPE});
                    this.p = loadClass.getMethod("setSystemUiVisibility", new Class[]{Integer.TYPE});
                    this.q = loadClass.getMethod("showDownButton", new Class[]{Boolean.TYPE});
                    this.s = loadClass.getMethod("showFacetNavigation", new Class[]{Boolean.TYPE});
                    this.t = loadClass.getMethod("showFacetNavigation", new Class[]{Boolean.TYPE, Integer.TYPE, Integer.TYPE});
                    this.r = loadClass.getMethod("setSystemUiFlagLightStatusBar", new Class[]{Boolean.TYPE});
                    this.u = loadClass.getMethod("setPrettyImmersiveStickyTransitions", new Class[]{Boolean.TYPE});
                    try {
                        this.v = loadClass.getMethod("suppressHomeButtonExit", new Class[]{Boolean.TYPE});
                        this.c = classLoader.loadClass("com.google.android.apps.auto.sdk.vanagon.PhoneSysUiClient$ScreenshotProvider");
                        this.d = classLoader.loadClass("com.google.android.apps.auto.sdk.vanagon.PhoneSysUiClient$ScreenshotProvider$OnCompleteListener");
                        this.w = loadClass.getMethod("setScreenshotProvider", new Class[]{this.c});
                    } catch (ClassNotFoundException | NoSuchMethodException e2) {
                        Log.w("GH.PhoneSysUiClient", "Optional method is not loaded.", e2);
                    }
                    try {
                        return loadClass.getDeclaredConstructor(new Class[]{Context.class, Context.class}).newInstance(new Object[]{this.a, createPackageContext});
                    } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e3) {
                        Log.e("GH.PhoneSysUiClient", "Could not construct control.");
                        throw a(e3);
                    }
                } catch (Exception e4) {
                    Log.e("GH.PhoneSysUiClient", "Could not retrieve all required methods.");
                    throw a(e4);
                }
            } catch (ClassNotFoundException e5) {
                Log.e("GH.PhoneSysUiClient", "Could not find CarModeSysUI. Extremely old Android Auto?");
                return null;
            }
        } catch (NameNotFoundException | IllegalStateException e6) {
            a("Android Auto package not found.");
            return null;
        }
    }

    private static void b(String str) {
        if (Log.isLoggable("GH.PhoneSysUiClient", 3)) {
            Log.d("GH.PhoneSysUiClient", str);
        }
    }

    private final boolean c() {
        if (this.b == null) {
            a("SystemUI not installed");
        }
        return this.b != null;
    }

    public static boolean isAndroidAutoRunning(Context context) {
        boolean z;
        if (((UiModeManager) context.getSystemService("uimode")).getCurrentModeType() == 3) {
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.CAR_DOCK"), 0);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                b("No car dock app installed.");
                z = false;
            } else {
                String valueOf = String.valueOf(resolveActivity.activityInfo.toString());
                b(valueOf.length() != 0 ? "activityInfo: ".concat(valueOf) : new String("activityInfo: "));
                String valueOf2 = String.valueOf(resolveActivity.activityInfo.packageName);
                b(valueOf2.length() != 0 ? "packageName: ".concat(valueOf2) : new String("packageName: "));
                if (a(context).equals(resolveActivity.activityInfo.packageName)) {
                    z = true;
                } else {
                    b("Non-vanagon car dock app installed.");
                    z = false;
                }
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public ViewGroup getActivityRootView() {
        return (ViewGroup) this.a.findViewById(16908290);
    }

    public View getSystemRoot() {
        a("getSystemRoot()");
        return (View) a(this.n, new Object[0]);
    }

    public void onConfigurationChanged(Configuration configuration) {
        a("onConfigurationChanged");
        a(this.k, configuration);
    }

    public ViewGroup onCreate(boolean z) {
        a("onCreate");
        return !z ? getActivityRootView() : a();
    }

    public void onDestroy() {
        a("onDestroy");
        a(this.f, new Object[0]);
    }

    public void onPause() {
        a("onPause");
        a(this.j, new Object[0]);
    }

    public void onResume() {
        a("onResume");
        a(this.i, new Object[0]);
    }

    public void onStart() {
        a("onStart");
        a(this.g, new Object[0]);
    }

    public void onStop() {
        a("onStop");
        a(this.h, new Object[0]);
    }

    public void onWindowFocusChanged(boolean z) {
        a("onWindowFocusChanged: " + z);
        a(this.l, Boolean.valueOf(z));
    }

    public void setAndroidAutoStateListener(@Nullable AndroidAutoStateCallback androidAutoStateCallback) {
        if (this.x == null && androidAutoStateCallback != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(UiModeManager.ACTION_ENTER_CAR_MODE);
            intentFilter.addAction(UiModeManager.ACTION_EXIT_CAR_MODE);
            this.a.registerReceiver(this.y, intentFilter);
        }
        if (this.x != null && androidAutoStateCallback == null) {
            this.a.unregisterReceiver(this.y);
        }
        this.x = androidAutoStateCallback;
    }

    public void setEnabled(boolean z) {
        a("setEnabled: " + z);
        if (!c() && z) {
            a();
        }
        if (c()) {
            a(this.m, Boolean.valueOf(z));
        }
    }

    public void setPrettyImmersiveStickyTransitions(boolean z) {
        a("setPrettyImmersiveStickyTransitions: " + z);
        a(this.u, Boolean.valueOf(z));
    }

    public void setScreenshotProvider(ScreenshotProvider screenshotProvider) {
        String valueOf = String.valueOf(screenshotProvider);
        a(new StringBuilder(String.valueOf(valueOf).length() + 23).append("setScreenshotProvider: ").append(valueOf).toString());
        if (this.c == null || this.d == null) {
            Log.d("GH.PhoneSysUiClient", "Method is not loaded. no op and return.");
            return;
        }
        Method method = this.w;
        ClassLoader classLoader = this.c.getClassLoader();
        Class<?> cls = this.c;
        Class[] clsArr = {cls};
        a(method, Proxy.newProxyInstance(classLoader, clsArr, new a(this, screenshotProvider)));
    }

    public void setSystemUiVisibility(int i2) {
        a("setSystemUiVisibility: " + i2);
        a(this.p, Integer.valueOf(i2));
    }

    public void setTintStatusBarIcons(boolean z) {
        a("setTintStatusBarIcons: " + z);
        a(this.r, Boolean.valueOf(z));
    }

    @Deprecated
    public void showDownButton(boolean z) {
        a("showDownButton()");
        a(this.q, Boolean.valueOf(z));
    }

    @Deprecated
    public void showFacetNavigation(boolean z) {
        a("showFacetNavigation()");
        a(this.s, Boolean.valueOf(z));
    }

    @Deprecated
    public void showFacetNavigation(boolean z, int i2, int i3) {
        a("showFacetNavigation()");
        a(this.t, Boolean.valueOf(z), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public void suppressHomeButtonExit(boolean z) {
        a("suppressHomeButtonExit: " + z);
        a(this.v, Boolean.valueOf(z));
    }
}
