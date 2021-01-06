package com.google.android.apps.auto.sdk.nav;

import android.os.RemoteException;
import com.google.android.apps.auto.sdk.nav.suggestion.NavigationSuggestion;
import com.google.android.apps.auto.sdk.nav.suggestion.a;
import com.google.android.apps.auto.sdk.nav.suggestion.d;

final class i extends d {
    final /* synthetic */ NavigationSuggestionManager a;

    i(NavigationSuggestionManager navigationSuggestionManager) {
        this.a = navigationSuggestionManager;
    }

    public final void a() {
        this.a.a.a(new l(this));
    }

    public final void a(NavigationSuggestion navigationSuggestion) throws RemoteException {
        this.a.a.a(new j(this, navigationSuggestion));
    }

    public final void a(a aVar) throws RemoteException {
        synchronized (this.a) {
            this.a.b = aVar;
        }
    }

    public final void b() {
        this.a.a.a(new m(this));
    }

    public final void b(NavigationSuggestion navigationSuggestion) throws RemoteException {
        this.a.a.a(new k(this, navigationSuggestion));
    }
}
