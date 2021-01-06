package com.google.android.apps.auto.sdk.nav;

final class g implements Runnable {
    private /* synthetic */ d a;

    g(d dVar) {
        this.a = dVar;
    }

    public final void run() {
        this.a.a.onAndroidAutoStop();
    }
}
