package com.google.android.apps.auto.sdk;

import android.support.annotation.VisibleForTesting;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.google.android.gms.car.input.CarEditable;
import com.google.android.gms.car.input.CarEditableListener;

@VisibleForTesting
final class x implements CarEditable {
    private final CarUiController a;

    public x(CarUiController carUiController) {
        this.a = carUiController;
    }

    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return this.a.a(editorInfo);
    }

    public final void setCarEditableListener(CarEditableListener carEditableListener) {
    }

    public final void setInputEnabled(boolean z) {
    }
}
