package com.google.android.apps.auto.sdk.service.a.c;

import android.support.annotation.VisibleForTesting;
import com.google.android.apps.auto.sdk.service.a.c.a;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class b<L, P extends a<L>> {
    @VisibleForTesting
    public final Map<L, P> a = new HashMap();
    @VisibleForTesting
    public final Map<Object, c<L, P>> b = Collections.synchronizedMap(new HashMap());
    private final d<L, P> c;

    public b(d<L, P> dVar) {
        this.c = dVar;
    }

    public final P a(L l) {
        P p;
        synchronized (this.b) {
            p = null;
            Iterator it = this.b.values().iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                P a2 = cVar.a(l);
                if (cVar.a()) {
                    it.remove();
                }
                p = a2;
            }
            this.a.remove(l);
        }
        return p;
    }

    public final P a(Object obj, L l) {
        c cVar;
        P p;
        synchronized (this.b) {
            c cVar2 = (c) this.b.get(obj);
            if (cVar2 == null) {
                c cVar3 = new c(this.c);
                this.b.put(obj, cVar3);
                cVar = cVar3;
            } else {
                cVar = cVar2;
            }
            p = (a) this.a.get(l);
            if (p == null) {
                p = this.c.a(l);
                this.a.put(l, p);
            }
            cVar.a.add(p);
        }
        return p;
    }

    public final P b(Object obj, L l) {
        P p;
        a aVar;
        synchronized (this.b) {
            c cVar = (c) this.b.get(obj);
            if (cVar != null) {
                p = cVar.a(l);
                if (cVar.a()) {
                    this.b.remove(obj);
                }
            } else {
                p = null;
            }
            synchronized (this.b) {
                Iterator it = this.b.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        aVar = null;
                        break;
                    }
                    aVar = ((c) it.next()).b(l);
                    if (aVar != null) {
                        break;
                    }
                }
                if (aVar == null) {
                    this.a.remove(l);
                }
            }
        }
        return p;
    }
}
