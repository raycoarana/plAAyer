package com.google.android.apps.auto.sdk;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.bgizmo;
import com.google.android.a.cgizmo;

public abstract class h extends bgizmo implements g {
    public h() {
        attachInterface(this, "com.google.android.apps.auto.sdk.IImeCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (a(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                boolean a = a();
                parcel2.writeNoException();
                cgizmo.a(parcel2, a);
                return true;
            case 2:
                b();
                parcel2.writeNoException();
                return true;
            case 3:
                c();
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
