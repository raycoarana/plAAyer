package com.google.android.apps.auto.sdk.nav;

import android.os.IInterface;
import android.os.RemoteException;

public interface c extends IInterface {
    NavigationProviderConfig a() throws RemoteException;

    void a(ClientMode clientMode) throws RemoteException;

    void a(NavigationClientConfig navigationClientConfig) throws RemoteException;

    void b() throws RemoteException;

    com.google.android.apps.auto.sdk.nav.state.c c() throws RemoteException;

    com.google.android.apps.auto.sdk.nav.suggestion.c d() throws RemoteException;
}
