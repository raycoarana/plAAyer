package com.google.android.apps.auto.sdk.vanagon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class c extends BroadcastReceiver {
    private /* synthetic */ PhoneSysUiClient a;

    c(PhoneSysUiClient phoneSysUiClient) {
        this.a = phoneSysUiClient;
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.a.x == null) {
            return;
        }
        if (PhoneSysUiClient.isAndroidAutoRunning(context)) {
            this.a.x.onEnterPhoneMode();
        } else {
            this.a.x.onExitPhoneMode();
        }
    }
}
