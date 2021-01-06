package com.google.android.apps.auto.sdk;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.agizmo;
import com.google.android.a.cgizmo;

public final class f extends agizmo implements e {
    f(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.auto.sdk.IDrawerController");
    }

    public final void a(int i) throws RemoteException {
        Parcel a_ = a_();
        a_.writeInt(i);
        b(6, a_);
    }

    public final void a(c cVar) throws RemoteException {
        Parcel a_ = a_();
        cgizmo.a(a_, (IInterface) cVar);
        b(5, a_);
    }

    public final boolean a() throws RemoteException {
        Parcel a = a(1, a_());
        boolean a2 = cgizmo.a(a);
        a.recycle();
        return a2;
    }

    public final boolean b() throws RemoteException {
        Parcel a = a(2, a_());
        boolean a2 = cgizmo.a(a);
        a.recycle();
        return a2;
    }

    public final void c() throws RemoteException {
        b(3, a_());
    }

    public final void d() throws RemoteException {
        b(4, a_());
    }
}
