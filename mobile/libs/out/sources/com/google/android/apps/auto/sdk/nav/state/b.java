package com.google.android.apps.auto.sdk.nav.state;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.a.agizmo;
import com.google.android.a.cgizmo;

public final class b extends agizmo implements a {
    b(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.auto.sdk.nav.state.INavigationStateCallback");
    }

    public final CarInstrumentClusterConfig a() throws RemoteException {
        Parcel a = a(3, a_());
        CarInstrumentClusterConfig carInstrumentClusterConfig = (CarInstrumentClusterConfig) cgizmo.a(a, CarInstrumentClusterConfig.CREATOR);
        a.recycle();
        return carInstrumentClusterConfig;
    }

    public final void a(NavigationSummary navigationSummary) throws RemoteException {
        Parcel a_ = a_();
        cgizmo.a(a_, (Parcelable) navigationSummary);
        c(1, a_);
    }

    public final void a(TurnEvent turnEvent) throws RemoteException {
        Parcel a_ = a_();
        cgizmo.a(a_, (Parcelable) turnEvent);
        c(2, a_);
    }
}
