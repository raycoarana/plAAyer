package com.google.android.apps.auto.sdk;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.LayoutInflater.Factory;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.google.android.gms.car.CarInfoManager.CarInfo;
import com.google.android.gms.car.input.InputManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CarUiController extends ac {
    private StatusBarController b;
    private DrawerController c;
    private MenuController d;
    private SearchController e;
    @IdRes
    private int f = ((Integer) a(this.m, new Object[0])).intValue();
    private Method g;
    private Method h;
    private Method i;
    private Method j;
    private Method k;
    private Method l;
    private Method m;
    private Method n;
    private Method o;
    private Method p;
    private Method q;
    private Method r;
    private Method s;
    private Method t;
    private Method u;

    public CarUiController(ad adVar, InputManager inputManager, Factory factory, CarInfo carInfo) {
        t uVar;
        m nVar;
        e fVar;
        i jVar;
        q qVar = null;
        super(adVar, "com.google.android.gearhead.appdecor.CarUiEntry", adVar.b(), adVar.a(), factory);
        IBinder iBinder = (IBinder) a(this.p, new Object[0]);
        if (iBinder == null) {
            uVar = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.apps.auto.sdk.IStatusBarController");
            uVar = queryLocalInterface instanceof t ? (t) queryLocalInterface : new u(iBinder);
        }
        this.b = new StatusBarController(uVar, carInfo);
        IBinder iBinder2 = (IBinder) a(this.r, new Object[0]);
        if (iBinder2 == null) {
            nVar = null;
        } else {
            IInterface queryLocalInterface2 = iBinder2.queryLocalInterface("com.google.android.apps.auto.sdk.IMenuController");
            nVar = queryLocalInterface2 instanceof m ? (m) queryLocalInterface2 : new n(iBinder2);
        }
        this.d = new MenuController(adVar.b(), nVar);
        IBinder iBinder3 = (IBinder) a(this.q, new Object[0]);
        if (iBinder3 == null) {
            fVar = null;
        } else {
            IInterface queryLocalInterface3 = iBinder3.queryLocalInterface("com.google.android.apps.auto.sdk.IDrawerController");
            fVar = queryLocalInterface3 instanceof e ? (e) queryLocalInterface3 : new f(iBinder3);
        }
        this.c = new DrawerController(fVar, this.d);
        IBinder iBinder4 = (IBinder) a(this.s, new Object[0]);
        if (iBinder4 == null) {
            jVar = null;
        } else {
            IInterface queryLocalInterface4 = iBinder4.queryLocalInterface("com.google.android.apps.auto.sdk.IImeController");
            jVar = queryLocalInterface4 instanceof i ? (i) queryLocalInterface4 : new j(iBinder4);
        }
        new v(jVar, inputManager, this);
        IBinder iBinder5 = (IBinder) a(this.t, new Object[0]);
        if (iBinder5 != null) {
            IInterface queryLocalInterface5 = iBinder5.queryLocalInterface("com.google.android.apps.auto.sdk.ISearchController");
            qVar = queryLocalInterface5 instanceof q ? (q) queryLocalInterface5 : new s(iBinder5);
        }
        this.e = new SearchController(qVar);
    }

    /* access modifiers changed from: 0000 */
    public final InputConnection a(EditorInfo editorInfo) {
        return (InputConnection) a(this.u, editorInfo);
    }

    public final void a() {
        a(this.g, new Object[0]);
    }

    public final void a(Intent intent) {
        a(this.o, intent);
    }

    public final void a(Configuration configuration) {
        a(this.l, configuration);
    }

    public final void a(Bundle bundle) {
        a(this.j, bundle);
        this.d.a(bundle);
    }

    /* access modifiers changed from: 0000 */
    public final void a(IBinder iBinder) {
        a(this.i, iBinder);
    }

    /* access modifiers changed from: protected */
    public final void a(Method[] methodArr) {
        Log.d("CSL.CarUiController", String.format("Initializing %s", new Object[]{this.a}));
        for (Method method : methodArr) {
            if (Modifier.isPublic(method.getModifiers())) {
                String name = method.getName();
                char c2 = 65535;
                switch (name.hashCode()) {
                    case -2094893759:
                        if (name.equals("startCarActivity")) {
                            c2 = 7;
                            break;
                        }
                        break;
                    case -1491459488:
                        if (name.equals("onSaveInstanceState")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case -1336895037:
                        if (name.equals("onStart")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -1186339443:
                        if (name.equals("onRestoreInstanceState")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case -1012956543:
                        if (name.equals("onStop")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case -369900066:
                        if (name.equals("requestXRayScan")) {
                            c2 = 14;
                            break;
                        }
                        break;
                    case 727922513:
                        if (name.equals("getMenuController")) {
                            c2 = 10;
                            break;
                        }
                        break;
                    case 808969955:
                        if (name.equals("getDrawerController")) {
                            c2 = 9;
                            break;
                        }
                        break;
                    case 852203143:
                        if (name.equals("getImeController")) {
                            c2 = 11;
                            break;
                        }
                        break;
                    case 983415097:
                        if (name.equals("getContentContainerId")) {
                            c2 = 5;
                            break;
                        }
                        break;
                    case 1272509941:
                        if (name.equals("getAppLayout")) {
                            c2 = 6;
                            break;
                        }
                        break;
                    case 1321081562:
                        if (name.equals("getSearchController")) {
                            c2 = 12;
                            break;
                        }
                        break;
                    case 1356972381:
                        if (name.equals("onConfigurationChanged")) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case 1861367303:
                        if (name.equals("getStatusBarController")) {
                            c2 = 8;
                            break;
                        }
                        break;
                    case 2060811692:
                        if (name.equals("createInputConnection")) {
                            c2 = 13;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        this.g = method;
                        break;
                    case 1:
                        this.h = method;
                        break;
                    case 2:
                        this.j = method;
                        break;
                    case 3:
                        this.k = method;
                        break;
                    case 4:
                        this.l = method;
                        break;
                    case 5:
                        this.m = method;
                        break;
                    case 6:
                        this.n = method;
                        break;
                    case 7:
                        this.o = method;
                        break;
                    case 8:
                        this.p = method;
                        break;
                    case 9:
                        this.q = method;
                        break;
                    case 10:
                        this.r = method;
                        break;
                    case 11:
                        this.s = method;
                        break;
                    case 12:
                        this.t = method;
                        break;
                    case 13:
                        this.u = method;
                        break;
                    case 14:
                        this.i = method;
                        break;
                    default:
                        Log.w("CSL.CarUiController", String.format("Unmapped public method %s", new Object[]{method.getName()}));
                        Log.d("CSL.CarUiController", String.format("Annotations for %s", new Object[]{method.getName()}));
                        for (Annotation annotation : method.getAnnotations()) {
                            Log.d("CSL.CarUiController", annotation.toString());
                        }
                        break;
                }
            }
        }
    }

    public final void b() {
        a(this.h, new Object[0]);
    }

    public final void b(Bundle bundle) {
        int i2 = 0;
        a(this.k, bundle);
        MenuController menuController = this.d;
        int[] iArr = new int[menuController.d.size()];
        while (true) {
            int i3 = i2;
            if (i3 < menuController.d.size()) {
                iArr[i3] = ((Integer) menuController.d.get(i3)).intValue();
                i2 = i3 + 1;
            } else {
                bundle.putIntArray("com.google.android.apps.auto.sdk.MenuController.KEY_SUBMENU_PATH", iArr);
                return;
            }
        }
    }

    @IdRes
    public final int c() {
        return this.f;
    }

    public final View d() {
        return (View) a(this.n, new Object[0]);
    }

    public DrawerController getDrawerController() {
        return this.c;
    }

    public MenuController getMenuController() {
        return this.d;
    }

    public SearchController getSearchController() {
        return this.e;
    }

    public StatusBarController getStatusBarController() {
        return this.b;
    }
}
