package com.google.android.apps.auto.sdk.vanagon;

import android.util.Log;
import com.google.android.apps.auto.sdk.vanagon.PhoneSysUiClient.ScreenshotProvider;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

final class a implements InvocationHandler {
    final /* synthetic */ PhoneSysUiClient a;
    private /* synthetic */ ScreenshotProvider b;

    a(PhoneSysUiClient phoneSysUiClient, ScreenshotProvider screenshotProvider) {
        this.a = phoneSysUiClient;
        this.b = screenshotProvider;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Class[] parameterTypes = method.getParameterTypes();
        if (method.getName().equals("getScreenshot") && parameterTypes.length == 1 && parameterTypes[0] == this.a.d) {
            this.b.getScreenshot(new b(this, objArr));
        } else {
            String valueOf = String.valueOf(method.getName());
            Log.e("GH.PhoneSysUiClient", valueOf.length() != 0 ? "Could not invoke this method in screenshotProvider: ".concat(valueOf) : new String("Could not invoke this method in screenshotProvider: "));
        }
        return null;
    }
}
