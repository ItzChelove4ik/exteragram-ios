package com.google.android.material.datepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.material.R;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;

/* loaded from: classes5.dex */
public class MaterialStyledDatePickerDialog extends DatePickerDialog {
    private static final int DEF_STYLE_ATTR = 16843612;
    private static final int DEF_STYLE_RES = 0;
    private final Drawable background;
    private final Rect backgroundInsets;

    static {
        DEF_STYLE_RES = R.style.MaterialAlertDialog_MaterialComponents_Picker_Date_Spinner;
    }

    public MaterialStyledDatePickerDialog(Context r2) {
        this(r2, 0);
    }

    public MaterialStyledDatePickerDialog(Context r8, int r9) {
        this(r8, r9, null, -1, -1, -1);
    }

    public MaterialStyledDatePickerDialog(Context r1, int r2, DatePickerDialog.OnDateSetListener r3, int r4, int r5, int r6) {
        super(r1, r2, r3, r4, r5, r6);
        Context r12 = getContext();
        int r22 = MaterialAttributes.resolveOrThrow(getContext(), R.attr.colorSurface, getClass().getCanonicalName());
        int r42 = DEF_STYLE_RES;
        MaterialShapeDrawable r32 = new MaterialShapeDrawable(r12, null, 16843612, r42);
        r32.setFillColor(ColorStateList.valueOf(r22));
        Rect r13 = MaterialDialogs.getDialogBackgroundInsets(r12, 16843612, r42);
        this.backgroundInsets = r13;
        this.background = MaterialDialogs.insetDrawable(r32, r13);
    }

    public MaterialStyledDatePickerDialog(Context r8, DatePickerDialog.OnDateSetListener r9, int r10, int r11, int r12) {
        this(r8, 0, r9, r10, r11, r12);
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public void onCreate(Bundle r3) {
        super.onCreate(r3);
        getWindow().setBackgroundDrawable(this.background);
        getWindow().getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(this, this.backgroundInsets));
    }
}
