package com.google.android.apps.auto.sdk;

import android.os.RemoteException;
import android.support.annotation.UiThread;
import android.util.Log;
import com.google.android.gms.car.input.InputManager;

final class v {
    private final InputManager a;
    private final CarUiController b;
    private final i c;

    v(i iVar, InputManager inputManager, CarUiController carUiController) {
        this.c = iVar;
        this.a = inputManager;
        this.b = carUiController;
        try {
            this.c.a(new w(this));
        } catch (RemoteException e) {
            Log.e("CSL.ImeController", "Error setting ImeCallbacks", e);
        }
    }

    public final boolean a() {
        return this.a.isInputActive();
    }

    @UiThread
    public final void b() {
        this.a.startInput(new x(this.b));
    }

    @UiThread
    public final void c() {
        this.a.stopInput();
    }
}
