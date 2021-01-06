package com.google.android.apps.auto.sdk;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.agizmo;
import com.google.android.a.cgizmo;
import java.util.List;

public final class n extends agizmo implements m {
    n(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.auto.sdk.IMenuController");
    }

    public final void a() throws RemoteException {
        b(2, a_());
    }

    public final void a(int i) throws RemoteException {
        Parcel a_ = a_();
        a_.writeInt(i);
        b(3, a_);
    }

    public final void a(k kVar) throws RemoteException {
        Parcel a_ = a_();
        cgizmo.a(a_, (IInterface) kVar);
        b(1, a_);
    }

    public final void a(List<AlphaJumpKeyItem> list) throws RemoteException {
        Parcel a_ = a_();
        a_.writeTypedList(list);
        b(10, a_);
    }

    public final void b() throws RemoteException {
        b(4, a_());
    }

    public final void c() throws RemoteException {
        b(5, a_());
    }

    public final void d() throws RemoteException {
        b(6, a_());
    }

    public final void e() throws RemoteException {
        b(7, a_());
    }

    public final void f() throws RemoteException {
        b(8, a_());
    }

    public final void g() throws RemoteException {
        b(9, a_());
    }
}
