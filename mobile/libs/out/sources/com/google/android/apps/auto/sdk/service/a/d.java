package com.google.android.apps.auto.sdk.service.a;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import com.google.android.apps.auto.sdk.service.CarFirstPartyManager;
import com.google.android.gms.car.CarFirstPartyManager.ScreenshotResultCallback;

final class d implements ScreenshotResultCallback {
    private /* synthetic */ CarFirstPartyManager.ScreenshotResultCallback a;

    d(CarFirstPartyManager.ScreenshotResultCallback screenshotResultCallback) {
        this.a = screenshotResultCallback;
    }

    public final void onScreeshotResult(@Nullable Bitmap bitmap) {
        this.a.onScreenshotResult(bitmap);
    }
}
