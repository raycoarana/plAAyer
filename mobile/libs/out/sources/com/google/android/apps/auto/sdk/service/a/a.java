package com.google.android.apps.auto.sdk.service.a;

import android.support.annotation.VisibleForTesting;
import android.support.car.CarAppFocusManager;
import android.support.car.CarAppFocusManager.OnAppFocusChangedListener;
import android.support.car.CarAppFocusManager.OnAppFocusOwnershipCallback;
import android.util.Log;
import com.google.android.gms.car.CarMessageManager;
import com.google.android.gms.car.CarNotConnectedException;

public final class a extends CarAppFocusManager {
    private CarMessageManager a;
    @VisibleForTesting
    private b b = new b(this);

    public a(CarMessageManager carMessageManager) {
        this.a = carMessageManager;
        this.a.registerMessageListener(this.b);
    }

    protected static int a(int i) {
        switch (i) {
            case 0:
                return 2;
            case 1:
                return 1;
            default:
                Log.d("CarAppFocusManagerGms", "Unknown category " + i);
                throw new IllegalArgumentException("invalid category type");
        }
    }

    private final void a(int i, boolean z) throws CarNotConnectedException {
        int i2 = 1;
        CarMessageManager carMessageManager = this.a;
        switch (i) {
            case 0:
            case 1:
                switch (i) {
                    case 0:
                        if (!z) {
                            i2 = 2;
                            break;
                        }
                        break;
                    case 1:
                        if (!z) {
                            i2 = 0;
                            break;
                        }
                        break;
                    default:
                        Log.d("CarAppFocusManagerGms", "Unknown category " + i);
                        throw new IllegalArgumentException("invalid category");
                }
                carMessageManager.sendIntegerMessage(i, 0, i2);
                return;
            default:
                Log.d("CarAppFocusManagerGms", "Unknown appType " + i);
                throw new IllegalArgumentException("invalid category");
        }
    }

    private static int b(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 0;
            default:
                Log.d("CarAppFocusManagerGms", "Unknown appType " + i);
                throw new IllegalArgumentException("invalid app type");
        }
    }

    public final void abandonAppFocus(OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) {
        synchronized (this.b) {
            for (Integer num : this.b.a(onAppFocusOwnershipCallback)) {
                try {
                    a(num.intValue(), false);
                    this.a.releaseCategory(num.intValue());
                } catch (CarNotConnectedException e) {
                    Log.d("CarAppFocusManagerGms", "Abandoned app focus but car is not connected", e);
                } catch (IllegalStateException e2) {
                    Log.d("CarAppFocusManagerGms", "Abandoned app focus but caller isn't the owner.  Ignoring.", e2);
                }
            }
        }
    }

    public final void abandonAppFocus(OnAppFocusOwnershipCallback onAppFocusOwnershipCallback, int i) {
        int b2 = b(i);
        synchronized (this.b) {
            if (this.b.a(b2) == onAppFocusOwnershipCallback) {
                this.b.b(b2, onAppFocusOwnershipCallback);
                try {
                    a(b2, false);
                    this.a.releaseCategory(b2);
                } catch (CarNotConnectedException e) {
                    Log.d("CarAppFocusManagerGms", "Abandoned app focus but car is not connected", e);
                } catch (IllegalStateException e2) {
                    Log.d("CarAppFocusManagerGms", "Abandoned app focus but caller isn't the owner.  Ignoring.", e2);
                }
            }
        }
        return;
    }

    public final void addFocusListener(OnAppFocusChangedListener onAppFocusChangedListener, int i) throws android.support.car.CarNotConnectedException {
        this.b.b(b(i), onAppFocusChangedListener);
    }

    public final boolean isOwningFocus(int i, OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) throws android.support.car.CarNotConnectedException {
        return this.b.a(onAppFocusOwnershipCallback, b(i));
    }

    public final void onCarDisconnected() {
    }

    public final void removeFocusListener(OnAppFocusChangedListener onAppFocusChangedListener) {
        this.b.a(onAppFocusChangedListener);
    }

    public final void removeFocusListener(OnAppFocusChangedListener onAppFocusChangedListener, int i) {
        this.b.a(b(i), onAppFocusChangedListener);
    }

    public final int requestAppFocus(int i, OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) throws IllegalStateException, SecurityException, android.support.car.CarNotConnectedException {
        int i2 = 1;
        if (onAppFocusOwnershipCallback == null) {
            throw new IllegalArgumentException("null listener");
        }
        synchronized (this.b) {
            int b2 = b(i);
            OnAppFocusOwnershipCallback a2 = this.b.a(b2);
            if (a2 != onAppFocusOwnershipCallback) {
                if (a2 != null) {
                    a2.onAppFocusOwnershipLost(this, i);
                    this.b.a(b2, onAppFocusOwnershipCallback);
                } else {
                    try {
                        boolean acquireCategory = this.a.acquireCategory(b2);
                        if (acquireCategory) {
                            this.b.a(b2, onAppFocusOwnershipCallback);
                            a(b2, true);
                            onAppFocusOwnershipCallback.onAppFocusOwnershipGranted(this, i);
                        }
                        if (!acquireCategory) {
                            i2 = 0;
                        }
                    } catch (CarNotConnectedException e) {
                        throw new android.support.car.CarNotConnectedException((Exception) e);
                    }
                }
            }
        }
        return i2;
    }
}
