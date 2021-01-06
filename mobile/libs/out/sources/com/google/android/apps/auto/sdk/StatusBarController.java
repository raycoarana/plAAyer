package com.google.android.apps.auto.sdk;

import android.os.RemoteException;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.google.android.gms.car.CarInfoManager.CarInfo;

public class StatusBarController {
    private t a;
    private final CarInfo b;

    StatusBarController(t tVar, @Nullable CarInfo carInfo) {
        this.a = tVar;
        this.b = carInfo;
        if (this.b != null) {
            if (this.b.isHideProjectedClock()) {
                hideClock();
            }
            if (this.b.isHideBatteryLevel()) {
                hideBatteryLevel();
            }
            if (this.b.isHidePhoneSignal()) {
                hideConnectivityLevel();
            }
        }
    }

    @UiThread
    public void hideAppHeader() {
        Log.d("CSL.StatusBarController", "hideAppHeader");
        hideTitle();
        hideConnectivityLevel();
        hideBatteryLevel();
        hideClock();
        hideMicButton();
    }

    @UiThread
    public void hideBatteryLevel() {
        Log.d("CSL.StatusBarController", "hideBatteryLevel");
        try {
            this.a.g();
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error hiding battery level", e);
        }
    }

    @UiThread
    public void hideClock() {
        Log.d("CSL.StatusBarController", "hideClock");
        try {
            this.a.i();
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error hiding clock", e);
        }
    }

    @UiThread
    public void hideConnectivityLevel() {
        Log.d("CSL.StatusBarController", "hideConnectivityLevel");
        try {
            this.a.e();
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error hiding connectivity level", e);
        }
    }

    @UiThread
    public void hideMicButton() {
        Log.d("CSL.StatusBarController", "hideMicButton");
        try {
            this.a.k();
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error hiding mic button", e);
        }
    }

    @UiThread
    public void hideTitle() {
        Log.d("CSL.StatusBarController", "hideTitle");
        try {
            this.a.c();
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error hiding title", e);
        }
    }

    @UiThread
    public boolean isTitleVisible() {
        Log.d("CSL.StatusBarController", "isTitleVisible");
        try {
            return this.a.a();
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error querying title visibility", e);
            return false;
        }
    }

    @UiThread
    public void setAppBarAlpha(float f) {
        Log.d("CSL.StatusBarController", "setAppBarAlpha");
        try {
            this.a.a(f);
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error setting app bar alpha", e);
        }
    }

    @UiThread
    public void setAppBarBackgroundColor(@ColorInt int i) {
        Log.d("CSL.StatusBarController", "setAppBarBackgroundColor");
        try {
            this.a.b(i);
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error setting app bar background color", e);
        }
    }

    public void setDayNightStyle(@DayNightStyle int i) {
        Log.d("CSL.StatusBarController", "setDayNightStyle " + i);
        try {
            this.a.a(i);
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error setting day style", e);
        }
    }

    @UiThread
    public void setTitle(CharSequence charSequence) {
        String valueOf = String.valueOf(charSequence);
        Log.d("CSL.StatusBarController", new StringBuilder(String.valueOf(valueOf).length() + 9).append("setTitle ").append(valueOf).toString());
        try {
            this.a.a(charSequence);
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error setting title", e);
        }
    }

    @UiThread
    public void showAppHeader() {
        Log.d("CSL.StatusBarController", "showAppHeader");
        showTitle();
        showConnectivityLevel();
        showBatteryLevel();
        showClock();
        showMicButton();
    }

    @UiThread
    public void showBatteryLevel() {
        if (this.b == null || !this.b.isHideBatteryLevel()) {
            Log.d("CSL.StatusBarController", "showBatteryLevel");
            try {
                this.a.f();
            } catch (RemoteException e) {
                Log.e("CSL.StatusBarController", "Error showing battery level", e);
            }
        } else {
            Log.d("CSL.StatusBarController", "Battery level is forced to be hidden.");
        }
    }

    @UiThread
    public void showClock() {
        if (this.b == null || !this.b.isHideProjectedClock()) {
            Log.d("CSL.StatusBarController", "showClock");
            try {
                this.a.h();
            } catch (RemoteException e) {
                Log.e("CSL.StatusBarController", "Error showing clock", e);
            }
        } else {
            Log.d("CSL.StatusBarController", "Projected clock is forced to be hidden.");
        }
    }

    @UiThread
    public void showConnectivityLevel() {
        if (this.b == null || !this.b.isHidePhoneSignal()) {
            Log.d("CSL.StatusBarController", "showConnectivityLevel");
            try {
                this.a.d();
            } catch (RemoteException e) {
                Log.e("CSL.StatusBarController", "Error showing connectivity level", e);
            }
        } else {
            Log.d("CSL.StatusBarController", "Phone signal is forced to be hidden.");
        }
    }

    @UiThread
    public void showMicButton() {
        Log.d("CSL.StatusBarController", "showMicButton");
        try {
            this.a.j();
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error showing mic button", e);
        }
    }

    @UiThread
    public void showTitle() {
        Log.d("CSL.StatusBarController", "showTitle");
        try {
            this.a.b();
        } catch (RemoteException e) {
            Log.e("CSL.StatusBarController", "Error showing title", e);
        }
    }
}
