package com.google.android.gms.car;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;

final class f extends FragmentHostCallback<e> {
    private /* synthetic */ e a;

    public f(e eVar, Context context) {
        this.a = eVar;
        super(context, eVar.a, 0);
    }

    public final void onDump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.a.a(str, fileDescriptor, printWriter, strArr);
    }

    @Nullable
    public final View onFindViewById(int i) {
        return this.a.findViewById(i);
    }

    public final /* synthetic */ Object onGetHost() {
        return this.a;
    }

    public final LayoutInflater onGetLayoutInflater() {
        return this.a.getLayoutInflater().cloneInContext(this.a.getBaseContext());
    }

    public final int onGetWindowAnimations() {
        Window c = this.a.c();
        if (c == null) {
            return 0;
        }
        return c.getAttributes().windowAnimations;
    }

    public final boolean onHasView() {
        Window c = this.a.c();
        return (c == null || c.peekDecorView() == null) ? false : true;
    }

    public final boolean onHasWindowAnimations() {
        return this.a.c() != null;
    }

    public final boolean onShouldSaveFragmentState(Fragment fragment) {
        return !this.a.b();
    }

    public final void onStartActivityFromFragment(Fragment fragment, Intent intent, int i) {
        if (i != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
    }

    public final void onSupportInvalidateOptionsMenu() {
        throw new UnsupportedOperationException();
    }
}
