package com.google.android.material.internal;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* loaded from: classes5.dex */
public class DescendantOffsetUtils {
    private static final ThreadLocal<Matrix> matrix = null;
    private static final ThreadLocal<RectF> rectF = null;

    static {
        matrix = new ThreadLocal();
        rectF = new ThreadLocal();
    }

    public DescendantOffsetUtils() {
    }

    public static void getDescendantRect(ViewGroup r3, View r4, Rect r5) {
        r5.set(0, 0, r4.getWidth(), r4.getHeight());
        offsetDescendantRect(r3, r4, r5);
    }

    private static void offsetDescendantMatrix(ViewParent r2, View r3, Matrix r4) {
        Object r0 = r3.getParent();
        if ((r0 instanceof View) == false) goto L6;
        if (r0 == r2) goto L6;
        offsetDescendantMatrix(r2, (View) r0, r4);
        r4.preTranslate(-r0.getScrollX(), -r0.getScrollY());
    L6:
        r4.preTranslate(r3.getLeft(), r3.getTop());
        if (r3.getMatrix().isIdentity() == true) goto L10;
        r4.preConcat(r3.getMatrix());
        return;
    }

    public static void offsetDescendantRect(ViewGroup r3, View r4, Rect r5) {
        ThreadLocal<Matrix> r0 = matrix;
        Matrix r1 = r0.get();
        if (r1 != null) goto L5;
        r1 = new Matrix();
        r0.set(r1);
    L6:
        offsetDescendantMatrix(r3, r4, r1);
        ThreadLocal<RectF> r32 = rectF;
        RectF r42 = r32.get();
        if (r42 != null) goto L9;
        r42 = new RectF();
        r32.set(r42);
    L9:
        r42.set(r5);
        r1.mapRect(r42);
        r5.set((int) (r42.left + 0.5f), (int) (r42.top + 0.5f), (int) (r42.right + 0.5f), (int) (r42.bottom + 0.5f));
        return;
    L5:
        r1.reset();
        goto L6
    }
}
