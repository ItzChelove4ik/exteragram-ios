package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.Random;
import org.telegram.messenger.LiteMode;

/* loaded from: classes7.dex */
public class BlobDrawable {
    public static float AMPLITUDE_SPEED = 0.33f;
    public static float FORM_BIG_MAX = 0.6f;
    public static float FORM_SMALL_MAX = 0.6f;
    public static float GLOBAL_SCALE = 1.0f;
    public static float GRADIENT_SPEED_MAX = 0.01f;
    public static float GRADIENT_SPEED_MIN = 0.5f;
    public static float LIGHT_GRADIENT_SIZE = 0.5f;
    public static float MAX_SPEED = 8.2f;
    public static float MIN_SPEED = 0.8f;
    public static float SCALE_BIG = 0.807f;
    public static float SCALE_BIG_MIN = 0.878f;
    public static float SCALE_SMALL = 0.704f;
    public static float SCALE_SMALL_MIN = 0.926f;
    private final float L;
    protected final float N;
    public float amplitude;
    protected float[] angle;
    protected float[] angleNext;
    private float animateAmplitudeDiff;
    private float animateToAmplitude;
    public float cubicBezierK;
    protected final int liteFlag;
    private final Matrix m;
    public float maxRadius;
    public float minRadius;
    public Paint paint;
    private Path path;
    private float[] pointEnd;
    private float[] pointStart;
    protected float[] progress;
    protected float[] radius;
    protected float[] radiusNext;
    protected final Random random;
    protected float[] speed;

    static {
    }

    public BlobDrawable(int r2) {
        this(r2, 512);
    }

    public BlobDrawable(int r5, int r6) {
        this.path = new Path();
        this.paint = new Paint(1);
        this.pointStart = new float[4];
        this.pointEnd = new float[4];
        this.random = new Random();
        this.cubicBezierK = 1.0f;
        this.m = new Matrix();
        this.N = r5;
        this.L = (float) (Math.tan(3.141592653589793d / (r0 * 2.0f)) * 1.3333333333333333d);
        this.radius = new float[r5];
        this.angle = new float[r5];
        this.radiusNext = new float[r5];
        this.angleNext = new float[r5];
        this.progress = new float[r5];
        this.speed = new float[r5];
        int r52 = 0;
    L4:
        if (r52 >= this.N) goto L6;
        generateBlob(this.radius, this.angle, r52);
        generateBlob(this.radiusNext, this.angleNext, r52);
        this.progress[r52] = 0.0f;
        r52 = r52 + 1;
        goto L4
    L6:
        this.liteFlag = r6;
    }

    public void draw(float r22, float r23, Canvas r24, Paint r25) {
        if (LiteMode.isEnabled(this.liteFlag) == true) goto L5;
        return;
    L5:
        this.path.reset();
        int r4 = 0;
    L6:
        float r5 = r4;
        float r6 = this.N;
        if (r5 >= r6) goto L16;
        float[] r52 = this.progress;
        float r7 = r52[r4];
        int r8 = r4 + 1;
        if (r8 >= r6) goto L11;
        int r62 = r8;
    L12:
        float r53 = r52[r62];
        float[] r9 = this.radius;
        float r12 = 1.0f - r7;
        float r10 = r9[r4] * r12;
        float[] r13 = this.radiusNext;
        float r102 = r10 + (r13[r4] * r7);
        float r11 = 1.0f - r53;
        float r92 = (r9[r62] * r11) + (r13[r62] * r53);
        float[] r132 = this.angle;
        float r14 = r132[r4] * r12;
        float[] r122 = this.angleNext;
        float r142 = r14 + (r122[r4] * r7);
        float r72 = (r132[r62] * r11) + (r122[r62] * r53);
        float r54 = (this.L * (Math.min(r102, r92) + ((Math.max(r102, r92) - Math.min(r102, r92)) / 2.0f))) * this.cubicBezierK;
        this.m.reset();
        this.m.setRotate(r142, r22, r23);
        float[] r63 = this.pointStart;
        r63[0] = r22;
        float r103 = r23 - r102;
        r63[1] = r103;
        r63[2] = r22 + r54;
        r63[3] = r103;
        this.m.mapPoints(r63);
        float[] r64 = this.pointEnd;
        r64[0] = r22;
        float r93 = r23 - r92;
        r64[1] = r93;
        r64[2] = r22 - r54;
        r64[3] = r93;
        this.m.reset();
        this.m.setRotate(r72, r22, r23);
        this.m.mapPoints(this.pointEnd);
        if (r4 != 0) goto L15;
        Path r42 = this.path;
        float[] r55 = this.pointStart;
        r42.moveTo(r55[0], r55[1]);
    L15:
        Path r143 = this.path;
        float[] r43 = this.pointStart;
        float r15 = r43[2];
        float r16 = r43[3];
        float[] r44 = this.pointEnd;
        r143.cubicTo(r15, r16, r44[2], r44[3], r44[0], r44[1]);
        r4 = r8;
        goto L6
    L11:
        r62 = 0;
        goto L12
    L16:
        r24.save();
        r24.drawPath(this.path, r25);
        r24.restore();
    }

    public void generateBlob() {
        int r0 = 0;
    L4:
        if (r0 >= this.N) goto L6;
        generateBlob(this.radius, this.angle, r0);
        generateBlob(this.radiusNext, this.angleNext, r0);
        this.progress[r0] = 0.0f;
        r0 = r0 + 1;
        goto L4
    }

    public void generateBlob(float[] r7, float[] r8, int r9) {
        float r0 = (360.0f / this.N) * 0.05f;
        float r2 = this.maxRadius;
        float r3 = this.minRadius;
        r7[r9] = r3 + (Math.abs((this.random.nextInt() % 100.0f) / 100.0f) * (r2 - r3));
        r8[r9] = ((360.0f / this.N) * r9) + (((this.random.nextInt() % 100.0f) / 100.0f) * r0);
        this.speed[r9] = (float) (((Math.abs(this.random.nextInt() % 100.0f) / 100.0f) * 0.003d) + 0.017d);
    }

    public void setValue(float r2, boolean r3) {
        this.animateToAmplitude = r2;
        if (LiteMode.isEnabled(this.liteFlag) == true) goto L5;
        return;
    L5:
        float r22 = this.animateToAmplitude;
        if (r3 == false) goto L13;
        float r32 = this.amplitude;
        if (r22 <= r32) goto L11;
        this.animateAmplitudeDiff = (r22 - r32) / 205.0f;
        return;
    L11:
        this.animateAmplitudeDiff = (r22 - r32) / 275.0f;
        return;
    L13:
        float r33 = this.amplitude;
        if (r22 <= r33) goto L17;
        this.animateAmplitudeDiff = (r22 - r33) / 320.0f;
        return;
    L17:
        this.animateAmplitudeDiff = (r22 - r33) / 375.0f;
    }

    public void update(float r7, float r8) {
        if (LiteMode.isEnabled(this.liteFlag) == false) goto L12;
        int r0 = 0;
    L7:
        if (r0 >= this.N) goto L16;
        float[] r1 = this.progress;
        float r2 = r1[r0];
        float r3 = this.speed[r0];
        float r22 = r2 + ((MIN_SPEED * r3) + (((r3 * r7) * MAX_SPEED) * r8));
        r1[r0] = r22;
        if (r22 < 1.0f) goto L11;
        r1[r0] = 0.0f;
        float[] r12 = this.radius;
        float[] r23 = this.radiusNext;
        r12[r0] = r23[r0];
        float[] r13 = this.angle;
        float[] r32 = this.angleNext;
        r13[r0] = r32[r0];
        generateBlob(r23, r32, r0);
    L11:
        r0 = r0 + 1;
        goto L7
    L16:
        return;
    }

    public void updateAmplitude(long r4) {
        float r0 = this.animateToAmplitude;
        float r1 = this.amplitude;
        if (r0 == r1) goto L14;
        float r2 = this.animateAmplitudeDiff;
        float r12 = r1 + (r4 * r2);
        this.amplitude = r12;
        if (r2 <= 0.0f) goto L11;
        if (r12 <= r0) goto L15;
        this.amplitude = r0;
        return;
    L15:
        return;
    L11:
        if (r12 >= r0) goto L16;
        this.amplitude = r0;
        return;
    L16:
        return;
    }
}
