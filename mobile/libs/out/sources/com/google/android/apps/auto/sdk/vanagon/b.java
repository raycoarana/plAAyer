package com.google.android.apps.auto.sdk.vanagon;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.apps.auto.sdk.vanagon.PhoneSysUiClient.ScreenshotProvider.OnCompleteListener;
import java.lang.reflect.InvocationTargetException;

final class b implements OnCompleteListener {
    private /* synthetic */ Object[] a;
    private /* synthetic */ a b;

    b(a aVar, Object[] objArr) {
        this.b = aVar;
        this.a = objArr;
    }

    public final void onScreenshotComplete(@Nullable Bitmap bitmap) {
        try {
            this.a[0].getClass().getMethod("onScreenshotComplete", new Class[]{Bitmap.class}).invoke(this.a[0], new Object[]{bitmap});
        } catch (NoSuchMethodException e) {
            Log.d("GH.PhoneSysUiClient", "Method is not loaded.");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            PhoneSysUiClient.a(e2);
        }
    }
}
