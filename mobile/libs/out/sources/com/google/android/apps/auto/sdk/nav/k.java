package com.google.android.apps.auto.sdk.nav;

import com.google.android.apps.auto.sdk.nav.suggestion.NavigationSuggestion;

final class k implements Runnable {
    private /* synthetic */ NavigationSuggestion a;
    private /* synthetic */ i b;

    k(i iVar, NavigationSuggestion navigationSuggestion) {
        this.b = iVar;
        this.a = navigationSuggestion;
    }

    public final void run() {
        this.b.a.onSuggestionShown(this.a);
    }
}
