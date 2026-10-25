package com.google.android.material.resources;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import com.google.android.material.R;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class MaterialAttributes {
    public MaterialAttributes() {
    }

    public static TypedValue resolve(Context r0, int r1) {
        return resolve(r0.getTheme(), r1);
    }

    public static TypedValue resolve(Resources.Theme r2, int r3) {
        TypedValue r0 = new TypedValue();
        if (r2.resolveAttribute(r3, r0, true) == false) goto L5;
        return r0;
    L5:
        return null;
    }

    public static boolean resolveBoolean(Context r0, int r1, boolean r2) {
        return resolveBoolean(r0.getTheme(), r1, r2);
    }

    public static boolean resolveBoolean(Resources.Theme r1, int r2, boolean r3) {
        TypedValue r12 = resolve(r1, r2);
        if (r12 != null) goto L5;
    L12:
        return r3;
    L5:
        if (r12.type != 18) goto L12;
        if (r12.data == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean resolveBooleanOrThrow(Context r0, int r1, String r2) {
        if (resolveOrThrow(r0, r1, r2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static float resolveDimension(Resources.Theme r2, int r3, float r4) {
        TypedValue r32 = resolve(r2, r3);
        if (r32 != null) goto L5;
    L9:
        return r4;
    L5:
        if (r32.type != 5) goto L9;
        return r32.getDimension(r2.getResources().getDisplayMetrics());
    }

    public static int resolveDimension(Context r2, int r3, int r4) {
        float r32 = resolveDimension(r2.getTheme(), r3, Float.NaN);
        if (Float.isNaN(r32) == false) goto L7;
        return (int) r2.getResources().getDimension(r4);
    L7:
        return (int) r32;
    }

    public static int resolveInteger(Context r0, int r1, int r2) {
        return resolveInteger(r0.getTheme(), r1, r2);
    }

    public static int resolveInteger(Resources.Theme r1, int r2, int r3) {
        TypedValue r12 = resolve(r1, r2);
        if (r12 != null) goto L5;
    L8:
        return r3;
    L5:
        if (r12.type != 16) goto L8;
        return r12.data;
    }

    public static int resolveMinimumAccessibleTouchTarget(Context r2) {
        return resolveDimension(r2, R.attr.minTouchTargetSize, R.dimen.mtrl_min_touch_target_size);
    }

    public static int resolveOrThrow(Context r0, int r1, String r2) {
        return resolveTypedValueOrThrow(r0, r1, r2).data;
    }

    public static int resolveOrThrow(View r0, int r1) {
        return resolveTypedValueOrThrow(r0, r1).data;
    }

    public static TypedValue resolveTypedValueOrThrow(Context r1, int r2, String r3) {
        TypedValue r0 = resolve(r1, r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        FieldSet$$ExternalSyntheticBUOutline0.m("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", new Object[]{r3, r1.getResources().getResourceName(r2)});
        return null;
    }

    public static TypedValue resolveTypedValueOrThrow(View r1, int r2) {
        return resolveTypedValueOrThrow(r1.getContext(), r2, r1.getClass().getCanonicalName());
    }
}
