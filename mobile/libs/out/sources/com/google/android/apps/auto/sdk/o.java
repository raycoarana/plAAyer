package com.google.android.apps.auto.sdk;

import android.os.IInterface;
import android.os.RemoteException;

public interface o extends IInterface {
    void a() throws RemoteException;

    void a(SearchItem searchItem) throws RemoteException;

    void a(String str) throws RemoteException;

    void b() throws RemoteException;

    boolean b(String str) throws RemoteException;
}
