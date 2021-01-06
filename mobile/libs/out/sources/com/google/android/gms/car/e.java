package com.google.android.gms.car;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class e extends c {
    final Handler a = new h(this);
    FragmentController b;
    boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;

    static {
        FragmentManager.enableDebugLogging(Log.isLoggable("CAR.PROJECTION", 2));
    }

    private static String a(View view) {
        String str;
        char c2 = 'F';
        char c3 = '.';
        StringBuilder sb = new StringBuilder(128);
        sb.append(view.getClass().getName());
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(view)));
        sb.append(' ');
        switch (view.getVisibility()) {
            case 0:
                sb.append('V');
                break;
            case 4:
                sb.append('I');
                break;
            case 8:
                sb.append('G');
                break;
            default:
                sb.append('.');
                break;
        }
        sb.append(view.isFocusable() ? 'F' : '.');
        sb.append(view.isEnabled() ? 'E' : '.');
        sb.append(view.willNotDraw() ? '.' : 'D');
        sb.append(view.isHorizontalScrollBarEnabled() ? 'H' : '.');
        sb.append(view.isVerticalScrollBarEnabled() ? 'V' : '.');
        sb.append(view.isClickable() ? 'C' : '.');
        sb.append(view.isLongClickable() ? 'L' : '.');
        sb.append(' ');
        if (!view.isFocused()) {
            c2 = '.';
        }
        sb.append(c2);
        sb.append(view.isSelected() ? 'S' : '.');
        if (view.isPressed()) {
            c3 = 'P';
        }
        sb.append(c3);
        sb.append(' ');
        sb.append(view.getLeft());
        sb.append(',');
        sb.append(view.getTop());
        sb.append('-');
        sb.append(view.getRight());
        sb.append(',');
        sb.append(view.getBottom());
        int id = view.getId();
        if (id != -1) {
            sb.append(" #");
            sb.append(Integer.toHexString(id));
            Resources resources = view.getResources();
            if (!(id == 0 || resources == null)) {
                switch (-16777216 & id) {
                    case 16777216:
                        str = "android";
                        break;
                    case 2130706432:
                        str = "app";
                        break;
                    default:
                        try {
                            str = resources.getResourcePackageName(id);
                            break;
                        } catch (NotFoundException e2) {
                            break;
                        }
                }
                String resourceTypeName = resources.getResourceTypeName(id);
                String resourceEntryName = resources.getResourceEntryName(id);
                sb.append(" ");
                sb.append(str);
                sb.append(":");
                sb.append(resourceTypeName);
                sb.append("/");
                sb.append(resourceEntryName);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private final void a(String str, PrintWriter printWriter, View view) {
        printWriter.print(str);
        if (view == null) {
            printWriter.println("null");
            return;
        }
        printWriter.println(a(view));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                String concat = String.valueOf(str).concat("  ");
                for (int i = 0; i < childCount; i++) {
                    a(concat, printWriter, viewGroup.getChildAt(i));
                }
            }
        }
    }

    public final void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String concat = String.valueOf(str).concat("  ");
        printWriter.print(concat);
        printWriter.print("mCreated=");
        printWriter.print(this.d);
        printWriter.print(" mResumed=");
        printWriter.print(this.e);
        printWriter.print(" mStopped=");
        printWriter.print(this.c);
        printWriter.print(" mReallyStopped=");
        printWriter.println(this.f);
        this.b.dumpLoaders(concat, fileDescriptor, printWriter, strArr);
        this.b.getSupportFragmentManager().dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.println("View Hierarchy:");
        a(String.valueOf(str).concat("  "), printWriter, c().getDecorView());
    }

    /* access modifiers changed from: 0000 */
    public final void a(boolean z) {
        if (!this.f) {
            this.f = true;
            this.g = z;
            this.a.removeMessages(1);
            this.b.doLoaderStop(this.g);
            this.b.dispatchReallyStop();
        }
    }

    public FragmentManager getSupportFragmentManager() {
        return this.b.getSupportFragmentManager();
    }

    public void onBackPressed() {
        if (!this.b.getSupportFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.b.dispatchConfigurationChanged(configuration);
    }

    public void onCreate(@Nullable Bundle bundle) {
        if (getBaseContext() == null) {
            throw new IllegalStateException("Context not attached to CarActivity");
        }
        this.b.attachHost(null);
        if (getLayoutInflater().getFactory() == null) {
            getLayoutInflater().setFactory(this);
        }
        super.onCreate(bundle);
        i iVar = (i) d();
        if (iVar != null) {
            this.b.restoreLoaderNonConfig(iVar.b);
        }
        if (bundle != null) {
            this.b.restoreAllState(bundle.getParcelable("android:support:fragments"), iVar != null ? iVar.a : null);
        }
        this.b.dispatchCreate();
    }

    @Nullable
    public View onCreateView(String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return super.onCreateView(str, context, attributeSet);
        }
        View onCreateView = this.b.onCreateView(null, str, context, attributeSet);
        return onCreateView == null ? super.onCreateView(str, context, attributeSet) : onCreateView;
    }

    public void onDestroy() {
        super.onDestroy();
        a(false);
        this.b.dispatchDestroy();
        this.b.doLoaderDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.b.dispatchLowMemory();
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.b.noteStateNotSaved();
    }

    public void onPause() {
        super.onPause();
        this.e = false;
        if (this.a.hasMessages(2)) {
            this.a.removeMessages(2);
            this.b.dispatchResume();
        }
        this.b.dispatchPause();
    }

    public void onPostResume() {
        super.onPostResume();
        this.a.removeMessages(2);
        this.b.dispatchResume();
        this.b.execPendingActions();
    }

    public void onResume() {
        super.onResume();
        this.a.sendEmptyMessage(2);
        this.e = true;
        this.b.execPendingActions();
    }

    public final Object onRetainNonConfigurationInstance() {
        if (this.c) {
            a(true);
        }
        List<Fragment> retainNonConfig = this.b.retainNonConfig();
        SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig = this.b.retainLoaderNonConfig();
        if (retainNonConfig == null && retainLoaderNonConfig == null) {
            return null;
        }
        i iVar = new i();
        iVar.a = retainNonConfig;
        iVar.b = retainLoaderNonConfig;
        return iVar;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable saveAllState = this.b.saveAllState();
        if (saveAllState != null) {
            bundle.putParcelable("android:support:fragments", saveAllState);
        }
    }

    public void onStart() {
        super.onStart();
        this.c = false;
        this.f = false;
        this.a.removeMessages(1);
        if (!this.d) {
            this.d = true;
            this.b.dispatchActivityCreated();
        }
        this.b.noteStateNotSaved();
        this.b.execPendingActions();
        this.b.doLoaderStart();
        this.b.dispatchStart();
        this.b.reportLoaderStart();
    }

    public void onStop() {
        super.onStop();
        this.c = true;
        this.a.sendEmptyMessage(1);
        this.b.dispatchStop();
    }

    @Hide
    @VisibleForTesting
    public void setContext(Context context) {
        super.setContext(context);
        this.b = FragmentController.createController(new f(this, this));
    }
}
