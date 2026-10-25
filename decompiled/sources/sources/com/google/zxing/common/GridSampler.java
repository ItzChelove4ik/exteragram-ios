package com.google.zxing.common;

import com.google.zxing.NotFoundException;

/* loaded from: classes5.dex */
public abstract class GridSampler {
    private static GridSampler gridSampler;

    static {
        gridSampler = new DefaultGridSampler();
    }

    public GridSampler() {
    }

    public static void checkAndNudgePoints(BitMatrix r10, float[] r11) {
        int r0 = r10.getWidth();
        int r102 = r10.getHeight();
        int r1 = r11.length - 1;
        boolean r5 = true;
        int r4 = 0;
    L4:
        if (r4 >= r1) goto L25;
        if (r5 == false) goto L25;
        int r52 = (int) r11[r4];
        int r8 = r4 + 1;
        int r9 = (int) r11[r8];
        if (r52 < (-1)) goto L24;
        if (r52 > r0) goto L24;
        if (r9 < (-1)) goto L24;
        if (r9 > r102) goto L24;
        if (r52 != (-1)) goto L14;
        r11[r4] = 0.0f;
    L13:
        r5 = true;
    L17:
        if (r9 != (-1)) goto L20;
        r11[r8] = 0.0f;
    L19:
        r5 = true;
    L22:
        r4 = r4 + 2;
        goto L4
    L20:
        if (r9 != r102) goto L22;
        r11[r8] = r102 - 1;
        goto L19
    L14:
        if (r52 != r0) goto L16;
        r11[r4] = r0 - 1;
        goto L13
    L16:
        r5 = false;
    L24:
        throw NotFoundException.getNotFoundInstance();
    L25:
        int r12 = r11.length - 2;
        boolean r42 = true;
    L26:
        if (r12 < 0) goto L47;
        if (r42 == false) goto L64;
        int r43 = (int) r11[r12];
        int r53 = r12 + 1;
        int r82 = (int) r11[r53];
        if (r43 < (-1)) goto L46;
        if (r43 > r0) goto L46;
        if (r82 < (-1)) goto L46;
        if (r82 > r102) goto L46;
        if (r43 != (-1)) goto L36;
        r11[r12] = 0.0f;
    L35:
        r42 = true;
    L39:
        if (r82 != (-1)) goto L42;
        r11[r53] = 0.0f;
    L41:
        r42 = true;
    L44:
        r12 = r12 - 2;
        goto L26
    L42:
        if (r82 != r102) goto L44;
        r11[r53] = r102 - 1;
        goto L41
    L36:
        if (r43 != r0) goto L38;
        r11[r12] = r0 - 1;
        goto L35
    L38:
        r42 = false;
    L46:
        throw NotFoundException.getNotFoundInstance();
    L64:
        return;
    }

    public static GridSampler getInstance() {
        return gridSampler;
    }

    public abstract BitMatrix sampleGrid(BitMatrix r1, int r2, int r3, PerspectiveTransform r4);
}
