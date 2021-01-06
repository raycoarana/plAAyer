package com.google.android.apps.auto.sdk;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.apps.auto.sdk.a;
import java.lang.reflect.Array;

public final class b<T extends a> implements Creator<T> {
    private Class<T> a;

    public b(Class<T> cls) {
        this.a = cls;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public T createFromParcel(Parcel parcel) {
        Exception e;
        T t = null;
        try {
            T t2 = (a) this.a.newInstance();
            try {
                t2.readFromBundle(parcel.readBundle(this.a.getClassLoader()));
                return t2;
            } catch (Exception e2) {
                e = e2;
                t = t2;
            }
        } catch (Exception e3) {
            e = e3;
            String valueOf = String.valueOf(this.a.getSimpleName());
            Log.e("CSL.AbstractBundleable", valueOf.length() != 0 ? "Failed to instantiate ".concat(valueOf) : new String("Failed to instantiate "), e);
            return t;
        }
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return (a[]) Array.newInstance(this.a, i);
    }
}
