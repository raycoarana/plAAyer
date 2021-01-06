package com.google.android.apps.auto.sdk;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.bgizmo;

public abstract class d extends bgizmo implements c {
    public d() {
        attachInterface(this, "com.google.android.apps.auto.sdk.IDrawerCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (a(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                a();
                break;
            case 2:
                b();
                break;
            case 3:
                c();
                break;
            case 4:
                d();
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
