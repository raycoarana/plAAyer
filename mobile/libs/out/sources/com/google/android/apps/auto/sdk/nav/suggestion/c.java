package com.google.android.apps.auto.sdk.nav.suggestion;

import android.os.IInterface;
import android.os.RemoteException;

public interface c extends IInterface {
    void a() throws RemoteException;

    void a(NavigationSuggestion navigationSuggestion) throws RemoteException;

    void a(a aVar) throws RemoteException;

    void b() throws RemoteException;

    void b(NavigationSuggestion navigationSuggestion) throws RemoteException;
}
