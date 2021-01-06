package com.google.android.apps.auto.sdk.nav;

final class m implements Runnable {
    private /* synthetic */ i a;

    m(i iVar) {
        this.a = iVar;
    }

    public final void run() {
        this.a.a.onStopSuggestionUpdates();
    }
}
