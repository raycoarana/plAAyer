package com.google.android.gms.car;

import android.os.Handler;
import android.os.Message;

final class h extends Handler {
    private /* synthetic */ e a;

    h(e eVar) {
        this.a = eVar;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (this.a.c) {
                    this.a.a(false);
                    return;
                }
                return;
            case 2:
                this.a.b.dispatchResume();
                this.a.b.execPendingActions();
                return;
            default:
                super.handleMessage(message);
                return;
        }
    }
}
