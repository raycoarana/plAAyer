package com.google.android.apps.auto.sdk.nav.state;

import android.os.Bundle;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.apps.auto.sdk.a;
import com.google.android.apps.auto.sdk.b;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TurnEvent extends a {
    public static final Creator<TurnEvent> CREATOR = new b(TurnEvent.class);
    /* access modifiers changed from: private */
    public int a;
    /* access modifiers changed from: private */
    public CharSequence b = "";
    /* access modifiers changed from: private */
    public int c;
    /* access modifiers changed from: private */
    public int d;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    @Nullable
    public byte[] f;
    /* access modifiers changed from: private */
    public int g = -1;
    /* access modifiers changed from: private */
    public int h = -1;
    /* access modifiers changed from: private */
    public int i = -1;
    /* access modifiers changed from: private */
    public int j;

    public static class Builder {
        private TurnEvent a;

        public Builder() {
            this.a = new TurnEvent();
        }

        public Builder(TurnEvent turnEvent) {
            this.a = turnEvent;
        }

        public TurnEvent build() {
            return this.a;
        }

        public Builder setRoundaboutTurnEvent(int i, int i2) throws IllegalArgumentException {
            if (i > 360 || i <= 0) {
                throw new IllegalArgumentException("Turn angle must be in [1, 360]");
            } else if (i2 <= 0) {
                throw new IllegalArgumentException("Turn number must be > 0");
            } else {
                this.a.a = 13;
                this.a.d = i;
                this.a.e = i2;
                return this;
            }
        }

        public Builder setSecondsUntilTurnEvent(int i) throws IllegalArgumentException {
            if (i < 0) {
                throw new IllegalArgumentException("turnEtaSeconds must be >= 0");
            }
            this.a.h = i;
            return this;
        }

        public Builder setTurnDisplayDistanceE3(int i) throws IllegalArgumentException {
            if (i < 0) {
                throw new IllegalArgumentException("displayDistanceE3 must be >= 0");
            }
            this.a.i = i;
            return this;
        }

        public Builder setTurnDistanceMeters(int i) throws IllegalArgumentException {
            if (i < 0) {
                throw new IllegalArgumentException("distanceMeters must be >= 0");
            }
            this.a.g = i;
            return this;
        }

        public Builder setTurnDistanceUnit(int i) {
            this.a.j = i;
            return this;
        }

        public Builder setTurnEventRoadName(CharSequence charSequence) {
            if (charSequence == null) {
                throw new IllegalArgumentException("Road name must not be null");
            }
            this.a.b = charSequence;
            return this;
        }

        public Builder setTurnEventType(int i) throws IllegalArgumentException {
            if (i <= 0 || i > 19) {
                throw new IllegalArgumentException("turnEvent is invalid: " + i);
            }
            this.a.a = i;
            this.a.d = -1;
            this.a.e = -1;
            return this;
        }

        public Builder setTurnImage(byte[] bArr) {
            this.a.f = bArr;
            return this;
        }

        public Builder setTurnSide(int i) throws IllegalArgumentException {
            if (i < 0 || i > 2) {
                throw new IllegalArgumentException("turnSide must be one of TurnSide.LEFT, TurnSide.RIGHT or TurnSide.UNKNOWN");
            }
            this.a.c = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DistanceUnit {
        public static final int FEET = 6;
        public static final int KILOMETERS = 2;
        public static final int KILOMETERS_P1 = 3;
        public static final int METERS = 1;
        public static final int MILES = 4;
        public static final int MILES_P1 = 5;
        public static final int UNKNOWN = 0;
        public static final int YARDS = 7;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TurnEventType {
        public static final int DEPART = 1;
        public static final int DESTINATION = 19;
        public static final int FERRY_BOAT = 16;
        public static final int FERRY_TRAIN = 17;
        public static final int FORK = 9;
        public static final int MERGE = 10;
        public static final int NAME_CHANGE = 2;
        public static final int OFF_RAMP = 8;
        public static final int ON_RAMP = 7;
        public static final int ROUNDABOUT_ENTER = 11;
        public static final int ROUNDABOUT_ENTER_AND_EXIT = 13;
        public static final int ROUNDABOUT_EXIT = 12;
        public static final int SHARP_TURN = 5;
        public static final int SLIGHT_TURN = 3;
        public static final int STRAIGHT = 14;
        public static final int TURN = 4;
        public static final int UNKNOWN = 0;
        public static final int U_TURN = 6;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TurnSide {
        public static final int LEFT = 1;
        public static final int RIGHT = 2;
        public static final int UNKNOWN = 0;
    }

    public int getSecondsUntilTurnEvent() {
        return this.h;
    }

    public int getTurnAngle() {
        return this.d;
    }

    public int getTurnDisplayDistanceE3() {
        return this.i;
    }

    public int getTurnDistanceMeters() {
        return this.g;
    }

    public int getTurnDistanceUnit() {
        return this.j;
    }

    public CharSequence getTurnEventRoadName() {
        return this.b;
    }

    public int getTurnEventType() {
        return this.a;
    }

    @Nullable
    public byte[] getTurnImage() {
        return this.f;
    }

    public int getTurnNumber() {
        return this.e;
    }

    public int getTurnSide() {
        return this.c;
    }

    public boolean hasSecondsUntilTurnEvent() {
        return this.h != -1;
    }

    public boolean hasTurnAngle() {
        return this.d > 0;
    }

    public boolean hasTurnDisplayDistanceE3() {
        return this.i != -1;
    }

    public boolean hasTurnDistanceMeters() {
        return this.g != -1;
    }

    public boolean hasTurnNumber() {
        return this.e > 0;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        this.a = bundle.getInt("turn_event");
        this.b = bundle.getCharSequence("turn_event_road", "");
        this.c = bundle.getInt("turn_event_side");
        this.d = bundle.getInt("turn_angle");
        this.e = bundle.getInt("turn_number");
        this.f = bundle.getByteArray("turn_image");
        this.g = bundle.getInt("turn_distance", -1);
        this.h = bundle.getInt("sec_to_turn", -1);
        this.j = bundle.getInt("turn_unit");
        this.i = bundle.getInt("turn_distance_e3", -1);
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        bundle.putInt("turn_event", this.a);
        bundle.putCharSequence("turn_event_road", this.b);
        bundle.putInt("turn_event_side", this.c);
        bundle.putInt("turn_angle", this.d);
        bundle.putInt("turn_number", this.e);
        bundle.putByteArray("turn_image", this.f);
        bundle.putInt("turn_distance", this.g);
        bundle.putInt("sec_to_turn", this.h);
        bundle.putInt("turn_unit", this.j);
        bundle.putInt("turn_distance_e3", this.i);
    }
}
