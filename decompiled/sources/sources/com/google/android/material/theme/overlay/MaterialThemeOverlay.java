package com.google.android.material.theme.overlay;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.R$attr;
import androidx.appcompat.view.ContextThemeWrapper;

/* loaded from: classes5.dex */
public class MaterialThemeOverlay {
    private static final int[] ANDROID_THEME_OVERLAY_ATTRS = null;
    private static final int[] MATERIAL_THEME_OVERLAY_ATTR = null;

    static {
        ANDROID_THEME_OVERLAY_ATTRS = new int[]{R.attr.theme, R$attr.theme};
        MATERIAL_THEME_OVERLAY_ATTR = new int[]{com.google.android.material.R.attr.materialThemeOverlay};
    }

    private MaterialThemeOverlay() {
    }

    private static int obtainAndroidThemeOverlayId(Context r2, AttributeSet r3) {
        TypedArray r22 = r2.obtainStyledAttributes(r3, ANDROID_THEME_OVERLAY_ATTRS);
        int r0 = r22.getResourceId(0, 0);
        int r32 = r22.getResourceId(1, 0);
        r22.recycle();
        if (r0 == 0) goto L5;
        return r0;
    L5:
        return r32;
    }

    private static int[] obtainMaterialOverlayIds(Context r2, AttributeSet r3, int[] r4, int r5, int r6) {
        int[] r0 = new int[r4.length];
        if (r4.length <= 0) goto L9;
        TypedArray r22 = r2.obtainStyledAttributes(r3, r4, r5, r6);
        int r52 = 0;
    L6:
        if (r52 >= r4.length) goto L8;
        r0[r52] = r22.getResourceId(r52, 0);
        r52 = r52 + 1;
        goto L6
    L8:
        r22.recycle();
    L9:
        return r0;
    }

    private static int obtainMaterialThemeOverlayId(Context r1, AttributeSet r2, int r3, int r4) {
        return obtainMaterialOverlayIds(r1, r2, MATERIAL_THEME_OVERLAY_ATTR, r3, r4)[0];
    }

    public static Context wrap(Context r1, AttributeSet r2, int r3, int r4) {
        return wrap(r1, r2, r3, r4, new int[0]);
    }

    public static Context wrap(Context r4, AttributeSet r5, int r6, int r7, int[] r8) {
        int r0 = obtainMaterialThemeOverlayId(r4, r5, r6, r7);
        int r2 = 0;
        if ((r4 instanceof ContextThemeWrapper) == true) goto L5;
    L7:
        boolean r1 = false;
    L8:
        if (r0 == 0) goto L21;
        if (r1 == true) goto L21;
        ContextThemeWrapper r12 = new ContextThemeWrapper(r4, r0);
        int[] r62 = obtainMaterialOverlayIds(r4, r5, r8, r6, r7);
        int r72 = r62.length;
    L12:
        if (r2 >= r72) goto L17;
        int r82 = r62[r2];
        if (r82 == 0) goto L16;
        r12.getTheme().applyStyle(r82, true);
    L16:
        r2 = r2 + 1;
        goto L12
    L17:
        int r42 = obtainAndroidThemeOverlayId(r4, r5);
        if (r42 == 0) goto L20;
        r12.getTheme().applyStyle(r42, true);
    L20:
        return r12;
    L21:
        return r4;
    L5:
        if (((ContextThemeWrapper) r4).getThemeResId() != r0) goto L7;
        r1 = true;
        goto L8
    }
}
