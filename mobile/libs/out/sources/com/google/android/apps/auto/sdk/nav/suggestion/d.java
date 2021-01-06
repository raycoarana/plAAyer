package com.google.android.apps.auto.sdk.nav.suggestion;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.bgizmo;
import com.google.android.a.cgizmo;

public abstract class d extends bgizmo implements c {
    public d() {
        attachInterface(this, "com.google.android.apps.auto.sdk.nav.suggestion.INavigationSuggestionManager");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        a bVar;
        if (a(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    bVar = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.apps.auto.sdk.nav.suggestion.INavigationSuggestionCallback");
                    bVar = queryLocalInterface instanceof a ? (a) queryLocalInterface : new b(readStrongBinder);
                }
                a(bVar);
                break;
            case 2:
                a((NavigationSuggestion) cgizmo.a(parcel, NavigationSuggestion.CREATOR));
                break;
            case 3:
                a();
                break;
            case 4:
                b();
                break;
            case 5:
                b((NavigationSuggestion) cgizmo.a(parcel, NavigationSuggestion.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
