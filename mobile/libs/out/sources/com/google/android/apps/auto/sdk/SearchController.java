package com.google.android.apps.auto.sdk;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.List;

public class SearchController {
    private final q a;
    private a b;

    static final class a extends p {
        @Nullable
        private SearchCallback a;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        /* access modifiers changed from: private */
        public final void a(SearchCallback searchCallback) {
            this.a = searchCallback;
        }

        public final void a() {
            if (this.a != null) {
                this.a.onSearchStart();
            }
        }

        public final void a(SearchItem searchItem) {
            if (this.a != null) {
                this.a.onSearchItemSelected(searchItem);
            }
        }

        public final void a(String str) {
            if (this.a != null) {
                this.a.onSearchTextChanged(str);
            }
        }

        public final void b() {
            if (this.a != null) {
                this.a.onSearchStop();
            }
        }

        public final boolean b(String str) {
            if (this.a != null) {
                return this.a.onSearchSubmitted(str);
            }
            return false;
        }
    }

    SearchController(q qVar) {
        this.a = qVar;
    }

    public void hideSearchBox() {
        Log.d("CSL.SearchController", "hideSearchBox");
        if (this.b == null) {
            throw new IllegalStateException("No SearchCallback is set");
        }
        try {
            this.a.b();
        } catch (RemoteException e) {
            Log.e("CSL.SearchController", "Error disabling search box", e);
        }
    }

    public void setSearchCallback(@Nullable SearchCallback searchCallback) {
        String valueOf = String.valueOf(searchCallback);
        Log.d("CSL.SearchController", new StringBuilder(String.valueOf(valueOf).length() + 18).append("setSearchCallback ").append(valueOf).toString());
        if (this.b == null) {
            this.b = new a(0);
            try {
                this.a.a((o) this.b);
            } catch (RemoteException e) {
                Log.e("CSL.SearchController", "Error setting SearchCallback", e);
            }
        }
        this.b.a(searchCallback);
    }

    public void setSearchHint(@Nullable CharSequence charSequence) {
        String valueOf = String.valueOf(charSequence);
        Log.d("CSL.SearchController", new StringBuilder(String.valueOf(valueOf).length() + 14).append("setSearchHint ").append(valueOf).toString());
        try {
            this.a.a(charSequence);
        } catch (RemoteException e) {
            Log.e("CSL.SearchController", "Error setting search hint", e);
        }
    }

    public void setSearchItems(List<SearchItem> list) {
        String valueOf = String.valueOf(list);
        Log.d("CSL.SearchController", new StringBuilder(String.valueOf(valueOf).length() + 15).append("setSearchItems ").append(valueOf).toString());
        if (list == null) {
            throw new IllegalArgumentException("SearchItems cannot be null.");
        }
        try {
            this.a.a(list);
        } catch (RemoteException e) {
            Log.e("CSL.SearchController", "Error setting search items", e);
        }
    }

    public void showSearchBox() {
        Log.d("CSL.SearchController", "showSearchBox");
        if (this.b == null) {
            throw new IllegalStateException("No SearchCallback is set");
        }
        try {
            this.a.a();
        } catch (RemoteException e) {
            Log.e("CSL.SearchController", "Error enabling search box", e);
        }
    }

    public void startSearch(String str) {
        Log.d("CSL.SearchController", "startSearch");
        if (this.b == null) {
            throw new IllegalStateException("No SearchCallback is set");
        }
        try {
            this.a.a(str);
        } catch (RemoteException e) {
            Log.e("CSL.SearchController", "Error start search", e);
        }
    }

    public void stopSearch() {
        Log.d("CSL.SearchController", "stopSearch");
        if (this.b == null) {
            throw new IllegalStateException("No SearchCallback is set");
        }
        try {
            this.a.c();
        } catch (RemoteException e) {
            Log.e("CSL.SearchController", "Error stopping search", e);
        }
    }
}
