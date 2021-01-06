package com.google.android.apps.auto.sdk;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.bgizmo;
import com.google.android.a.cgizmo;

public abstract class l extends bgizmo implements k {
    public l() {
        attachInterface(this, "com.google.android.apps.auto.sdk.IMenuAdapter");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (a(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                int a = a();
                parcel2.writeNoException();
                parcel2.writeInt(a);
                break;
            case 2:
                MenuItem a2 = a(parcel.readInt());
                parcel2.writeNoException();
                cgizmo.b(parcel2, a2);
                break;
            case 3:
                a((MenuItem) cgizmo.a(parcel, MenuItem.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                boolean b = b();
                parcel2.writeNoException();
                cgizmo.a(parcel2, b);
                break;
            case 5:
                c();
                parcel2.writeNoException();
                break;
            case 6:
                d();
                parcel2.writeNoException();
                break;
            case 7:
                e();
                parcel2.writeNoException();
                break;
            case 8:
                String f = f();
                parcel2.writeNoException();
                parcel2.writeString(f);
                break;
            case 9:
                a((Bundle) cgizmo.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
