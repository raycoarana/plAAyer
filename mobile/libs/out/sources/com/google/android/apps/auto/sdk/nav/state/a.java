package com.google.android.apps.auto.sdk.nav.state;

import android.os.IInterface;
import android.os.RemoteException;

public interface a extends IInterface {
    CarInstrumentClusterConfig a() throws RemoteException;

    void a(NavigationSummary navigationSummary) throws RemoteException;

    void a(TurnEvent turnEvent) throws RemoteException;
}
