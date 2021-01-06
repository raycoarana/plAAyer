package com.google.android.apps.auto.sdk.service.a.c;

import android.support.annotation.VisibleForTesting;
import com.google.android.apps.auto.sdk.service.a.c.a;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class c<L, P extends a<L>> {
    @VisibleForTesting
    final Set<P> a = Collections.synchronizedSet(new HashSet());

    public c(d<L, P> dVar) {
    }

    public final P a(L l) {
        P b;
        synchronized (this.a) {
            b = b(l);
            if (b != null) {
                this.a.remove(b);
            }
        }
        return b;
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return this.a.isEmpty();
    }

    /* access modifiers changed from: protected */
    public final P b(L l) {
        for (P p : this.a) {
            if (p.a() == l) {
                return p;
            }
        }
        return null;
    }
}
