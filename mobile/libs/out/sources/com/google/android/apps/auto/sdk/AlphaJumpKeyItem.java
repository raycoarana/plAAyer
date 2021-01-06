package com.google.android.apps.auto.sdk;

import android.os.Bundle;
import android.os.Parcelable.Creator;

public class AlphaJumpKeyItem extends a {
    public static final Creator<AlphaJumpKeyItem> CREATOR = new b(AlphaJumpKeyItem.class);
    /* access modifiers changed from: private */
    public char a;
    /* access modifiers changed from: private */
    public boolean b;
    /* access modifiers changed from: private */
    public int c = -1;

    public static class Builder {
        private AlphaJumpKeyItem a = new AlphaJumpKeyItem();

        public AlphaJumpKeyItem build() {
            if (this.a.a == 0) {
                throw new IllegalArgumentException("The character must be non-null.");
            } else if (!AlphaJumpMenuAdapter.SUPPORTED_CHARACTER_SET.contains(Character.valueOf(this.a.a))) {
                throw new IllegalArgumentException("The character is not supported.");
            } else if (!this.a.b || this.a.c != -1) {
                return this.a;
            } else {
                throw new IllegalArgumentException("The character is enabled but there is not a jump position.");
            }
        }

        public Builder setCharacter(char c) {
            this.a.a = c;
            return this;
        }

        public Builder setEnabled(boolean z) {
            this.a.b = z;
            return this;
        }

        public Builder setJumpPosition(int i) {
            this.a.c = i;
            return this;
        }
    }

    AlphaJumpKeyItem() {
    }

    public char getCharacter() {
        return this.a;
    }

    public int getJumpPosition() {
        return this.c;
    }

    public boolean isEnabled() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void readFromBundle(Bundle bundle) {
        this.a = bundle.getChar("character");
        this.b = bundle.getBoolean("is_enabled");
        this.c = bundle.getInt("jump_position");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[AlphaJumpKeyItem , character ").append(this.a).append(", isEnabled ").append(this.b).append(", jumpPosition ").append(this.c).append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void writeToBundle(Bundle bundle) {
        bundle.putChar("character", this.a);
        bundle.putBoolean("is_enabled", this.b);
        bundle.putInt("jump_position", this.c);
    }
}
