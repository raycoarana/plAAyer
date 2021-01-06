package com.google.android.apps.auto.sdk.nav;

import android.os.Handler;
import android.support.annotation.MainThread;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

final class a {
    @GuardedBy("this")
    private final Queue<Runnable> a = new ArrayDeque();
    @GuardedBy("this")
    private final Handler b;

    a(Handler handler) {
        this.b = handler;
    }

    /* access modifiers changed from: 0000 */
    @MainThread
    public final void a() {
        synchronized (this) {
            while (this.a.size() > 0) {
                Runnable runnable = (Runnable) this.a.remove();
                runnable.run();
                this.b.removeCallbacks(runnable);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(Runnable runnable) {
        synchronized (this) {
            this.a.add(runnable);
            this.b.post(new b(this));
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b() {
        synchronized (this) {
            Runnable runnable = (Runnable) this.a.poll();
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}
