package com.google.android.apps.auto.sdk;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.agizmo;
import com.google.android.a.cgizmo;
import java.util.List;

public final class s extends agizmo implements q {
    s(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.auto.sdk.ISearchController");
    }

    public final void a() throws RemoteException {
        b(1, a_());
    }

    public final void a(o oVar) throws RemoteException {
        Parcel a_ = a_();
        cgizmo.a(a_, (IInterface) oVar);
        b(5, a_);
    }

    public final void a(CharSequence charSequence) throws RemoteException {
        Parcel a_ = a_();
        cgizmo.a(a_, charSequence);
        b(4, a_);
    }

    public final void a(String str) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        b(7, a_);
    }

    public final void a(List<SearchItem> list) throws RemoteException {
        Parcel a_ = a_();
        a_.writeTypedList(list);
        b(3, a_);
    }

    public final void b() throws RemoteException {
        b(2, a_());
    }

    public final void c() throws RemoteException {
        b(6, a_());
    }
}
