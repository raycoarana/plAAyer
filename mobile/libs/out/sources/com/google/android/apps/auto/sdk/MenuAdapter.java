package com.google.android.apps.auto.sdk;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.SparseArray;

public abstract class MenuAdapter {
    @Nullable
    @VisibleForTesting
    MenuAdapterCallback a;
    private final SparseArray<MenuAdapter> b = new SparseArray<>();
    @Nullable
    private MenuAdapter c;
    @VisibleForTesting
    private boolean d;
    protected Bundle mConfig;

    @Nullable
    @VisibleForTesting
    public final MenuAdapter a() {
        return this.c;
    }

    @VisibleForTesting
    @NonNull
    public final MenuAdapter a(int i) {
        return (MenuAdapter) this.b.get(i);
    }

    @VisibleForTesting
    public final void a(int i, MenuAdapter menuAdapter) {
        this.b.put(i, menuAdapter);
    }

    /* access modifiers changed from: 0000 */
    public final void a(Bundle bundle) {
        this.mConfig = bundle;
        if (Log.isLoggable("CSL.MenuAdapter", 3)) {
            String valueOf = String.valueOf(bundle);
            String valueOf2 = String.valueOf(this);
            Log.d("CSL.MenuAdapter", new StringBuilder(String.valueOf(valueOf).length() + 38 + String.valueOf(valueOf2).length()).append("recieved new config bundle ").append(valueOf).append(" in object ").append(valueOf2).toString());
        }
    }

    @VisibleForTesting
    public final void a(MenuAdapter menuAdapter) {
        this.c = menuAdapter;
    }

    public abstract MenuItem getMenuItem(int i);

    public abstract int getMenuItemCount();

    public String getTitle() {
        return null;
    }

    @CallSuper
    public void hideLoadingIndicator() {
        if (this.d) {
            this.d = false;
            if (this.a != null) {
                this.a.hideLoadingIndicator();
            }
        }
    }

    @CallSuper
    public void notifyDataSetChanged() {
        if (this.a == null) {
            Log.w("CSL.MenuAdapter", "Cannot notify dataset changed because this MenuAdapter is not connected to a root menu");
            return;
        }
        this.b.clear();
        this.a.notifyDataSetChanged(this);
    }

    @CallSuper
    public void notifyItemChanged(int i) {
        if (this.a == null) {
            Log.w("CSL.MenuAdapter", "Cannot notify item changed because this MenuAdapter is not connected to a root menu");
            return;
        }
        this.b.put(i, null);
        this.a.notifyItemChanged(this, i);
    }

    @VisibleForTesting
    public void onAttach(MenuAdapterCallback menuAdapterCallback) {
        this.a = menuAdapterCallback;
        if (this.d) {
            showLoadingIndicator();
        }
        onEnter();
    }

    @VisibleForTesting
    public void onDetach() {
        onExit();
        if (this.d) {
            hideLoadingIndicator();
        }
        this.a = null;
    }

    public void onEnter() {
    }

    public void onExit() {
    }

    @Nullable
    public MenuAdapter onLoadSubmenu(int i) {
        return null;
    }

    public void onMenuItemClicked(int i) {
    }

    @CallSuper
    public void showLoadingIndicator() {
        if (!this.d) {
            this.d = true;
            if (this.a != null) {
                this.a.showLoadingIndicator();
            }
        }
    }
}
