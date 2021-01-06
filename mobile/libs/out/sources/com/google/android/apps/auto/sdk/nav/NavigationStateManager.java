package com.google.android.apps.auto.sdk.nav;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.apps.auto.sdk.nav.state.CarInstrumentClusterConfig;
import com.google.android.apps.auto.sdk.nav.state.TurnEvent;
import com.google.android.apps.auto.sdk.nav.state.c;
import javax.annotation.concurrent.GuardedBy;

public class NavigationStateManager extends e<c> {
    /* access modifiers changed from: private */
    @Nullable
    @GuardedBy("this")
    public com.google.android.apps.auto.sdk.nav.state.a a;
    /* access modifiers changed from: private */
    @GuardedBy("this")
    @NonNull
    public CarInstrumentClusterConfig b = new CarInstrumentClusterConfig();
    private final a c = new a();
    private final c d = new h(this);

    public static final class a {
        private long a = 0;

        public final boolean a(@Nullable TurnEvent turnEvent, @NonNull CarInstrumentClusterConfig carInstrumentClusterConfig) {
            if (turnEvent == null) {
                return false;
            }
            if (turnEvent.getTurnImage() != null) {
                if (!carInstrumentClusterConfig.supportsImages()) {
                    Log.w("NavigationStateManager", "Dropping turn event since it contains an image but the HU does not support images.");
                    return false;
                }
                new Options().inJustDecodeBounds = true;
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(turnEvent.getTurnImage(), 0, turnEvent.getTurnImage().length);
                if (decodeByteArray == null) {
                    Log.w("NavigationStateManager", "Dropping turn event since image cannot be decoded");
                    return false;
                } else if (!(decodeByteArray.getWidth() == carInstrumentClusterConfig.getImageWidth() && decodeByteArray.getHeight() == carInstrumentClusterConfig.getImageHeight())) {
                    Log.w("NavigationStateManager", "Dropping turn event since it contains an image with dimensions that do not match the head unit's configuration");
                    return false;
                }
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.a < ((long) carInstrumentClusterConfig.getMinMessageIntervalMs())) {
                Log.w("NavigationStateManager", "Rate limiting turn event message");
                return false;
            }
            this.a = elapsedRealtime;
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Object a() {
        return this.d;
    }

    @NonNull
    public CarInstrumentClusterConfig getInstrumentClusterConfig() {
        CarInstrumentClusterConfig carInstrumentClusterConfig;
        synchronized (this) {
            carInstrumentClusterConfig = this.b;
        }
        return carInstrumentClusterConfig;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    @android.support.annotation.CallSuper
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendNavigationSummary(com.google.android.apps.auto.sdk.nav.state.NavigationSummary r2) throws android.os.RemoteException {
        /*
            r1 = this;
            if (r2 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            monitor-enter(r1)
            boolean r0 = r1.b()     // Catch:{ all -> 0x000c }
            if (r0 != 0) goto L_0x000f
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            goto L_0x0002
        L_0x000c:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            throw r0
        L_0x000f:
            com.google.android.apps.auto.sdk.nav.state.a r0 = r1.a     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x0018
            com.google.android.apps.auto.sdk.nav.state.a r0 = r1.a     // Catch:{ all -> 0x000c }
            r0.a(r2)     // Catch:{ all -> 0x000c }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.auto.sdk.nav.NavigationStateManager.sendNavigationSummary(com.google.android.apps.auto.sdk.nav.state.NavigationSummary):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    @android.support.annotation.CallSuper
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendTurnEvent(com.google.android.apps.auto.sdk.nav.state.TurnEvent r3) throws android.os.RemoteException {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            monitor-enter(r2)
            boolean r0 = r2.b()     // Catch:{ all -> 0x000c }
            if (r0 != 0) goto L_0x000f
            monitor-exit(r2)     // Catch:{ all -> 0x000c }
            goto L_0x0002
        L_0x000c:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x000c }
            throw r0
        L_0x000f:
            com.google.android.apps.auto.sdk.nav.NavigationStateManager$a r0 = r2.c     // Catch:{ all -> 0x000c }
            com.google.android.apps.auto.sdk.nav.state.CarInstrumentClusterConfig r1 = r2.b     // Catch:{ all -> 0x000c }
            boolean r0 = r0.a(r3, r1)     // Catch:{ all -> 0x000c }
            if (r0 != 0) goto L_0x001b
            monitor-exit(r2)     // Catch:{ all -> 0x000c }
            goto L_0x0002
        L_0x001b:
            com.google.android.apps.auto.sdk.nav.state.a r0 = r2.a     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x0024
            com.google.android.apps.auto.sdk.nav.state.a r0 = r2.a     // Catch:{ all -> 0x000c }
            r0.a(r3)     // Catch:{ all -> 0x000c }
        L_0x0024:
            monitor-exit(r2)     // Catch:{ all -> 0x000c }
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.auto.sdk.nav.NavigationStateManager.sendTurnEvent(com.google.android.apps.auto.sdk.nav.state.TurnEvent):void");
    }
}
