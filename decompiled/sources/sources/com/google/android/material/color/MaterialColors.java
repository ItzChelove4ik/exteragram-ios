package com.google.android.material.color;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.R$attr;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.color.utilities.Blend;
import com.google.android.material.color.utilities.Hct;
import com.google.android.material.resources.MaterialAttributes;

/* loaded from: classes.dex */
public class MaterialColors {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;
    private static final int CHROMA_NEUTRAL = 6;
    private static final int TONE_ACCENT_CONTAINER_DARK = 30;
    private static final int TONE_ACCENT_CONTAINER_LIGHT = 90;
    private static final int TONE_ACCENT_DARK = 80;
    private static final int TONE_ACCENT_LIGHT = 40;
    private static final int TONE_ON_ACCENT_CONTAINER_DARK = 90;
    private static final int TONE_ON_ACCENT_CONTAINER_LIGHT = 10;
    private static final int TONE_ON_ACCENT_DARK = 20;
    private static final int TONE_ON_ACCENT_LIGHT = 100;
    private static final int TONE_SURFACE_CONTAINER_DARK = 12;
    private static final int TONE_SURFACE_CONTAINER_HIGH_DARK = 17;
    private static final int TONE_SURFACE_CONTAINER_HIGH_LIGHT = 92;
    private static final int TONE_SURFACE_CONTAINER_LIGHT = 94;

    private MaterialColors() {
    }

    public static int compositeARGBWithAlpha(int r1, int r2) {
        return ColorUtils.setAlphaComponent(r1, (Color.alpha(r1) * r2) / 255);
    }

    public static int getColor(Context r0, int r1, int r2) {
        Integer r02 = getColorOrNull(r0, r1);
        if (r02 != null) goto L5;
        return r2;
    L5:
        return r02.intValue();
    }

    public static int getColor(Context r0, int r1, String r2) {
        return resolveColor(r0, MaterialAttributes.resolveTypedValueOrThrow(r0, r1, r2));
    }

    public static int getColor(View r1, int r2) {
        return resolveColor(r1.getContext(), MaterialAttributes.resolveTypedValueOrThrow(r1, r2));
    }

    public static int getColor(View r0, int r1, int r2) {
        return getColor(r0.getContext(), r1, r2);
    }

    public static Integer getColorOrNull(Context r0, int r1) {
        TypedValue r12 = MaterialAttributes.resolve(r0, r1);
        if (r12 != null) goto L5;
        return null;
    L5:
        return Integer.valueOf(resolveColor(r0, r12));
    }

    private static int getColorRole(int r2, int r3) {
        Hct r22 = Hct.fromInt(r2);
        r22.setTone(r3);
        return r22.toInt();
    }

    private static int getColorRole(int r0, int r1, int r2) {
        Hct r02 = Hct.fromInt(getColorRole(r0, r1));
        r02.setChroma(r2);
        return r02.toInt();
    }

    public static ColorRoles getColorRoles(int r4, boolean r5) {
        if (r5 == false) goto L7;
        return new ColorRoles(getColorRole(r4, 40), getColorRole(r4, 100), getColorRole(r4, 90), getColorRole(r4, 10));
    L7:
        return new ColorRoles(getColorRole(r4, 80), getColorRole(r4, 20), getColorRole(r4, 30), getColorRole(r4, 90));
    }

    public static ColorRoles getColorRoles(Context r0, int r1) {
        return getColorRoles(r1, isLightTheme(r0));
    }

    public static ColorStateList getColorStateList(Context r0, int r1, ColorStateList r2) {
        TypedValue r12 = MaterialAttributes.resolve(r0, r1);
        if (r12 == null) goto L5;
        ColorStateList r02 = resolveColorStateList(r0, r12);
    L6:
        if (r02 != null) goto L8;
        return r2;
    L8:
        return r02;
    L5:
        r02 = null;
        goto L6
    }

    public static ColorStateList getColorStateListOrNull(Context r2, int r3) {
        TypedValue r32 = MaterialAttributes.resolve(r2, r3);
        if (r32 != null) goto L5;
        return null;
    L5:
        int r1 = r32.resourceId;
        if (r1 != 0) goto L8;
        int r22 = r32.data;
        if (r22 != 0) goto L12;
        return null;
    L12:
        return ColorStateList.valueOf(r22);
    L8:
        return ContextCompat.getColorStateList(r2, r1);
    }

    public static int getSurfaceContainerFromSeed(Context r1, int r2) {
        if (isLightTheme(r1) == false) goto L5;
        int r12 = 94;
    L7:
        return getColorRole(r2, r12, 6);
    L5:
        r12 = 12;
        goto L7
    }

    public static int getSurfaceContainerHighFromSeed(Context r1, int r2) {
        if (isLightTheme(r1) == false) goto L5;
        int r12 = 92;
    L7:
        return getColorRole(r2, r12, 6);
    L5:
        r12 = 17;
        goto L7
    }

    public static int harmonize(int r0, int r1) {
        return Blend.harmonize(r0, r1);
    }

    public static int harmonizeWithPrimary(Context r2, int r3) {
        return harmonize(r3, getColor(r2, R$attr.colorPrimary, MaterialColors.class.getCanonicalName()));
    }

    public static boolean isColorLight(int r4) {
        if (r4 != 0) goto L4;
        return false;
    L4:
        if (ColorUtils.calculateLuminance(r4) <= 0.5d) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isLightTheme(Context r2) {
        return MaterialAttributes.resolveBoolean(r2, R$attr.isLightTheme, true);
    }

    public static int layer(int r0, int r1) {
        return ColorUtils.compositeColors(r1, r0);
    }

    public static int layer(int r1, int r2, float r3) {
        return layer(r1, ColorUtils.setAlphaComponent(r2, Math.round(Color.alpha(r2) * r3)));
    }

    public static int layer(View r1, int r2, int r3) {
        return layer(r1, r2, r3, 1.0f);
    }

    public static int layer(View r0, int r1, int r2, float r3) {
        return layer(getColor(r0, r1), getColor(r0, r2), r3);
    }

    private static int resolveColor(Context r1, TypedValue r2) {
        int r0 = r2.resourceId;
        if (r0 == 0) goto L7;
        return ContextCompat.getColor(r1, r0);
    L7:
        return r2.data;
    }

    private static ColorStateList resolveColorStateList(Context r1, TypedValue r2) {
        int r0 = r2.resourceId;
        if (r0 == 0) goto L7;
        return ContextCompat.getColorStateList(r1, r0);
    L7:
        return ColorStateList.valueOf(r2.data);
    }
}
