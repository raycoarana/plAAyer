package com.google.android.apps.auto.sdk;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.bgizmo;
import com.google.android.a.cgizmo;

public abstract class p extends bgizmo implements o {
    public p() {
        attachInterface(this, "com.google.android.apps.auto.sdk.ISearchCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (a(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                a();
                parcel2.writeNoException();
                break;
            case 2:
                b();
                parcel2.writeNoException();
                break;
            case 3:
                a(parcel.readString());
                parcel2.writeNoException();
                break;
            case 4:
                boolean b = b(parcel.readString());
                parcel2.writeNoException();
                cgizmo.a(parcel2, b);
                break;
            case 5:
                a((SearchItem) cgizmo.a(parcel, SearchItem.CREATOR));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
