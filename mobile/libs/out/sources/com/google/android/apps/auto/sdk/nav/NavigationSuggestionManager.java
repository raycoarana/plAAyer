package com.google.android.apps.auto.sdk.nav;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import com.google.android.apps.auto.sdk.nav.suggestion.NavigationSuggestion;
import com.google.android.apps.auto.sdk.nav.suggestion.a;
import com.google.android.apps.auto.sdk.nav.suggestion.c;
import javax.annotation.concurrent.GuardedBy;

public class NavigationSuggestionManager extends e<c> {
    /* access modifiers changed from: private */
    public final a a = new a(new Handler(Looper.getMainLooper()));
    /* access modifiers changed from: private */
    @Nullable
    @GuardedBy("this")
    public a b;
    private final c c = new i(this);

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object a() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    @MainThread
    public final void c() {
        this.a.a();
    }

    @MainThread
    public void onRequestSuggestionUpdates() {
    }

    @MainThread
    public void onStopSuggestionUpdates() {
    }

    @MainThread
    public void onSuggestionDismissed(NavigationSuggestion navigationSuggestion) {
    }

    @MainThread
    public void onSuggestionShown(NavigationSuggestion navigationSuggestion) {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    @android.support.annotation.CallSuper
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendNavigationSuggestions(com.google.android.apps.auto.sdk.nav.suggestion.NavigationSuggestion[] r2) throws android.os.RemoteException {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.b()     // Catch:{ all -> 0x0014 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r1)     // Catch:{ all -> 0x0014 }
        L_0x0008:
            return
        L_0x0009:
            com.google.android.apps.auto.sdk.nav.suggestion.a r0 = r1.b     // Catch:{ all -> 0x0014 }
            if (r0 == 0) goto L_0x0012
            com.google.android.apps.auto.sdk.nav.suggestion.a r0 = r1.b     // Catch:{ all -> 0x0014 }
            r0.a(r2)     // Catch:{ all -> 0x0014 }
        L_0x0012:
            monitor-exit(r1)     // Catch:{ all -> 0x0014 }
            goto L_0x0008
        L_0x0014:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0014 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.auto.sdk.nav.NavigationSuggestionManager.sendNavigationSuggestions(com.google.android.apps.auto.sdk.nav.suggestion.NavigationSuggestion[]):void");
    }
}
