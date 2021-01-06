package com.google.android.apps.auto.sdk.nav;

final class f implements Runnable {
    private /* synthetic */ ClientMode a;
    private /* synthetic */ d b;

    f(d dVar, ClientMode clientMode) {
        this.b = dVar;
        this.a = clientMode;
    }

    public final void run() {
        this.b.a.onAndroidAutoStart(this.a);
    }
}
