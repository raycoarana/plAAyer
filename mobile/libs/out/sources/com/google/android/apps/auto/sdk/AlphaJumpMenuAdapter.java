package com.google.android.apps.auto.sdk;

import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AlphaJumpMenuAdapter extends MenuAdapter {
    protected static final List<Character> SUPPORTED_CHARACTER_LIST = Collections.unmodifiableList(Arrays.asList(new Character[]{Character.valueOf('0'), Character.valueOf('A'), Character.valueOf('B'), Character.valueOf('C'), Character.valueOf('D'), Character.valueOf('E'), Character.valueOf('F'), Character.valueOf('G'), Character.valueOf('H'), Character.valueOf('I'), Character.valueOf('J'), Character.valueOf('K'), Character.valueOf('L'), Character.valueOf('M'), Character.valueOf('N'), Character.valueOf('O'), Character.valueOf('P'), Character.valueOf('Q'), Character.valueOf('R'), Character.valueOf('S'), Character.valueOf('T'), Character.valueOf('U'), Character.valueOf('V'), Character.valueOf('W'), Character.valueOf('X'), Character.valueOf('Y'), Character.valueOf('Z')}));
    protected static final Set<Character> SUPPORTED_CHARACTER_SET = Collections.unmodifiableSet(new HashSet(SUPPORTED_CHARACTER_LIST));
    private boolean b;
    private boolean c;
    @VisibleForTesting
    protected final List<AlphaJumpKeyItem> mAlphaJumpKeyItemList = new ArrayList();

    public AlphaJumpMenuAdapter() {
        new ArrayList();
    }

    private final void b() {
        boolean z;
        boolean z2;
        boolean z3;
        if (this.a != null) {
            boolean z4 = getMenuItemCount() >= 12;
            if (this.mConfig != null) {
                z = this.mConfig.getBoolean("touch_enabled", false);
                boolean z5 = getMenuItemCount() >= this.mConfig.getInt("minimum_menu_items_for_alpha_jump", 12);
                z3 = this.mConfig.getBoolean("alpha_jump_language_supported", false);
                z2 = z5;
            } else {
                if (Log.isLoggable("CSL.AlphaJumpMenuAdapte", 3)) {
                    Log.d("CSL.AlphaJumpMenuAdapte", "config was null");
                }
                z = false;
                z2 = z4;
                z3 = false;
            }
            boolean z6 = !this.c && z && z2 && z3;
            if (Log.isLoggable("CSL.AlphaJumpMenuAdapte", 3)) {
                Log.d("CSL.AlphaJumpMenuAdapte", String.format("touchEnabled %s, isLongList %s, isLanguageSupported %s, showAlphaJump %s", new Object[]{Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3), Boolean.valueOf(z6)}));
            }
            if (z6) {
                this.b = true;
                this.a.enableAlphaJump();
            }
        }
    }

    public void hideLoadingIndicator() {
        super.hideLoadingIndicator();
        this.c = false;
        b();
    }

    public void notifyDataSetChanged() {
        if (this.a == null) {
            Log.w("CSL.AlphaJumpMenuAdapte", "Cannot notify dataset changed because this AlphaJumpMenuAdapter is not connected to a root menu");
            return;
        }
        if (this.b) {
            this.a.disableAlphaJump();
        }
        super.notifyDataSetChanged();
        b();
    }

    public void onAttach(MenuAdapterCallback menuAdapterCallback) {
        super.onAttach(menuAdapterCallback);
        b();
    }

    public void onDetach() {
        this.b = false;
        this.c = false;
        this.a.disableAlphaJump();
        super.onDetach();
    }

    public void showLoadingIndicator() {
        super.showLoadingIndicator();
        this.c = true;
    }
}
