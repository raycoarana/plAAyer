package com.google.android.apps.auto.sdk.service.a;

import android.content.Intent;
import android.support.car.CarNotConnectedException;
import com.google.android.apps.auto.sdk.service.CarFirstPartyManager;
import com.google.android.apps.auto.sdk.service.CarFirstPartyManager.ScreenshotResultCallback;

public final class c implements CarFirstPartyManager {
    private final com.google.android.gms.car.CarFirstPartyManager a;

    public c(com.google.android.gms.car.CarFirstPartyManager carFirstPartyManager) {
        this.a = carFirstPartyManager;
    }

    public final void captureScreenshot(ScreenshotResultCallback screenshotResultCallback) throws CarNotConnectedException {
        if (screenshotResultCallback != null) {
            try {
                this.a.captureScreenshot(new d(screenshotResultCallback));
            } catch (com.google.android.gms.car.CarNotConnectedException e) {
                throw new CarNotConnectedException((Exception) e);
            }
        }
    }

    public final void startCarActivity(Intent intent) throws CarNotConnectedException {
        try {
            this.a.startCarActivity(intent);
        } catch (com.google.android.gms.car.CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }
}
