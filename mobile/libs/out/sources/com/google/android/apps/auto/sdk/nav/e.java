package com.google.android.apps.auto.sdk.nav;

import android.support.annotation.Nullable;
import android.util.Log;

abstract class e<T> {
    @Nullable
    private NavigationClientConfig a;

    e() {
    }

    /* access modifiers changed from: 0000 */
    public abstract T a();

    /* access modifiers changed from: 0000 */
    public final void a(NavigationClientConfig navigationClientConfig) {
        this.a = navigationClientConfig;
    }

    /* access modifiers changed from: protected */
    public final boolean b() {
        if (this.a != null) {
            return true;
        }
        if (Log.isLoggable("GH.NavManager", 5)) {
            Log.w("GH.NavManager", "Navigation client is not yet registered. Call registerClient() first");
        }
        return false;
    }
}
