package com.google.android.apps.auto.sdk;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.annotation.AnyThread;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.List;
import java.util.Stack;

public class MenuController {
    @Nullable
    @VisibleForTesting
    d a;
    @VisibleForTesting
    MenuAdapter b;
    @VisibleForTesting
    Bundle c;
    @VisibleForTesting
    Stack<Integer> d;
    /* access modifiers changed from: private */
    public final Context e;
    /* access modifiers changed from: private */
    public final m f;
    /* access modifiers changed from: private */
    public final MenuAdapterCallback g;
    private MenuAdapter h;

    abstract class a implements MenuAdapterCallback {
        private a() {
        }

        /* synthetic */ a(MenuController menuController, byte b) {
            this();
        }

        /* access modifiers changed from: 0000 */
        @MainThread
        public final void a() {
            try {
                MenuController.this.f.d();
            } catch (RemoteException e) {
                Log.e("CSL.MenuController", "Error showing loading indication", e);
            }
        }

        /* access modifiers changed from: 0000 */
        @MainThread
        public final void a(MenuAdapter menuAdapter) {
            String valueOf = String.valueOf(menuAdapter);
            Log.d("CSL.MenuController", new StringBuilder(String.valueOf(valueOf).length() + 21).append("notifyDataSetChanged ").append(valueOf).toString());
            if (menuAdapter == MenuController.this.a.a) {
                try {
                    MenuController.this.f.a();
                } catch (RemoteException e) {
                    Log.e("CSL.MenuController", "Error notifying data set changed", e);
                }
            }
        }

        public void activateAlphaJumpKeyboard(List<AlphaJumpKeyItem> list) {
            try {
                MenuController.this.f.a(list);
            } catch (RemoteException e) {
                Log.e("CSL.MenuController", "Error activating AlphaJumpKeyboard.", e);
            }
        }

        /* access modifiers changed from: 0000 */
        @MainThread
        public final void b() {
            try {
                MenuController.this.f.e();
            } catch (RemoteException e) {
                Log.e("CSL.MenuController", "Error hiding loading indicator", e);
            }
        }

        public void disableAlphaJump() {
            try {
                MenuController.this.f.g();
            } catch (RemoteException e) {
                Log.e("CSL.MenuController", "Error disabling AlphaJump.", e);
            }
        }

        public void enableAlphaJump() {
            try {
                MenuController.this.f.f();
            } catch (RemoteException e) {
                Log.e("CSL.MenuController", "Error enabling AlphaJump.", e);
            }
        }
    }

    static final class b extends MenuAdapter {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final MenuItem getMenuItem(int i) {
            return null;
        }

        public final int getMenuItemCount() {
            return 0;
        }
    }

    final class c extends a {
        private final Handler b;

        private c(MenuController menuController) {
            super(menuController, 0);
            this.b = new Handler();
        }

        /* synthetic */ c(MenuController menuController, byte b2) {
            this(menuController);
        }

        @AnyThread
        public final void hideLoadingIndicator() {
            this.b.post(new ab(this));
        }

        @AnyThread
        public final void notifyDataSetChanged(MenuAdapter menuAdapter) {
            this.b.post(new y(this, menuAdapter));
        }

        @AnyThread
        public final void notifyItemChanged(MenuAdapter menuAdapter, int i) {
            this.b.post(new z(this, menuAdapter, i));
        }

        @AnyThread
        public final void showLoadingIndicator() {
            this.b.post(new aa(this));
        }
    }

    @VisibleForTesting
    final class d extends l {
        @VisibleForTesting
        @NonNull
        MenuAdapter a;

        public d(MenuAdapter menuAdapter) {
            this.a = menuAdapter;
        }

        /* access modifiers changed from: private */
        public final void a(@NonNull MenuAdapter menuAdapter) {
            this.a.onDetach();
            boolean z = this.a != menuAdapter;
            this.a = menuAdapter;
            this.a.a(MenuController.this.c);
            this.a.onAttach(MenuController.this.g);
            if (z) {
                this.a.notifyDataSetChanged();
            }
        }

        /* access modifiers changed from: private */
        public final void b(int i) {
            if (((long) MenuController.this.d.size()) >= MenuController.this.b()) {
                CarToast.makeText(MenuController.this.e, (CharSequence) String.format("Cannot have more than %s levels of submenu", new Object[]{Long.valueOf(MenuController.this.b())}), 1).show();
                return;
            }
            if (this.a.a(i) == null) {
                MenuAdapter onLoadSubmenu = this.a.onLoadSubmenu(i);
                if (onLoadSubmenu == null) {
                    String valueOf = String.valueOf(this.a);
                    Log.w("CSL.MenuController", new StringBuilder(String.valueOf(valueOf).length() + 52).append(valueOf).append(" onLoadSubmenu returns null for position ").append(i).toString());
                    return;
                }
                this.a.a(i, onLoadSubmenu);
            }
            MenuAdapter a2 = this.a.a(i);
            a2.a(this.a);
            MenuAdapter menuAdapter = this.a;
            MenuController.this.d.add(Integer.valueOf(i));
            a(a2);
        }

        public final int a() {
            return this.a.getMenuItemCount();
        }

        public final MenuItem a(int i) {
            MenuItem menuItem = this.a.getMenuItem(i);
            menuItem.a(i);
            return menuItem;
        }

        public final void a(Bundle bundle) {
            MenuController.this.c = bundle;
            this.a.a(MenuController.this.c);
        }

        public final void a(MenuItem menuItem) {
            String valueOf = String.valueOf(menuItem);
            Log.d("CSL.MenuController", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onItemClicked ").append(valueOf).toString());
            int a2 = menuItem.a();
            if (menuItem.getType() == 2) {
                b(a2);
                return;
            }
            if (menuItem.getType() == 1) {
                a(a2).a(menuItem.isChecked());
            }
            this.a.onMenuItemClicked(a2);
        }

        public final boolean b() {
            Log.d("CSL.MenuController", "hasParent");
            return this.a.a() != null;
        }

        public final void c() {
            String valueOf = String.valueOf(this.a.a());
            Log.d("CSL.MenuController", new StringBuilder(String.valueOf(valueOf).length() + 21).append("onBackClicked parent=").append(valueOf).toString());
            if (this.a.a() == null) {
                throw new IllegalStateException("onBackClicked when there is no parent submenu");
            }
            MenuController.this.d.pop();
            a(this.a.a());
        }

        public final void d() {
        }

        public final void e() {
        }

        public final String f() {
            return this.a.getTitle();
        }
    }

    @VisibleForTesting
    public MenuController(Context context, m mVar) {
        this(context, mVar, true);
    }

    private MenuController(Context context, m mVar, boolean z) {
        this.c = null;
        this.d = new Stack<>();
        this.e = context;
        this.f = mVar;
        this.b = new b(0);
        this.g = new c(this, 0);
    }

    /* access modifiers changed from: private */
    public final long b() {
        if (this.c != null) {
            return this.c.getLong("max_submenu_levels", 1000);
        }
        return 1000;
    }

    @VisibleForTesting
    public final void a() {
        Log.d("CSL.MenuController", "onDrawerOpening");
        this.d.clear();
        if (this.a != null) {
            this.a.a(this.h);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(Bundle bundle) {
        Log.d("CSL.MenuController", "onRestoreInstanceState");
        if (this.a == null) {
            Log.w("CSL.MenuController", "Root MenuAdapter is null after day/night mode Activity recreate.");
            return;
        }
        if (bundle.containsKey("com.google.android.apps.auto.sdk.MenuController.KEY_SUBMENU_PATH")) {
            int[] intArray = bundle.getIntArray("com.google.android.apps.auto.sdk.MenuController.KEY_SUBMENU_PATH");
            for (int a2 : intArray) {
                this.a.b(a2);
            }
        }
        this.g.notifyDataSetChanged(this.a.a);
    }

    @UiThread
    public void hideMenuButton() {
        try {
            this.f.c();
        } catch (RemoteException e2) {
            Log.e("CSL.MenuController", "Error hide menu button", e2);
        }
    }

    @UiThread
    public void setRootMenuAdapter(@Nullable MenuAdapter menuAdapter) {
        String valueOf = String.valueOf(menuAdapter);
        Log.d("CSL.MenuController", new StringBuilder(String.valueOf(valueOf).length() + 19).append("setRootMenuAdapter ").append(valueOf).toString());
        if (menuAdapter == null) {
            this.h = this.b;
        } else {
            menuAdapter.a((MenuAdapter) null);
            this.h = menuAdapter;
        }
        if (this.c != null) {
            this.h.a(this.c);
        }
        if (this.a == null) {
            this.a = new d(this.b);
            try {
                this.f.a((k) this.a);
            } catch (RemoteException e2) {
                Log.e("CSL.MenuController", "Error setting menu callbacks", e2);
            }
        }
    }

    @UiThread
    public void showMenuButton() {
        try {
            this.f.b();
        } catch (RemoteException e2) {
            Log.e("CSL.MenuController", "Error showing menu button", e2);
        }
    }
}
