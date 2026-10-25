package org.telegram.utils.glass.positions;

import android.graphics.RectF;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public final class GlassPositionsMerger {
    public static final GlassPositionsMerger DEFAULT = null;
    private final float epsX;
    private final float epsY;

    static {
        DEFAULT = new GlassPositionsMerger(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    }

    public GlassPositionsMerger(float r3, float r4) {
        if (Float.isNaN(r3) == false) goto L5;
    L14:
        f$$ExternalSyntheticBUOutline1.m("epsX must be >= 0");
        throw null;
    L5:
        if (r3 < 0.0f) goto L14;
        if (Float.isNaN(r4) == false) goto L9;
    L12:
        f$$ExternalSyntheticBUOutline1.m("epsY must be >= 0");
        throw null;
    L9:
        if (r4 < 0.0f) goto L12;
        this.epsX = r3;
        this.epsY = r4;
    }

    private static int compare(float r0, float r1, float r2, float r3) {
        int r02 = Float.compare(r0, r2);
        if (r02 == 0) goto L5;
        return r02;
    L5:
        return Float.compare(r1, r3);
    }

    private boolean intersectsOrNear(float r4, float r5, float r6, float r7, RectF r8) {
        if (intersectsOrNear1D(r4, r6, r8.left, r8.right, this.epsX) == true) goto L5;
        return false;
    L5:
        if (intersectsOrNear1D(r5, r7, r8.top, r8.bottom, this.epsY) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean intersectsOrNear1D(float r3, float r4, float r5, float r6, float r7) {
        if (r4 >= r5) goto L9;
        if ((r5 - r4) > r7) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r6 < r3) goto L11;
        return true;
    L11:
        if ((r3 - r6) > r7) goto L13;
        return true;
    L13:
        return false;
    }

    private static void sort(GlassPositionsArray r10) {
        int r0 = r10.size();
        int r1 = 1;
    L3:
        if (r1 >= r0) goto L11;
        RectF r2 = r10.get(r1);
        float r3 = r2.left;
        float r4 = r2.top;
        float r5 = r2.right;
        float r22 = r2.bottom;
        int r6 = r1 - 1;
    L5:
        if (r6 < 0) goto L10;
        RectF r7 = r10.get(r6);
        if (compare(r7.top, r7.left, r4, r3) <= 0) goto L10;
        r10.get(r6 + 1).set(r7);
        r6 = r6 - 1;
    L10:
        r10.get(r6 + 1).set(r3, r4, r5, r22);
        r1 = r1 + 1;
        goto L3
    }

    public void merge(GlassPositionsArray r14, GlassPositionsArray r15) {
        if (r14 == r15) goto L28;
        r15.clear();
        int r0 = r14.size();
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L26;
        RectF r3 = r14.get(r2);
        float r4 = r3.left;
        float r5 = r3.top;
        float r6 = r3.right;
        float r11 = r3.bottom;
        float r8 = r4;
        float r9 = r5;
        float r10 = r6;
        int r32 = 0;
    L7:
        if (r32 >= r15.size()) goto L25;
        RectF r12 = r15.get(r32);
        GlassPositionsMerger r7 = this;
        if (r7.intersectsOrNear(r8, r9, r10, r11, r12) == false) goto L23;
        float r13 = r12.left;
        if (r13 >= r8) goto L13;
        r8 = r13;
    L13:
        float r132 = r12.top;
        if (r132 >= r9) goto L16;
        r9 = r132;
    L16:
        float r133 = r12.right;
        if (r133 <= r10) goto L19;
        r10 = r133;
    L19:
        float r134 = r12.bottom;
        if (r134 <= r11) goto L22;
        r11 = r134;
    L22:
        r15.removeUnordered(r32);
        r32 = 0;
    L24:
        GlassPositionsMerger this = r7;
        goto L7
    L23:
        r32 = r32 + 1;
        goto L24
    L25:
        r15.add(r8, r9, r10, r11);
        r2 = r2 + 1;
        goto L4
    L26:
        sort(r15);
        return;
    L28:
        f$$ExternalSyntheticBUOutline1.m("positions and output must be different arrays");
    }
}
