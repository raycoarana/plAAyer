package com.google.android.gms.car;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;
import android.view.Window;
import com.google.android.gms.car.CarActivityHost.HostedCarActivity;
import com.google.android.gms.car.input.InputManager;
import com.google.android.gms.common.internal.Hide;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@TargetApi(21)
public class c extends ContextWrapper implements Factory, HostedCarActivity {
    private CarActivityHost a;
    private boolean b;

    public c() {
        super(null);
    }

    public final InputManager a() {
        return this.a.getInputManager();
    }

    @Nullable
    public final Object a(String str) throws CarNotSupportedException, CarNotConnectedException {
        return this.a.getCarManager(str);
    }

    @Hide
    public void attach(CarActivityHost carActivityHost) {
        if (Log.isLoggable("CAR.PROJECTION", 2)) {
            Log.v("CAR.PROJECTION", "Context DPI: " + getResources().getConfiguration().densityDpi);
        }
        this.a = carActivityHost;
    }

    public final boolean b() {
        if (this.a != null) {
            return this.a.isFinishing();
        }
        return true;
    }

    public final Window c() {
        return this.a.getWindow();
    }

    @Nullable
    public final Object d() {
        return this.a.getNonConfigurationInstance();
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public View findViewById(int i) {
        return this.a.findViewById(i);
    }

    public Intent getIntent() {
        return this.a.getIntent();
    }

    public LayoutInflater getLayoutInflater() {
        return this.a.getLayoutInflater();
    }

    public void onAccessibilityScanRequested(IBinder iBinder) {
    }

    public void onBackPressed() {
        this.b = false;
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onCreate(Bundle bundle) {
    }

    @Hide
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return null;
    }

    public void onDestroy() {
    }

    public void onFrameRateChange(int i) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        this.b = true;
        onBackPressed();
        return this.b;
    }

    public void onLowMemory() {
    }

    public void onNewIntent(Intent intent) {
    }

    public void onPause() {
    }

    public void onPostResume() {
    }

    public void onPowerStateChange(int i) {
    }

    public void onRestoreInstanceState(Bundle bundle) {
        this.a.onRestoreInstanceState(bundle);
    }

    public void onResume() {
    }

    public Object onRetainNonConfigurationInstance() {
        return null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.a.onSaveInstanceState(bundle);
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onWindowFocusChanged(boolean z, boolean z2) {
    }

    public void setContentView(int i) {
        this.a.setContentView(i);
    }

    public void setContentView(View view) {
        this.a.setContentView(view);
    }

    @Hide
    @CallSuper
    public void setContext(Context context) {
        attachBaseContext(context);
    }

    @Deprecated
    public void setIgnoreConfigChanges(int i) {
        this.a.setIgnoreConfigChanges(i);
    }

    public void setIntent(Intent intent) {
        this.a.setIntent(intent);
    }
}
