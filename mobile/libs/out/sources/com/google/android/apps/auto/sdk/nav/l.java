package com.google.android.apps.auto.sdk.nav;

final class l implements Runnable {
    private /* synthetic */ i a;

    l(i iVar) {
        this.a = iVar;
    }

    public final void run() {
        this.a.a.onRequestSuggestionUpdates();
    }
}
