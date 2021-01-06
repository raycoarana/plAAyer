package com.google.android.apps.auto.sdk;

final /* synthetic */ class y implements Runnable {
    private final c a;
    private final MenuAdapter b;

    y(c cVar, MenuAdapter menuAdapter) {
        this.a = cVar;
        this.b = menuAdapter;
    }

    public final void run() {
        this.a.a(this.b);
    }
}
