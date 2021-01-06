package com.google.android.apps.auto.sdk.nav.state;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.bgizmo;

public abstract class d extends bgizmo implements c {
    public d() {
        attachInterface(this, "com.google.android.apps.auto.sdk.nav.state.INavigationStateManager");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        a bVar;
        if (a(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            bVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.apps.auto.sdk.nav.state.INavigationStateCallback");
            bVar = queryLocalInterface instanceof a ? (a) queryLocalInterface : new b(readStrongBinder);
        }
        a(bVar);
        return true;
    }
}
