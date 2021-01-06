package com.google.android.apps.auto.sdk;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.agizmo;
import com.google.android.a.cgizmo;

public final class u extends agizmo implements t {
    u(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.auto.sdk.IStatusBarController");
    }

    public final void a(float f) throws RemoteException {
        Parcel a_ = a_();
        a_.writeFloat(f);
        b(17, a_);
    }

    public final void a(int i) throws RemoteException {
        Parcel a_ = a_();
        a_.writeInt(i);
        b(13, a_);
    }

    public final void a(CharSequence charSequence) throws RemoteException {
        Parcel a_ = a_();
        cgizmo.a(a_, charSequence);
        b(1, a_);
    }

    public final boolean a() throws RemoteException {
        Parcel a = a(2, a_());
        boolean a2 = cgizmo.a(a);
        a.recycle();
        return a2;
    }

    public final void b() throws RemoteException {
        b(3, a_());
    }

    public final void b(int i) throws RemoteException {
        Parcel a_ = a_();
        a_.writeInt(i);
        b(16, a_);
    }

    public final void c() throws RemoteException {
        b(4, a_());
    }

    public final void d() throws RemoteException {
        b(5, a_());
    }

    public final void e() throws RemoteException {
        b(6, a_());
    }

    public final void f() throws RemoteException {
        b(7, a_());
    }

    public final void g() throws RemoteException {
        b(8, a_());
    }

    public final void h() throws RemoteException {
        b(9, a_());
    }

    public final void i() throws RemoteException {
        b(10, a_());
    }

    public final void j() throws RemoteException {
        b(11, a_());
    }

    public final void k() throws RemoteException {
        b(12, a_());
    }
}
