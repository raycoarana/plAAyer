package android.support.car;

import android.car.CarAppFocusManager;
import android.car.CarNotConnectedException;
import android.support.car.CarAppFocusManager.OnAppFocusChangedListener;
import android.support.car.CarAppFocusManager.OnAppFocusOwnershipCallback;
import java.util.HashMap;
import java.util.Map;

public class CarAppFocusManagerEmbedded extends CarAppFocusManager {
    private final Map<OnAppFocusChangedListener, OnAppFocusChangedListenerProxy> mChangeListeners = new HashMap();
    private final CarAppFocusManager mManager;
    private final Map<OnAppFocusOwnershipCallback, OnAppFocusOwnershipCallbackProxy> mOwnershipCallbacks = new HashMap();

    private static class OnAppFocusChangedListenerProxy implements CarAppFocusManager.OnAppFocusChangedListener {
        private final OnAppFocusChangedListener mListener;
        private final CarAppFocusManager mManager;

        OnAppFocusChangedListenerProxy(CarAppFocusManager carAppFocusManager, OnAppFocusChangedListener onAppFocusChangedListener) {
            this.mManager = carAppFocusManager;
            this.mListener = onAppFocusChangedListener;
        }

        public void onAppFocusChanged(int i, boolean z) {
            this.mListener.onAppFocusChanged(this.mManager, i, z);
        }
    }

    private static class OnAppFocusOwnershipCallbackProxy implements CarAppFocusManager.OnAppFocusOwnershipCallback {
        private final OnAppFocusOwnershipCallback mListener;
        private final CarAppFocusManager mManager;

        OnAppFocusOwnershipCallbackProxy(CarAppFocusManager carAppFocusManager, OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) {
            this.mListener = onAppFocusOwnershipCallback;
            this.mManager = carAppFocusManager;
        }

        public void onAppFocusOwnershipGranted(int i) {
            this.mListener.onAppFocusOwnershipGranted(this.mManager, i);
        }

        public void onAppFocusOwnershipLost(int i) {
            this.mListener.onAppFocusOwnershipLost(this.mManager, i);
        }
    }

    CarAppFocusManagerEmbedded(Object obj) {
        this.mManager = (CarAppFocusManager) obj;
    }

    public void abandonAppFocus(OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) {
        if (onAppFocusOwnershipCallback == null) {
            throw new IllegalArgumentException("null listener");
        }
        synchronized (this) {
            OnAppFocusOwnershipCallbackProxy onAppFocusOwnershipCallbackProxy = (OnAppFocusOwnershipCallbackProxy) this.mOwnershipCallbacks.get(onAppFocusOwnershipCallback);
            if (onAppFocusOwnershipCallbackProxy != null) {
                this.mManager.abandonAppFocus(onAppFocusOwnershipCallbackProxy);
            }
        }
    }

    public void abandonAppFocus(OnAppFocusOwnershipCallback onAppFocusOwnershipCallback, int i) {
        if (onAppFocusOwnershipCallback == null) {
            throw new IllegalArgumentException("null listener");
        }
        synchronized (this) {
            OnAppFocusOwnershipCallbackProxy onAppFocusOwnershipCallbackProxy = (OnAppFocusOwnershipCallbackProxy) this.mOwnershipCallbacks.get(onAppFocusOwnershipCallback);
            if (onAppFocusOwnershipCallbackProxy != null) {
                this.mManager.abandonAppFocus(onAppFocusOwnershipCallbackProxy, i);
            }
        }
    }

    public void addFocusListener(OnAppFocusChangedListener onAppFocusChangedListener, int i) throws CarNotConnectedException {
        OnAppFocusChangedListenerProxy onAppFocusChangedListenerProxy;
        if (onAppFocusChangedListener == null) {
            throw new IllegalArgumentException("null listener");
        }
        synchronized (this) {
            onAppFocusChangedListenerProxy = (OnAppFocusChangedListenerProxy) this.mChangeListeners.get(onAppFocusChangedListener);
            if (onAppFocusChangedListenerProxy == null) {
                onAppFocusChangedListenerProxy = new OnAppFocusChangedListenerProxy(this, onAppFocusChangedListener);
                this.mChangeListeners.put(onAppFocusChangedListener, onAppFocusChangedListenerProxy);
            }
        }
        try {
            this.mManager.addFocusListener(onAppFocusChangedListenerProxy, i);
        } catch (CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }

    public boolean isOwningFocus(int i, OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) throws CarNotConnectedException {
        synchronized (this) {
            OnAppFocusOwnershipCallbackProxy onAppFocusOwnershipCallbackProxy = (OnAppFocusOwnershipCallbackProxy) this.mOwnershipCallbacks.get(onAppFocusOwnershipCallback);
            if (onAppFocusOwnershipCallbackProxy == null) {
                return false;
            }
            try {
                return this.mManager.isOwningFocus(onAppFocusOwnershipCallbackProxy, i);
            } catch (CarNotConnectedException e) {
                throw new CarNotConnectedException((Exception) e);
            }
        }
    }

    public void onCarDisconnected() {
    }

    public void removeFocusListener(OnAppFocusChangedListener onAppFocusChangedListener) {
        synchronized (this) {
            OnAppFocusChangedListenerProxy onAppFocusChangedListenerProxy = (OnAppFocusChangedListenerProxy) this.mChangeListeners.remove(onAppFocusChangedListener);
            if (onAppFocusChangedListenerProxy != null) {
                this.mManager.removeFocusListener(onAppFocusChangedListenerProxy);
            }
        }
    }

    public void removeFocusListener(OnAppFocusChangedListener onAppFocusChangedListener, int i) {
        synchronized (this) {
            OnAppFocusChangedListenerProxy onAppFocusChangedListenerProxy = (OnAppFocusChangedListenerProxy) this.mChangeListeners.get(onAppFocusChangedListener);
            if (onAppFocusChangedListenerProxy != null) {
                this.mManager.removeFocusListener(onAppFocusChangedListenerProxy, i);
            }
        }
    }

    public int requestAppFocus(int i, OnAppFocusOwnershipCallback onAppFocusOwnershipCallback) throws IllegalStateException, SecurityException, CarNotConnectedException {
        OnAppFocusOwnershipCallbackProxy onAppFocusOwnershipCallbackProxy;
        if (onAppFocusOwnershipCallback == null) {
            throw new IllegalArgumentException("null listener");
        }
        synchronized (this) {
            onAppFocusOwnershipCallbackProxy = (OnAppFocusOwnershipCallbackProxy) this.mOwnershipCallbacks.get(onAppFocusOwnershipCallback);
            if (onAppFocusOwnershipCallbackProxy == null) {
                onAppFocusOwnershipCallbackProxy = new OnAppFocusOwnershipCallbackProxy(this, onAppFocusOwnershipCallback);
                this.mOwnershipCallbacks.put(onAppFocusOwnershipCallback, onAppFocusOwnershipCallbackProxy);
            }
        }
        try {
            return this.mManager.requestAppFocus(i, onAppFocusOwnershipCallbackProxy);
        } catch (CarNotConnectedException e) {
            throw new CarNotConnectedException((Exception) e);
        }
    }
}
