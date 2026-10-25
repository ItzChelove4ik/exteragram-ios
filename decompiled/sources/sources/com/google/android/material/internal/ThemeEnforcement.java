package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.TintTypedArray;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialAttributes;
import com.sun.jna.Native$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class ThemeEnforcement {
    private static final int[] APPCOMPAT_CHECK_ATTRS = null;
    private static final String APPCOMPAT_THEME_NAME = "Theme.AppCompat";
    private static final int[] MATERIAL_CHECK_ATTRS = null;
    private static final String MATERIAL_THEME_NAME = "Theme.MaterialComponents";

    static {
        APPCOMPAT_CHECK_ATTRS = new int[]{R$attr.colorPrimary};
        MATERIAL_CHECK_ATTRS = new int[]{R.attr.colorPrimaryVariant};
    }

    private ThemeEnforcement() {
    }

    public static void checkAppCompatTheme(Context r2) {
        checkTheme(r2, APPCOMPAT_CHECK_ATTRS, APPCOMPAT_THEME_NAME);
    }

    private static void checkCompatibleTheme(Context r1, AttributeSet r2, int r3, int r4) {
        TypedArray r22 = r1.obtainStyledAttributes(r2, R.styleable.ThemeEnforcement, r3, r4);
        boolean r32 = r22.getBoolean(R.styleable.ThemeEnforcement_enforceMaterialTheme, false);
        r22.recycle();
        if (r32 == false) goto L11;
        TypedValue r23 = new TypedValue();
        if (r1.getTheme().resolveAttribute(R.attr.isMaterialTheme, r23, true) == true) goto L7;
    L10:
        checkMaterialTheme(r1);
        goto L11
    L7:
        if (r23.type != 18) goto L11;
        if (r23.data == 0) goto L10;
    L11:
        checkAppCompatTheme(r1);
    }

    public static void checkMaterialTheme(Context r2) {
        checkTheme(r2, MATERIAL_CHECK_ATTRS, MATERIAL_THEME_NAME);
    }

    private static void checkTextAppearance(Context r3, AttributeSet r4, int[] r5, int r6, int r7, int... r8) {
        TypedArray r0 = r3.obtainStyledAttributes(r4, R.styleable.ThemeEnforcement, r6, r7);
        boolean r2 = false;
        if (r0.getBoolean(R.styleable.ThemeEnforcement_enforceTextAppearance, false) == true) goto L6;
        r0.recycle();
        return;
    L6:
        if (r8 == null) goto L12;
        if (r8.length == 0) goto L12;
        boolean r32 = isCustomTextAppearanceValid(r3, r4, r5, r6, r7, r8);
    L15:
        r0.recycle();
        if (r32 == false) goto L18;
        return;
    L18:
        f$$ExternalSyntheticBUOutline1.m("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
        return;
    L12:
        if (r0.getResourceId(R.styleable.ThemeEnforcement_android_textAppearance, -1) == (-1)) goto L14;
        r2 = true;
    L14:
        r32 = r2;
        goto L15
    }

    private static void checkTheme(Context r0, int[] r1, String r2) {
        if (isTheme(r0, r1) == false) goto L5;
        return;
    L5:
        Native$$ExternalSyntheticBUOutline0.m("The style on this component requires your app theme to be ", r2, " (or a descendant).");
    }

    public static boolean isAppCompatTheme(Context r1) {
        return isTheme(r1, APPCOMPAT_CHECK_ATTRS);
    }

    private static boolean isCustomTextAppearanceValid(Context r1, AttributeSet r2, int[] r3, int r4, int r5, int... r6) {
        TypedArray r12 = r1.obtainStyledAttributes(r2, r3, r4, r5);
        int r22 = r6.length;
        int r42 = 0;
    L3:
        if (r42 >= r22) goto L9;
        if (r12.getResourceId(r6[r42], -1) == (-1)) goto L6;
        r42 = r42 + 1;
        goto L3
    L6:
        r12.recycle();
        return false;
    L9:
        r12.recycle();
        return true;
    }

    public static boolean isMaterial3Theme(Context r2) {
        return MaterialAttributes.resolveBoolean(r2, R.attr.isMaterial3Theme, false);
    }

    public static boolean isMaterialTheme(Context r1) {
        return isTheme(r1, MATERIAL_CHECK_ATTRS);
    }

    private static boolean isTheme(Context r3, int[] r4) {
        TypedArray r32 = r3.obtainStyledAttributes(r4);
        int r1 = 0;
    L4:
        if (r1 >= r4.length) goto L10;
        if (r32.hasValue(r1) == false) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        r32.recycle();
        return false;
    L10:
        r32.recycle();
        return true;
    }

    public static TypedArray obtainStyledAttributes(Context r0, AttributeSet r1, int[] r2, int r3, int r4, int... r5) {
        checkCompatibleTheme(r0, r1, r3, r4);
        checkTextAppearance(r0, r1, r2, r3, r4, r5);
        return r0.obtainStyledAttributes(r1, r2, r3, r4);
    }

    public static TintTypedArray obtainTintedStyledAttributes(Context r0, AttributeSet r1, int[] r2, int r3, int r4, int... r5) {
        checkCompatibleTheme(r0, r1, r3, r4);
        checkTextAppearance(r0, r1, r2, r3, r4, r5);
        return TintTypedArray.obtainStyledAttributes(r0, r1, r2, r3, r4);
    }
}
