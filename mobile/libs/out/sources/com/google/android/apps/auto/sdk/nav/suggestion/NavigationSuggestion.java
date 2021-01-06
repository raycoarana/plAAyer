package com.google.android.apps.auto.sdk.nav.suggestion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.apps.auto.sdk.a;
import com.google.android.apps.auto.sdk.b;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NavigationSuggestion extends a {
    public static final Creator<NavigationSuggestion> CREATOR = new b(NavigationSuggestion.class);
    /* access modifiers changed from: private */
    @NonNull
    public Intent a;
    /* access modifiers changed from: private */
    public CharSequence b;
    /* access modifiers changed from: private */
    public CharSequence c;
    /* access modifiers changed from: private */
    public CharSequence d;
    /* access modifiers changed from: private */
    public CharSequence e;
    /* access modifiers changed from: private */
    public int f;
    /* access modifiers changed from: private */
    public double g = Double.MAX_VALUE;
    /* access modifiers changed from: private */
    public double h = Double.MAX_VALUE;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public LatLng[] j;

    public static class Builder {
        private NavigationSuggestion a = new NavigationSuggestion();

        public NavigationSuggestion build() {
            if (this.a.a == null) {
                throw new IllegalArgumentException("Failed to provide navigation intent");
            } else if (this.a.d != null || this.a.b != null) {
                return this.a;
            } else {
                throw new IllegalArgumentException("Failed to provide address and destination name. Must provide either  address or name.");
            }
        }

        public Builder setAddress(CharSequence charSequence) {
            this.a.d = charSequence;
            return this;
        }

        public Builder setFormattedTimeToDestination(CharSequence charSequence) {
            this.a.e = charSequence;
            return this;
        }

        public Builder setLatLng(double d, double d2) throws IllegalArgumentException {
            if (d > 90.0d || d < -90.0d) {
                throw new IllegalArgumentException("Invalid latitude value: " + d);
            } else if (d2 > 180.0d || d2 < -180.0d) {
                throw new IllegalArgumentException("Invalid longitude value: " + d2);
            } else {
                this.a.g = d;
                this.a.h = d2;
                return this;
            }
        }

        public Builder setName(CharSequence charSequence) {
            this.a.b = charSequence;
            return this;
        }

        public Builder setNavigationIntent(Intent intent) {
            this.a.a = intent;
            return this;
        }

        public Builder setRoute(CharSequence charSequence) {
            this.a.c = charSequence;
            return this;
        }

        public Builder setSecondsToDestination(int i) {
            this.a.f = i;
            return this;
        }

        public Builder setTraffic(int i) throws IllegalArgumentException {
            if (i == 1 || i == 2 || i == 3 || i == 0) {
                this.a.i = i;
                return this;
            }
            throw new IllegalArgumentException("Traffic must be one of [Traffic.LIGHT, Traffic.MEDIUM, Traffic.HEAVY, Traffic.UNKNOWN]");
        }

        public Builder setWaypoints(LatLng[] latLngArr) {
            this.a.j = latLngArr;
            return this;
        }
    }

    public static class LatLng {
        private double a;
        private double b;

        public LatLng(double d, double d2) {
            this.a = d;
            this.b = d2;
        }

        public double getLat() {
            return this.a;
        }

        public double getLng() {
            return this.b;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Traffic {
        public static final int HEAVY = 3;
        public static final int LIGHT = 1;
        public static final int MEDIUM = 2;
        public static final int UNKNOWN = 0;
    }

    @Nullable
    public CharSequence getAddress() {
        return this.d;
    }

    @Nullable
    public CharSequence getFormattedTimeToDestination() {
        return this.e;
    }

    @NonNull
    public LatLng getLatLng() {
        return new LatLng(this.g, this.h);
    }

    @Nullable
    public CharSequence getName() {
        return this.b;
    }

    @NonNull
    public Intent getNavigationIntent() {
        return this.a;
    }

    @Nullable
    public CharSequence getRoute() {
        return this.c;
    }

    public int getSecondsToDestination() {
        return this.f;
    }

    public int getTraffic() {
        return this.i;
    }

    public LatLng[] getWaypoints() {
        return this.j;
    }

    public boolean hasLatLng() {
        return (this.g == Double.MAX_VALUE || this.h == Double.MAX_VALUE) ? false : true;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        LatLng[] latLngArr;
        this.b = bundle.getCharSequence("name");
        this.c = bundle.getCharSequence("route");
        this.a = (Intent) bundle.getParcelable("intent");
        this.d = bundle.getCharSequence("address");
        this.g = bundle.getDouble("lat");
        this.h = bundle.getDouble("lng");
        this.f = bundle.getInt("sec_to_dest");
        this.e = bundle.getCharSequence("formatted_tta");
        double[] doubleArray = bundle.getDoubleArray("waypoints");
        if (doubleArray == null) {
            latLngArr = null;
        } else {
            LatLng[] latLngArr2 = new LatLng[(doubleArray.length / 2)];
            for (int i2 = 0; i2 < doubleArray.length / 2; i2++) {
                latLngArr2[i2] = new LatLng(doubleArray[i2 * 2], doubleArray[(i2 * 2) + 1]);
            }
            latLngArr = latLngArr2;
        }
        this.j = latLngArr;
        this.i = bundle.getInt("traffic");
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        double[] dArr;
        bundle.putCharSequence("name", this.b);
        bundle.putCharSequence("route", this.c);
        bundle.putParcelable("intent", this.a);
        bundle.putCharSequence("address", this.d);
        bundle.putDouble("lat", this.g);
        bundle.putDouble("lng", this.h);
        bundle.putCharSequence("formatted_tta", this.e);
        bundle.putInt("sec_to_dest", this.f);
        LatLng[] latLngArr = this.j;
        if (latLngArr == null) {
            dArr = null;
        } else {
            double[] dArr2 = new double[(latLngArr.length << 1)];
            for (int i2 = 0; i2 < latLngArr.length; i2++) {
                dArr2[i2 * 2] = latLngArr[i2].getLat();
                dArr2[(i2 * 2) + 1] = latLngArr[i2].getLng();
            }
            dArr = dArr2;
        }
        bundle.putDoubleArray("waypoints", dArr);
        bundle.putInt("traffic", this.i);
    }
}
