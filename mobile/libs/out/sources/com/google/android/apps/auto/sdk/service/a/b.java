package com.google.android.apps.auto.sdk.service.a;

import android.support.annotation.VisibleForTesting;
import android.support.car.CarAppFocusManager.OnAppFocusChangedListener;
import android.support.car.CarAppFocusManager.OnAppFocusOwnershipCallback;
import android.util.ArrayMap;
import android.util.SparseArray;
import com.google.android.gms.car.CarMessageManager.CarMessageListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
public final class b implements CarMessageListener {
    private SparseArray<List<OnAppFocusChangedListener>> a = new SparseArray<>();
    private Map<Integer, OnAppFocusOwnershipCallback> b = new ArrayMap();
    private a c;

    b(a aVar) {
        this.c = aVar;
    }

    public final OnAppFocusOwnershipCallback a(int i) {
        OnAppFocusOwnershipCallback onAppFocusOwnershipCallback;
        synchronized (this) {
            onAppFocusOwnershipCallback = (OnAppFocusOwnershipCallback) this.b.get(Integer.valueOf(i));
        }
        return onAppFocusOwnershipCallback;
    }

    public final Set<Integer> a(OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) {
        HashSet hashSet;
        synchronized (this) {
            hashSet = new HashSet();
            for (Integer num : new HashSet(this.b.keySet())) {
                if (b(num.intValue(), onAppFocusOwnershipCallback)) {
                    hashSet.add(num);
                }
            }
        }
        return hashSet;
    }

    public final void a(int i, OnAppFocusChangedListener onAppFocusChangedListener) {
        synchronized (this) {
            List list = (List) this.a.get(i);
            if (list != null) {
                list.remove(onAppFocusChangedListener);
            }
        }
    }

    public final void a(int i, OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) {
        synchronized (this) {
            this.b.put(Integer.valueOf(i), onAppFocusOwnershipCallback);
        }
    }

    public final void a(OnAppFocusChangedListener onAppFocusChangedListener) {
        synchronized (this) {
            a(1, onAppFocusChangedListener);
            a(0, onAppFocusChangedListener);
        }
    }

    public final boolean a(OnAppFocusOwnershipCallback onAppFocusOwnershipCallback, int i) {
        boolean z;
        synchronized (this) {
            z = onAppFocusOwnershipCallback == a(i);
        }
        return z;
    }

    public final void b(int i, OnAppFocusChangedListener onAppFocusChangedListener) {
        synchronized (this) {
            List list = (List) this.a.get(i);
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(onAppFocusChangedListener);
                this.a.put(i, arrayList);
            } else if (!list.contains(onAppFocusChangedListener)) {
                list.add(onAppFocusChangedListener);
            }
        }
    }

    public final boolean b(int i, OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) {
        boolean z;
        synchronized (this) {
            if (((OnAppFocusOwnershipCallback) this.b.get(Integer.valueOf(i))) == onAppFocusOwnershipCallback) {
                this.b.remove(Integer.valueOf(i));
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (r8 == 1) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        if (r8 != 1) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onIntegerMessage(int r6, int r7, int r8) {
        /*
            r5 = this;
            r2 = 1
            r1 = 0
            monitor-enter(r5)
            android.util.SparseArray<java.util.List<android.support.car.CarAppFocusManager$OnAppFocusChangedListener>> r0 = r5.a     // Catch:{ all -> 0x0030 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0030 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x003a
            boolean r3 = r0.isEmpty()     // Catch:{ all -> 0x0030 }
            if (r3 != 0) goto L_0x003a
            switch(r6) {
                case 0: goto L_0x0037;
                case 1: goto L_0x0033;
                default: goto L_0x0016;
            }     // Catch:{ all -> 0x0030 }
        L_0x0016:
            int r2 = com.google.android.apps.auto.sdk.service.a.a.a(r6)     // Catch:{ all -> 0x0030 }
            java.util.Iterator r3 = r0.iterator()     // Catch:{ all -> 0x0030 }
        L_0x001e:
            boolean r0 = r3.hasNext()     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x003a
            java.lang.Object r0 = r3.next()     // Catch:{ all -> 0x0030 }
            android.support.car.CarAppFocusManager$OnAppFocusChangedListener r0 = (android.support.car.CarAppFocusManager.OnAppFocusChangedListener) r0     // Catch:{ all -> 0x0030 }
            com.google.android.apps.auto.sdk.service.a.a r4 = r5.c     // Catch:{ all -> 0x0030 }
            r0.onAppFocusChanged(r4, r2, r1)     // Catch:{ all -> 0x0030 }
            goto L_0x001e
        L_0x0030:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        L_0x0033:
            if (r8 != r2) goto L_0x0016
        L_0x0035:
            r1 = r2
            goto L_0x0016
        L_0x0037:
            if (r8 == r2) goto L_0x0035
            goto L_0x0016
        L_0x003a:
            monitor-exit(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.auto.sdk.service.a.b.onIntegerMessage(int, int, int):void");
    }

    public final void onOwnershipLost(int i) {
        synchronized (this) {
            OnAppFocusOwnershipCallback a2 = a(i);
            if (a2 != null) {
                a2.onAppFocusOwnershipLost(this.c, a.a(i));
                this.b.remove(Integer.valueOf(i));
            }
        }
    }
}
