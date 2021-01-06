package com.google.android.apps.auto.sdk.nav;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.bgizmo;
import com.google.android.a.cgizmo;
import com.google.android.apps.auto.sdk.nav.state.c;
import java.util.ArrayList;

public class d extends bgizmo implements c {
    final /* synthetic */ NavigationProviderService a;

    public d() {
        attachInterface(this, "com.google.android.apps.auto.sdk.nav.INavigationProvider");
    }

    d(NavigationProviderService navigationProviderService) {
        this.a = navigationProviderService;
        this();
    }

    private <T> T a(e<T> eVar) {
        if (eVar != null && e()) {
            return eVar.a();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean e() {
        /*
            r3 = this;
            com.google.android.apps.auto.sdk.nav.NavigationProviderService r1 = r3.a
            monitor-enter(r1)
            com.google.android.apps.auto.sdk.nav.NavigationProviderService r0 = r3.a     // Catch:{ all -> 0x0021 }
            com.google.android.apps.auto.sdk.nav.NavigationClientConfig r0 = r0.a     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x001e
            java.lang.String r0 = "GH.NavProviderService"
            r2 = 5
            boolean r0 = android.util.Log.isLoggable(r0, r2)     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x001b
            java.lang.String r0 = "GH.NavProviderService"
            java.lang.String r2 = "Navigation client is not yet registered. Call registerClient() first"
            android.util.Log.w(r0, r2)     // Catch:{ all -> 0x0021 }
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            r0 = 0
        L_0x001d:
            return r0
        L_0x001e:
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            r0 = 1
            goto L_0x001d
        L_0x0021:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.auto.sdk.nav.d.e():boolean");
    }

    public NavigationProviderConfig a() throws RemoteException {
        return new NavigationProviderConfig(this.a.getMinSupportedVersion(), this.a.getMaxSupportedVersion());
    }

    public void a(ClientMode clientMode) throws RemoteException {
        if (e()) {
            this.a.b.a(new f(this, clientMode));
        }
    }

    public void a(NavigationClientConfig navigationClientConfig) throws RemoteException {
        synchronized (this.a) {
            this.a.a = navigationClientConfig;
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.a.getNavigationStateManager());
            arrayList.add(this.a.getNavigationSuggestionManager());
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                e eVar = (e) obj;
                if (eVar != null) {
                    eVar.a(navigationClientConfig);
                }
            }
        }
    }

    public void b() throws RemoteException {
        if (e()) {
            this.a.b.a(new g(this));
        }
    }

    public c c() throws RemoteException {
        return (c) a((e<T>) this.a.getNavigationStateManager());
    }

    public com.google.android.apps.auto.sdk.nav.suggestion.c d() throws RemoteException {
        return (com.google.android.apps.auto.sdk.nav.suggestion.c) a((e<T>) this.a.getNavigationSuggestionManager());
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (a(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                NavigationProviderConfig a2 = a();
                parcel2.writeNoException();
                cgizmo.b(parcel2, a2);
                break;
            case 2:
                a((NavigationClientConfig) cgizmo.a(parcel, NavigationClientConfig.CREATOR));
                parcel2.writeNoException();
                break;
            case 3:
                a((ClientMode) cgizmo.a(parcel, ClientMode.CREATOR));
                break;
            case 4:
                b();
                parcel2.writeNoException();
                break;
            case 5:
                c c = c();
                parcel2.writeNoException();
                cgizmo.a(parcel2, (IInterface) c);
                break;
            case 6:
                com.google.android.apps.auto.sdk.nav.suggestion.c d = d();
                parcel2.writeNoException();
                cgizmo.a(parcel2, (IInterface) d);
                break;
            default:
                return false;
        }
        return true;
    }
}
