package com.google.a.a.a.a.a;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class d extends WeakReference<Throwable> {
    private final int a;

    public d(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        boolean z = true;
        if (obj == null || obj.getClass() != getClass()) {
            z = false;
        } else if (this != obj) {
            d dVar = (d) obj;
            if (!(this.a == dVar.a && get() == dVar.get())) {
                return false;
            }
        }
        return z;
    }

    public final int hashCode() {
        return this.a;
    }
}
