package com.google.android.apps.auto.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.car.Car;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.car.CarInfoManager;
import com.google.android.gms.car.CarInfoManager.CarInfo;
import com.google.android.gms.car.CarNotConnectedException;
import com.google.android.gms.car.CarNotSupportedException;
import com.google.android.gms.car.e;

public class CarActivity extends e {
    @VisibleForTesting
    private CarUiController d;
    private ad e;
    private SupportLibViewLoader f;
    private ViewGroup g;

    public View findViewById(int i) {
        return super.findViewById(i);
    }

    @CallSuper
    public CarUiController getCarUiController() {
        return this.d;
    }

    @CallSuper
    public Intent getIntent() {
        return super.getIntent();
    }

    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }

    public FragmentManager getSupportFragmentManager() {
        return super.getSupportFragmentManager();
    }

    public void onAccessibilityScanRequested(IBinder iBinder) {
        this.d.a(iBinder);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    @CallSuper
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        String valueOf = String.valueOf(configuration);
        Log.d("CSL.CarActivity", new StringBuilder(String.valueOf(valueOf).length() + 23).append("onConfigurationChanged ").append(valueOf).toString());
        getResources().getConfiguration().updateFrom(configuration);
        this.d.a(getResources().getConfiguration());
    }

    @CallSuper
    public void onCreate(Bundle bundle) {
        CarInfo carInfo;
        super.onCreate(bundle);
        this.e = new ad(getBaseContext());
        this.f = new SupportLibViewLoader();
        try {
            carInfo = ((CarInfoManager) a(Car.INFO_SERVICE)).loadCarInfo();
        } catch (CarNotConnectedException | CarNotSupportedException e2) {
            Log.w("CSL.CarActivity", "Unable to get car info", e2);
            carInfo = null;
        }
        this.d = new CarUiController(this.e, a(), this.f, carInfo);
        super.setContentView(this.d.d());
        this.g = (ViewGroup) findViewById(this.d.c());
    }

    @Nullable
    @CallSuper
    public View onCreateView(String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View onCreateView = this.f.onCreateView(str, context, attributeSet);
        return onCreateView != null ? onCreateView : super.onCreateView(str, context, attributeSet);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("android:viewHierarchyState");
        if (bundle2 != null) {
            bundle2.setClassLoader(this.e.a().getClassLoader());
        }
        super.onRestoreInstanceState(bundle);
        this.d.a(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.d.b(bundle);
    }

    @CallSuper
    public void onStart() {
        super.onStart();
        this.d.a();
    }

    @CallSuper
    public void onStop() {
        super.onStop();
        this.d.b();
    }

    @CallSuper
    public void setContentView(@LayoutRes int i) {
        this.g.removeAllViews();
        getLayoutInflater().inflate(i, this.g, true);
    }

    @CallSuper
    public void setContentView(View view) {
        this.g.removeAllViews();
        this.g.addView(view);
    }

    public void setIgnoreConfigChanges(int i) {
        super.setIgnoreConfigChanges(i);
    }

    @CallSuper
    public void setIntent(Intent intent) {
        super.setIntent(intent);
    }

    @CallSuper
    public void startCarActivity(Intent intent) {
        intent.putExtra("android.intent.extra.PACKAGE_NAME", getBaseContext().getPackageName());
        this.d.a(intent);
    }
}
