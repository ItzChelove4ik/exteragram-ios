package org.telegram.ui.Components.Paint;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* loaded from: classes7.dex */
public abstract class Render {
    private static RectF Draw(RenderState r24) {
        RectF r0 = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        int r2 = r24.getCount();
        if (r2 > 0) goto L5;
        return r0;
    L5:
        int r4 = r2 - 1;
        ByteBuffer r3 = ByteBuffer.allocateDirect(((r2 * 4) + (r4 * 2)) * 20);
        r3.order(ByteOrder.nativeOrder());
        FloatBuffer r32 = r3.asFloatBuffer();
        int r11 = 0;
        r32.position(0);
        r24.setPosition(0);
        int r6 = 0;
        int r12 = 0;
    L7:
        if (r6 >= r2) goto L16;
        float r7 = r24.read();
        float r8 = r24.read();
        float r10 = r24.read();
        float r9 = r24.read();
        float r13 = r24.read();
        int r20 = r11;
        RectF r15 = new RectF(r7 - r10, r8 - r10, r7 + r10, r8 + r10);
        float r72 = r15.left;
        float r82 = r15.top;
        float r102 = r15.right;
        float r112 = r15.bottom;
        float[] r14 = new float[8];
        r14[r20] = r72;
        r14[1] = r82;
        r14[2] = r102;
        r14[3] = r82;
        r14[4] = r72;
        r14[5] = r112;
        r14[6] = r102;
        r14[7] = r112;
        float r103 = r15.centerX();
        float r113 = r15.centerY();
        Matrix r73 = new Matrix();
        r73.setRotate((float) Math.toDegrees(r9), r103, r113);
        r73.mapPoints(r14);
        r73.mapRect(r15);
        Utils.RectFIntegral(r15);
        r0.union(r15);
        if (r12 == 0) goto L11;
        r32.put(r14[r20]);
        r32.put(r14[1]);
        r32.put(0.0f);
        r32.put(0.0f);
        r32.put(r13);
        r12 = r12 + 1;
    L11:
        r32.put(r14[r20]);
        r32.put(r14[1]);
        r32.put(0.0f);
        r32.put(0.0f);
        r32.put(r13);
        r32.put(r14[2]);
        r32.put(r14[3]);
        r32.put(1.0f);
        r32.put(0.0f);
        r32.put(r13);
        r32.put(r14[4]);
        r32.put(r14[5]);
        r32.put(0.0f);
        r32.put(1.0f);
        r32.put(r13);
        r32.put(r14[6]);
        r32.put(r14[7]);
        r32.put(1.0f);
        r32.put(1.0f);
        r32.put(r13);
        int r83 = r12 + 4;
        if (r6 == r4) goto L14;
        r32.put(r14[6]);
        r32.put(r14[7]);
        r32.put(1.0f);
        r32.put(1.0f);
        r32.put(r13);
        r12 = r12 + 5;
    L15:
        r6 = r6 + 1;
        r11 = r20;
        goto L7
    L14:
        r12 = r83;
        goto L15
    L16:
        r32.position(r11);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 20, r32.slice());
        GLES20.glEnableVertexAttribArray(r11);
        r32.position(2);
        GLES20.glVertexAttribPointer(1, 2, 5126, true, 20, r32.slice());
        GLES20.glEnableVertexAttribArray(1);
        r32.position(4);
        GLES20.glVertexAttribPointer(2, 1, 5126, true, 20, r32.slice());
        GLES20.glEnableVertexAttribArray(2);
        GLES20.glDrawArrays(5, 0, r12);
        return r0;
    }

    private static void PaintSegment(Point r19, Point r20, RenderState r21) {
        RenderState r2 = r21;
        double r8 = r19.getDistanceTo(r20);
        Point r3 = r20.substract(r19);
        Point r10 = new Point(1.0d, 1.0d, 0.0d);
        if (Math.abs(r2.angle) <= 0.0f) goto L6;
        float r4 = r2.angle;
    L5:
        float r5 = r4;
        float r42 = (float) ((((r2.baseWeight * r20.z) * r2.scale) * 1.0d) / r2.viewportScale);
        double r13 = Math.max(1.0f, r2.spacing * r42);
        if (r8 <= 0.0d) goto L10;
        r10 = r3.multiplyByScalar(1.0d / r8);
    L10:
        float r11 = Math.min(1.0f, r2.alpha * 1.15f);
        boolean r32 = r19.edge;
        boolean r12 = r20.edge;
        int r6 = (int) Math.ceil((r8 - r2.remainder) / r13);
        int r7 = r2.getCount();
        r2.appendValuesCount(r6);
        r2.setPosition(r7);
        Point r0 = r19.add(r10.multiplyByScalar(r2.remainder));
        double r16 = r2.remainder;
        boolean r62 = true;
    L12:
        if (r16 > r8) goto L20;
        if (r32 == false) goto L15;
        float r63 = r11;
    L16:
        r62 = r2.addPoint(r0.toPointF(), r42, r5, r63, -1);
        float r33 = r5;
        if (r62 == false) goto L21;
        r0 = r0.add(r10.multiplyByScalar(r13));
        r16 = r16 + r13;
        r5 = r33;
        r32 = false;
    L21:
        if (r62 == false) goto L24;
        if (r12 == false) goto L24;
        r2.appendValuesCount(1);
        r2.addPoint(r20.toPointF(), r42, r33, r11, -1);
        r2 = r2;
    L24:
        r2.remainder = r16 - r8;
        return;
    L15:
        r63 = r2.alpha;
        goto L16
    L20:
        r33 = r5;
        goto L21
    L6:
        r4 = (float) Math.atan2(r3.y, r3.x);
        goto L5
    }

    private static void PaintStamp(Point r8, RenderState r9) {
        float r4 = ((r9.baseWeight * r9.scale) * 1.0f) / r9.viewportScale;
        PointF r3 = r8.toPointF();
        float r0 = 0.0f;
        if (Math.abs(r9.angle) <= 0.0f) goto L5;
        r0 = r9.angle;
    L5:
        float r6 = r9.alpha;
        r9.prepare();
        r9.appendValuesCount(1);
        r9.addPoint(r3, r4, r0, r6, 0);
    }

    public static RectF RenderPath(Path r4, RenderState r5, boolean r6) {
        r5.baseWeight = r4.getBaseWeight();
        r5.spacing = r4.getBrush().getSpacing();
        if (r6 == false) goto L5;
        float r62 = 1.0f;
    L6:
        r5.alpha = r62;
        r5.angle = r4.getBrush().getAngle();
        r5.scale = r4.getBrush().getScale();
        int r63 = r4.getLength();
        if (r63 != 0) goto L10;
        return null;
    L10:
        int r0 = 0;
        if (r63 != 1) goto L13;
        PaintStamp(r4.getPoints()[0], r5);
    L17:
        r4.remainder = r5.remainder;
        return Draw(r5);
    L13:
        Point[] r64 = r4.getPoints();
        r5.prepare();
    L15:
        if (r0 >= (r64.length - 1)) goto L17;
        Point r2 = r64[r0];
        r0 = r0 + 1;
        PaintSegment(r2, r64[r0], r5);
        goto L15
    L5:
        r62 = r4.getBrush().getAlpha();
        goto L6
    }
}
