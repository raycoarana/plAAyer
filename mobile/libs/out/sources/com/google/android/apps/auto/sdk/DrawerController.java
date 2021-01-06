package com.google.android.apps.auto.sdk;

import android.os.RemoteException;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;

public class DrawerController {
    private final e a;
    /* access modifiers changed from: private */
    public MenuController b;
    /* access modifiers changed from: private */
    @Nullable
    public DrawerCallback c;

    final class a extends d {
        private a() {
        }

        /* synthetic */ a(DrawerController drawerController, byte b) {
            this();
        }

        public final void a() {
            if (DrawerController.this.c != null) {
                DrawerController.this.c.onDrawerOpened();
            }
        }

        public final void b() {
            DrawerController.this.b.a();
            if (DrawerController.this.c != null) {
                DrawerController.this.c.onDrawerOpening();
            }
        }

        public final void c() {
            MenuController b = DrawerController.this.b;
            Log.d("CSL.MenuController", "onDrawerClosed");
            b.d.clear();
            if (b.a != null) {
                b.a.a(b.b);
            }
            if (DrawerController.this.c != null) {
                DrawerController.this.c.onDrawerClosed();
            }
        }

        public final void d() {
            if (DrawerController.this.c != null) {
                DrawerController.this.c.onDrawerClosing();
            }
        }
    }

    DrawerController(e eVar, MenuController menuController) {
        this.a = eVar;
        this.b = menuController;
        try {
            this.a.a((c) new a(this, 0));
        } catch (RemoteException e) {
            Log.e("CSL.DrawerController", "Error setting DrawerCallbacks", e);
        }
    }

    @UiThread
    public void closeDrawer() {
        Log.d("CSL.DrawerController", "closeDrawer");
        try {
            this.a.d();
        } catch (RemoteException e) {
            Log.e("CSL.DrawerController", "Error closing title", e);
        }
    }

    @UiThread
    public boolean isDrawerOpen() {
        Log.d("CSL.DrawerController", "isDrawerOpen");
        try {
            return this.a.a();
        } catch (RemoteException e) {
            Log.e("CSL.DrawerController", "Error querying drawer visibility", e);
            return false;
        }
    }

    @UiThread
    public boolean isDrawerVisible() {
        Log.d("CSL.DrawerController", "isDrawerVisible");
        try {
            return this.a.b();
        } catch (RemoteException e) {
            Log.e("CSL.DrawerController", "Error querying drawer visibility state", e);
            return false;
        }
    }

    @UiThread
    public void openDrawer() {
        Log.d("CSL.DrawerController", "openDrawer");
        try {
            this.a.c();
        } catch (RemoteException e) {
            Log.e("CSL.DrawerController", "Error opening drawer", e);
        }
    }

    public void setDrawerCallback(DrawerCallback drawerCallback) {
        this.c = drawerCallback;
    }

    @UiThread
    public void setScrimColor(@ColorInt int i) {
        Log.d("CSL.DrawerController", "setScrimColor " + i);
        try {
            this.a.a(i);
        } catch (RemoteException e) {
            Log.e("CSL.DrawerController", "Error setting scrim color", e);
        }
    }
}
