package com.google.android.apps.auto.sdk;

import android.os.RemoteException;
import android.util.Log;

final /* synthetic */ class z implements Runnable {
    private final c a;
    private final MenuAdapter b;
    private final int c;

    z(c cVar, MenuAdapter menuAdapter, int i) {
        this.a = cVar;
        this.b = menuAdapter;
        this.c = i;
    }

    public final void run() {
        c cVar = this.a;
        MenuAdapter menuAdapter = this.b;
        int i = this.c;
        String valueOf = String.valueOf(menuAdapter);
        Log.d("CSL.MenuController", new StringBuilder(String.valueOf(valueOf).length() + 48).append("notifyItemChanged ").append(valueOf).append(", displayPosition: ").append(i).toString());
        if (menuAdapter == MenuController.this.a.a) {
            try {
                MenuController.this.f.a(i);
            } catch (RemoteException e) {
                Log.e("CSL.MenuController", "Error notifying item changed", e);
            }
        }
    }
}
