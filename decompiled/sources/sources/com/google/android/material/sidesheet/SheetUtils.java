package com.google.android.material.sidesheet;

/* loaded from: classes5.dex */
final class SheetUtils {
    private SheetUtils() {
    }

    public static boolean isSwipeMostlyHorizontal(float r0, float r1) {
        if (Math.abs(r0) <= Math.abs(r1)) goto L6;
        return true;
    L6:
        return false;
    }
}
