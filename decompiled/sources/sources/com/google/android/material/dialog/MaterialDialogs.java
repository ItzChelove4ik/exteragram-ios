package com.google.android.material.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes5.dex */
public class MaterialDialogs {
    private MaterialDialogs() {
    }

    public static Rect getDialogBackgroundInsets(Context r6, int r7, int r8) {
        TypedArray r62 = ThemeEnforcement.obtainStyledAttributes(r6, null, R.styleable.MaterialAlertDialog, r7, r8, new int[0]);
        int r72 = r62.getDimensionPixelSize(R.styleable.MaterialAlertDialog_backgroundInsetStart, r6.getResources().getDimensionPixelSize(R.dimen.mtrl_alert_dialog_background_inset_start));
        int r82 = r62.getDimensionPixelSize(R.styleable.MaterialAlertDialog_backgroundInsetTop, r6.getResources().getDimensionPixelSize(R.dimen.mtrl_alert_dialog_background_inset_top));
        int r1 = r62.getDimensionPixelSize(R.styleable.MaterialAlertDialog_backgroundInsetEnd, r6.getResources().getDimensionPixelSize(R.dimen.mtrl_alert_dialog_background_inset_end));
        int r2 = r62.getDimensionPixelSize(R.styleable.MaterialAlertDialog_backgroundInsetBottom, r6.getResources().getDimensionPixelSize(R.dimen.mtrl_alert_dialog_background_inset_bottom));
        r62.recycle();
        int r63 = r6.getResources().getConfiguration().getLayoutDirection();
        if (r63 != 1) goto L5;
        int r3 = r1;
    L6:
        if (r63 == 1) goto L10;
        r72 = r1;
    L10:
        return new Rect(r3, r82, r72, r2);
    L5:
        r3 = r72;
        goto L6
    }

    public static InsetDrawable insetDrawable(Drawable r6, Rect r7) {
        return new InsetDrawable(r6, r7.left, r7.top, r7.right, r7.bottom);
    }
}
