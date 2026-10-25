package org.telegram.messenger.utils;

import android.graphics.RectF;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Deprecated
/* loaded from: classes6.dex */
public abstract class RectFMergeBounding {
    private static final Comparator<RectF> RECT_COMPARATOR = null;

    /* renamed from: $r8$lambda$3FEaNDNxTEmLiir9-NQLN9ndvqw, reason: not valid java name */
    public static /* synthetic */ int m5203$r8$lambda$3FEaNDNxTEmLiir9NQLN9ndvqw(RectF r3, RectF r4) {
        if (Math.abs(r3.top - r4.top) <= 1.0E-4f) goto L8;
        if (r3.top < r4.top) goto L17;
        return 1;
    L17:
        return -1;
    L8:
        if (Math.abs(r3.left - r4.left) > 1.0E-4f) goto L10;
        return 0;
    L10:
        if (r3.left >= r4.left) goto L18;
        return -1;
    L18:
        return 1;
    }

    static {
        RECT_COMPARATOR = new RectFMergeBounding$$ExternalSyntheticLambda0();
    }

    private static boolean intersectsOrTouches(RectF r3, RectF r4) {
        if (r3.left <= (r4.right + 1.0E-4f)) goto L5;
        return false;
    L5:
        if (r3.right >= (r4.left - 1.0E-4f)) goto L7;
        return false;
    L7:
        if (r3.top <= (r4.bottom + 1.0E-4f)) goto L9;
        return false;
    L9:
        if (r3.bottom < (r4.top - 1.0E-4f)) goto L16;
        return true;
    L16:
        return false;
    }

    public static int mergeOverlapping(List<RectF> r5, int r6, List<RectF> r7) {
        if (r5 == null) goto L50;
        if (r6 <= 0) goto L50;
        if (r6 <= r5.size()) goto L9;
        r6 = r5.size();
    L9:
        int r1 = r7.size();
    L10:
        if (r1 >= r6) goto L12;
        r7.add(new RectF());
        r1 = r1 + 1;
        goto L10
    L12:
        int r12 = 0;
    L13:
        if (r12 >= r6) goto L19;
        RectF r2 = r5.get(r12);
        RectF r3 = r7.get(r12);
        if (r2 == null) goto L17;
        r3.set(r2);
    L18:
        r12 = r12 + 1;
        goto L13
    L17:
        r3.set(0.0f, 0.0f, 0.0f, 0.0f);
    L19:
        int r52 = 0;
    L20:
        if (r52 >= r6) goto L42;
        RectF r13 = r7.get(r52);
        r52 = r52 + 1;
        int r22 = r52;
    L22:
        if (r22 >= r6) goto L20;
        RectF r32 = r7.get(r22);
        if (intersectsOrTouches(r13, r32) == true) goto L25;
        r22 = r22 + 1;
        goto L22
    L25:
        float r53 = r32.left;
        if (r53 >= r13.left) goto L28;
        r13.left = r53;
    L28:
        float r54 = r32.top;
        if (r54 >= r13.top) goto L31;
        r13.top = r54;
    L31:
        float r55 = r32.right;
        if (r55 <= r13.right) goto L34;
        r13.right = r55;
    L34:
        float r56 = r32.bottom;
        if (r56 <= r13.bottom) goto L37;
        r13.bottom = r56;
    L37:
        int r57 = r6 - 1;
        if (r22 == r57) goto L40;
        r7.get(r22).set(r7.get(r57));
    L40:
        r6 = r6 - 1;
        boolean r58 = true;
    L43:
        if (r58 == true) goto L19;
        int r59 = r6;
    L46:
        if (r59 >= r7.size()) goto L48;
        RectF r0 = r7.get(r59);
        r0.top = Float.MAX_VALUE;
        r0.left = Float.MAX_VALUE;
        r59 = r59 + 1;
        goto L46
    L48:
        Collections.sort(r7, RECT_COMPARATOR);
        return r6;
    L42:
        r58 = false;
    L50:
        return 0;
    }
}
