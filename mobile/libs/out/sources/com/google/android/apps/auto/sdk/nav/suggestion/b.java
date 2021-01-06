package com.google.android.apps.auto.sdk.nav.suggestion;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.agizmo;

public final class b extends agizmo implements a {
    b(IBinder iBinder) {
        super(iBinder, "com.google.android.apps.auto.sdk.nav.suggestion.INavigationSuggestionCallback");
    }

    public final void a(NavigationSuggestion[] navigationSuggestionArr) throws RemoteException {
        Parcel a_ = a_();
        a_.writeTypedArray(navigationSuggestionArr, 0);
        c(1, a_);
    }
}
