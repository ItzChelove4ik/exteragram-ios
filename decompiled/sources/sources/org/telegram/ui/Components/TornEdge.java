package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public abstract class TornEdge {

    public static final class Params {
        public float jitterDp;
        public int seed;
        public float stepDp;

        public Params() {
            this.seed = 1337;
            this.stepDp = 2.5f;
            this.jitterDp = 1.9f;
        }

        public float maxDeviationDp() {
            return this.jitterDp;
        }

        public int paddingPx() {
            return (int) Math.ceil(TornEdge.m13375$$Nest$smpx(maxDeviationDp()));
        }
    }

    /* renamed from: -$$Nest$smpx, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13375$$Nest$smpx(float r0) {
        return px(r0);
    }

    public static int bitmapHeight(Params r0) {
        return fragmentHeight(r0) * 2;
    }

    public static Path buildSlabPath(Params r5, float r6, float r7, float r8, float[] r9, float[] r10) {
        float r52 = Math.max(1.0f, px(r5.stepDp));
        Path r0 = new Path();
        if (r9 == null) goto L12;
        int r2 = 0;
    L6:
        if (r2 >= r9.length) goto L13;
        float r3 = Math.min(r2 * r52, r6);
        float r4 = r9[r2] + r7;
        if (r2 != 0) goto L10;
        r0.moveTo(r3, r4);
    L11:
        r2 = r2 + 1;
        goto L6
    L10:
        r0.lineTo(r3, r4);
    L13:
        if (r10 == null) goto L17;
        int r72 = r10.length - 1;
    L15:
        if (r72 < 0) goto L18;
        r0.lineTo(Math.min(r72 * r52, r6), r10[r72] + r8);
        r72 = r72 - 1;
    L18:
        r0.close();
        return r0;
    L17:
        r0.lineTo(r6, r8);
        r0.lineTo(0.0f, r8);
        goto L18
    L12:
        r0.moveTo(0.0f, r7);
        r0.lineTo(r6, r7);
        goto L13
    }

    public static Bitmap createTearBitmap(Params r11, int r12, int r13) {
        int r0 = r11.paddingPx();
        int r1 = bitmapHeight(r11);
        float[] r9 = profile(r11, r12, r13);
        Bitmap r132 = Bitmap.createBitmap(r12, r1, Bitmap.Config.ALPHA_8);
        draw(new Canvas(r132), r11, r12, r0, r1 - r0, -16777216, 0.0f, r9, r9);
        return r132;
    }

    public static void draw(Canvas r6, Params r7, float r8, float r9, float r10, int r11, float r12, float[] r13, float[] r14) {
        Path r72 = buildSlabPath(r7, r8, r9, r10, r13, r14);
        if (r12 <= 0.0f) goto L5;
        Path r92 = new Path();
        RectF r132 = new RectF();
        r72.computeBounds(r132, true);
        r92.addRoundRect(new RectF(0.0f, r132.top, r8, r132.bottom), r12, r12, Path.Direction.CW);
        r72.op(r92, Path.Op.INTERSECT);
    L5:
        Paint r82 = new Paint(1);
        r82.setColor(r11);
        r6.drawPath(r72, r82);
    }

    public static void drawBottomEdge(Canvas r8, Bitmap r9, Params r10, int r11, float r12, float r13, Paint r14) {
        int r6 = fragmentHeight(r10);
        stamp(r8, r9, r11, r12, r13 - r6, 0, r6, r14);
    }

    public static void drawTopEdge(Canvas r8, Bitmap r9, Params r10, int r11, float r12, float r13, Paint r14) {
        int r5 = fragmentHeight(r10);
        stamp(r8, r9, r11, r12, r13, r5, r5, r14);
    }

    public static int fragmentHeight(Params r1) {
        return (r1.paddingPx() * 2) + solidGuardPx();
    }

    private static int hash(int r1, int r2) {
        int r12 = (r1 * 374761393) + (r2 * 668265263);
        int r13 = (r12 ^ (r12 >>> 13)) * 1274126177;
        return r13 ^ (r13 >>> 16);
    }

    public static float[] profile(Params r6, int r7, int r8) {
        float r0 = Math.max(1.0f, px(r6.stepDp));
        float r62 = px(r6.jitterDp);
        int r72 = ((int) Math.ceil(r7 / r0)) + 1;
        float[] r02 = new float[r72];
        int r1 = 0;
    L3:
        if (r1 >= r72) goto L5;
        r02[r1] = (float) (((rand01(r1, 40503 ^ r8) - 0.5d) * 2.0d) * r62);
        r1 = r1 + 1;
        goto L3
    L5:
        return r02;
    }

    private static float px(float r1) {
        return r1 * AndroidUtilities.density;
    }

    private static double rand01(int r2, int r3) {
        return (hash(r2, r3) >>> 8) / 1.6777216E7d;
    }

    private static int solidGuardPx() {
        return Math.max(1, (int) Math.ceil(px(0.5f)));
    }

    private static void stamp(Canvas r3, Bitmap r4, int r5, float r6, float r7, int r8, int r9, Paint r10) {
        int r52 = Math.min(r5, r4.getWidth());
        int r62 = (int) r6;
        int r72 = (int) r7;
        r3.drawBitmap(r4, new Rect(0, r8, r52, r8 + r9), new Rect(r62, r72, r52 + r62, r9 + r72), r10);
    }
}
