package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public final class DislikeAnalyzer {
    private DislikeAnalyzer() {
        throw new UnsupportedOperationException();
    }

    public static Hct fixIfDisliked(Hct r7) {
        if (isDisliked(r7) == true) goto L5;
        return r7;
    L5:
        return Hct.from(r7.getHue(), r7.getChroma(), 70.0d);
    }

    public static boolean isDisliked(Hct r8) {
        if (Math.round(r8.getHue()) >= 90.0d) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (Math.round(r8.getChroma()) <= 16.0d) goto L11;
        boolean r3 = true;
    L13:
        if (Math.round(r8.getTone()) >= 65.0d) goto L15;
        boolean r82 = true;
    L16:
        if (r0 == false) goto L20;
        if (r3 == false) goto L20;
        if (r82 == false) goto L20;
        return true;
    L20:
        return false;
    L15:
        r82 = false;
        goto L16
    L11:
        r3 = false;
        goto L13
    L5:
        if (Math.round(r8.getHue()) > 111.0d) goto L7;
        r0 = true;
        goto L9
    }
}
