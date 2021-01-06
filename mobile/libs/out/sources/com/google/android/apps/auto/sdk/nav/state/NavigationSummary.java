package com.google.android.apps.auto.sdk.nav.state;

import android.os.Bundle;
import android.os.Parcelable.Creator;
import com.google.android.apps.auto.sdk.a;
import com.google.android.apps.auto.sdk.b;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NavigationSummary extends a {
    public static final Creator<NavigationSummary> CREATOR = new b(NavigationSummary.class);
    /* access modifiers changed from: private */
    public int a;
    /* access modifiers changed from: private */
    public CharSequence b = "";
    /* access modifiers changed from: private */
    public int c = -1;

    public static class Builder {
        private NavigationSummary a = new NavigationSummary();

        public NavigationSummary build() {
            return this.a;
        }

        public Builder setFormattedDestinationEta(CharSequence charSequence) {
            this.a.b = charSequence;
            return this;
        }

        public Builder setNavigationStatus(int i) throws IllegalArgumentException {
            if (i == 2 || i == 1 || i == 4 || i == 3) {
                this.a.a = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid navigation status value");
        }

        public Builder setSecondsToDestination(int i) {
            this.a.c = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NavigationStatus {
        public static final int NAVIGATING = 1;
        public static final int PAUSING = 4;
        public static final int RESUMING = 3;
        public static final int STOPPED = 2;
        public static final int UNKNOWN = 0;
    }

    public CharSequence getFormattedDestinationEta() {
        return this.b;
    }

    public int getNavigationStatus() {
        return this.a;
    }

    public int getSecondsToDestination() {
        return this.c;
    }

    public boolean hasSecondsToDestination() {
        return this.c != -1;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        this.b = bundle.getCharSequence("formatted_eta", "");
        this.c = bundle.getInt("sec_to_dest", -1);
        this.a = bundle.getInt("nav_status");
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        bundle.putCharSequence("formatted_eta", this.b);
        bundle.putInt("sec_to_dest", this.c);
        bundle.putInt("nav_status", this.a);
    }
}
