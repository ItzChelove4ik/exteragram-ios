package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.Random;

/* loaded from: classes7.dex */
public class CircleBezierDrawable {
    private final float L;
    private final int N;
    public float cubicBezierK;
    float globalRotate;
    public float idleStateDiff;
    private Matrix m;
    private Path path;
    private float[] pointEnd;
    private float[] pointStart;
    public float radius;
    public float radiusDiff;
    final Random random;
    float[] randomAdditionals;
    public float randomK;

    public CircleBezierDrawable(int r5) {
        this.path = new Path();
        this.pointStart = new float[4];
        this.pointEnd = new float[4];
        this.m = new Matrix();
        this.globalRotate = 0.0f;
        this.idleStateDiff = 0.0f;
        this.cubicBezierK = 1.0f;
        this.random = new Random();
        this.N = r5;
        this.L = (float) (Math.tan(3.141592653589793d / (r5 * 2)) * 1.3333333333333333d);
        this.randomAdditionals = new float[r5];
        calculateRandomAdditionals();
    }

    public void calculateRandomAdditionals() {
        int r0 = 0;
    L4:
        if (r0 >= this.N) goto L6;
        this.randomAdditionals[r0] = (this.random.nextInt() % 100) / 100.0f;
        r0 = r0 + 1;
        goto L4
    }

    public void draw(float r26, float r27, Canvas r28, Paint r29) {
        float r4 = this.radius;
        float r5 = this.idleStateDiff;
        float r8 = this.radiusDiff;
        float r7 = (r4 - (r5 / 2.0f)) - (r8 / 2.0f);
        float r42 = (r4 + (r8 / 2.0f)) + (r5 / 2.0f);
        float r52 = (this.L * Math.max(r7, r42)) * this.cubicBezierK;
        this.path.reset();
        int r6 = 0;
        int r82 = 0;
    L4:
        if (r82 >= this.N) goto L22;
        this.m.reset();
        this.m.setRotate((360.0f / this.N) * r82, r26, r27);
        if ((r82 % 2) != 0) goto L8;
        float r9 = r7;
    L9:
        float r10 = this.randomK;
        float[] r12 = this.randomAdditionals;
        float r92 = r9 + (r12[r82] * r10);
        float[] r13 = this.pointStart;
        r13[r6] = r26;
        float r93 = r27 - r92;
        r13[1] = r93;
        r13[2] = (r26 + r52) + ((r10 * r12[r82]) * this.L);
        r13[3] = r93;
        this.m.mapPoints(r13);
        int r94 = r82 + 1;
        if (r94 < this.N) goto L12;
        int r132 = r6;
    L14:
        if ((r132 % 2) != 0) goto L17;
        float r15 = r7;
    L16:
        int r16 = r6;
        float r62 = this.randomK;
        float[] r102 = this.randomAdditionals;
        float r152 = r15 + (r102[r132] * r62);
        float[] r11 = this.pointEnd;
        r11[r16] = r26;
        float r153 = r27 - r152;
        r11[1] = r153;
        r11[2] = (r26 - r52) + ((r62 * r102[r132]) * this.L);
        r11[3] = r153;
        this.m.reset();
        this.m.setRotate((360.0f / this.N) * r132, r26, r27);
        this.m.mapPoints(this.pointEnd);
        if (r82 != 0) goto L21;
        Path r63 = this.path;
        float[] r83 = this.pointStart;
        r63.moveTo(r83[r16], r83[1]);
    L21:
        Path r64 = this.path;
        float[] r84 = this.pointStart;
        float r19 = r84[2];
        float r20 = r84[3];
        float[] r85 = this.pointEnd;
        r64.cubicTo(r19, r20, r85[2], r85[3], r85[r16], r85[1]);
        r82 = r94;
        r6 = r16;
        goto L4
    L17:
        r15 = r42;
        goto L16
    L12:
        r132 = r94;
        goto L14
    L8:
        r9 = r42;
        goto L9
    L22:
        r28.save();
        r28.rotate(this.globalRotate, r26, r27);
        r28.drawPath(this.path, r29);
        r28.restore();
    }

    public void setAdditionals(int[] r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.N) goto L6;
        float[] r1 = this.randomAdditionals;
        r1[r0] = r5[r0 / 2];
        r1[r0 + 1] = 0.0f;
        r0 = r0 + 2;
        goto L4
    }
}
