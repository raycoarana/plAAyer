package com.google.android.apps.auto.sdk.nav;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.apps.auto.sdk.nav.state.CarInstrumentClusterConfig;
import com.google.android.apps.auto.sdk.nav.state.a;
import com.google.android.apps.auto.sdk.nav.state.d;

final class h extends d {
    private /* synthetic */ NavigationStateManager a;

    h(NavigationStateManager navigationStateManager) {
        this.a = navigationStateManager;
    }

    public final void a(a aVar) throws RemoteException {
        synchronized (this.a) {
            this.a.a = aVar;
            if (aVar != null) {
                CarInstrumentClusterConfig a2 = aVar.a();
                if (a2 != null) {
                    this.a.b = a2;
                } else {
                    Log.w("NavigationStateManager", "Received null instrument cluster config");
                }
            } else {
                Log.e("NavigationStateManager", "Received null INavigationStateCallback");
            }
        }
    }
}
