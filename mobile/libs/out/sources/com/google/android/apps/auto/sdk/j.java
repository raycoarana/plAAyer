package com.google.android.apps.auto.sdk;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.agizmo;
import com.google.android.a.cgizmo;

public final class j extends agizmo implements i {
    j(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.auto.sdk.IImeController");
    }

    public final void a(g gVar) throws RemoteException {
        Parcel a_ = a_();
        cgizmo.a(a_, (IInterface) gVar);
        b(1, a_);
    }
}
