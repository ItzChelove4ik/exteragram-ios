package com.google.android.material.textfield;

import android.widget.EditText;

/* loaded from: classes5.dex */
class EditTextUtils {
    private EditTextUtils() {
    }

    public static boolean isEditable(EditText r0) {
        if (r0.getInputType() == 0) goto L6;
        return true;
    L6:
        return false;
    }
}
