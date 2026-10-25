package org.telegram.ui.Stars;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.zxing.common.detector.MathUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.BatchParticlesDrawHelper;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public abstract class StarGiftPatterns {
    private static final BatchParticlesDrawHelper.BatchParticlesBuffer batchBuffer = null;
    private static final float[][] patternLocations = null;
    private static final float[] profileLeft = null;
    private static final float[] profileRight = null;

    static {
        float[][] r0 = {new float[]{83.33f, 24.0f, 27.33f, 0.22f, 68.66f, 75.33f, 25.33f, 0.21f, 0.0f, 86.0f, 25.33f, 0.12f, -68.66f, 75.33f, 25.33f, 0.21f, -82.66f, 13.66f, 27.33f, 0.22f, -80.0f, -33.33f, 20.0f, 0.24f, -46.5f, -63.16f, 27.0f, 0.21f, 1.0f, -82.66f, 20.0f, 0.15f, 46.5f, -63.16f, 27.0f, 0.21f, 80.0f, -33.33f, 19.33f, 0.24f, 115.66f, -63.0f, 20.0f, 0.15f, 134.0f, -10.66f, 20.0f, 0.18f, 118.66f, 55.66f, 20.0f, 0.15f, 124.33f, 98.33f, 20.0f, 0.11f, -128.0f, 98.33f, 20.0f, 0.11f, -108.0f, 55.66f, 20.0f, 0.15f, -123.33f, -10.66f, 20.0f, 0.18f, -116.0f, -63.33f, 20.0f, 0.15f}, new float[]{27.33f, -57.66f, 20.0f, 0.12f, 59.0f, -32.0f, 19.33f, 0.22f, 77.0f, 4.33f, 22.66f, 0.2f, 100.0f, 40.33f, 18.0f, 0.12f, 58.66f, 59.0f, 20.0f, 0.18f, 73.33f, 100.33f, 22.66f, 0.15f, 75.0f, 155.0f, 22.0f, 0.11f, -27.33f, -57.33f, 20.0f, 0.12f, -59.0f, -32.33f, 19.33f, 0.2f, -77.0f, 4.66f, 23.33f, 0.2f, -98.66f, 41.0f, 18.66f, 0.12f, -58.0f, 59.33f, 19.33f, 0.18f, -73.33f, 100.0f, 22.0f, 0.15f, -75.66f, 155.0f, 22.0f, 0.11f}, new float[]{-0.83f, -52.16f, 12.33f, 0.2f, 26.66f, -40.33f, 16.0f, 0.2f, 44.16f, -20.5f, 12.33f, 0.2f, 53.0f, 7.33f, 16.0f, 0.2f, 31.0f, 23.66f, 14.66f, 0.2f, 0.0f, 32.0f, 13.33f, 0.2f, -29.0f, 23.66f, 14.0f, 0.2f, -53.0f, 7.33f, 16.0f, 0.2f, -44.5f, -20.16f, 12.33f, 0.2f, -27.33f, -40.33f, 16.0f, 0.2f, 43.66f, 50.0f, 14.66f, 0.2f, -41.66f, 48.0f, 14.66f, 0.2f}, new float[]{-0.16f, -103.5f, 20.33f, 0.15f, 39.66f, -77.33f, 26.66f, 0.15f, 70.66f, -46.33f, 21.33f, 0.15f, 84.5f, -3.83f, 29.66f, 0.15f, 65.33f, 56.33f, 24.66f, 0.15f, 0.0f, 67.66f, 24.66f, 0.15f, -65.66f, 56.66f, 24.66f, 0.15f, -85.0f, -4.0f, 29.33f, 0.15f, -70.66f, -46.33f, 21.33f, 0.15f, -40.33f, -77.66f, 26.66f, 0.15f, 62.66f, -109.66f, 21.33f, 0.11f, 103.166f, -67.5f, 20.33f, 0.11f, 110.33f, 37.66f, 20.66f, 0.11f, 94.166f, 91.16f, 20.33f, 0.11f, 38.83f, 91.16f, 20.33f, 0.11f, 0.0f, 112.5f, 20.33f, 0.11f, -38.83f, 91.16f, 20.33f, 0.11f, -94.166f, 91.16f, 20.33f, 0.11f, -110.33f, 37.66f, 20.66f, 0.11f, -103.166f, -67.5f, 20.33f, 0.11f, -62.66f, -109.66f, 21.33f, 0.11f}};
        patternLocations = r0;
        int r1 = r0.length;
        int r2 = 0;
        short r3 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r3 = (short) Math.max(r3, r0[r2].length / 4);
        r2 = r2 + 1;
        goto L3
    L5:
        batchBuffer = new BatchParticlesDrawHelper.BatchParticlesBuffer(r3);
        profileRight = new float[]{-35.66f, -5.0f, 24.0f, 0.2388f, -14.33f, -29.33f, 20.66f, 0.32f, -15.0f, -73.66f, 19.33f, 0.32f, -2.0f, -99.66f, 18.0f, 0.1476f, -64.33f, -24.66f, 23.33f, 0.3235f, -40.66f, -53.33f, 24.0f, 0.3654f, -50.33f, -85.66f, 20.0f, 0.172f, -96.0f, -1.33f, 19.33f, 0.3343f, -136.66f, -13.0f, 18.66f, 0.2569f, -104.66f, -33.66f, 20.66f, 0.2216f, -82.0f, -62.33f, 22.66f, 0.2562f, -131.66f, -60.0f, 18.0f, 0.1316f, -105.66f, -88.33f, 18.0f, 0.1487f};
        profileLeft = new float[]{0.0f, -107.33f, 16.0f, 0.1505f, 14.33f, -84.0f, 18.0f, 0.1988f, 0.0f, -50.66f, 18.66f, 0.3225f, 13.0f, -15.0f, 18.66f, 0.37f, 43.33f, 1.0f, 18.66f, 0.3186f};
    }

    public static void drawPattern(Canvas r11, int r12, Drawable r13, float r14, float r15, float r16, float r17) {
        if (r16 <= 0.0f) goto L13;
        int r1 = 0;
    L6:
        float[] r2 = patternLocations[r12];
        if (r1 >= r2.length) goto L18;
        float r3 = r2[r1];
        float r4 = r2[r1 + 1];
        float r5 = r2[r1 + 2];
        float r22 = r2[r1 + 3];
        if (r14 >= r15) goto L12;
        if (r12 != 0) goto L12;
        r4 = r3;
        r3 = r4;
    L12:
        float r32 = r3 * r17;
        float r42 = r4 * r17;
        float r52 = r5 * r17;
        r13.setBounds((int) (AndroidUtilities.dp(r32) - (AndroidUtilities.dp(r52) / 2.0f)), (int) (AndroidUtilities.dp(r42) - (AndroidUtilities.dp(r52) / 2.0f)), (int) (AndroidUtilities.dp(r32) + (AndroidUtilities.dp(r52) / 2.0f)), (int) (AndroidUtilities.dp(r42) + (AndroidUtilities.dp(r52) / 2.0f)));
        r13.setAlpha((int) Utilities.clamp((r16 * 255.0f) * r22, 255.0f, 0.0f));
        r13.draw(r11);
        r1 = r1 + 4;
        goto L6
    L18:
        return;
    }

    public static void drawPattern(Canvas r7, Drawable r8, float r9, float r10, float r11, float r12) {
        drawPattern(r7, 0, r8, r9, r10, r11, r12);
    }

    public static void drawPatternBatch(Canvas r11, int r12, Paint r13, Bitmap r14, float r15, float r16, float r17, float r18) {
        if (r17 > 0.0f) goto L5;
        return;
    L5:
        batchBuffer.fillParticleTextureCords(0.0f, 0.0f, r14.getWidth(), r14.getHeight());
        int r142 = 0;
    L6:
        float[] r0 = patternLocations[r12];
        if (r142 >= r0.length) goto L13;
        float r1 = r0[r142];
        float r2 = r0[r142 + 1];
        float r3 = r0[r142 + 2];
        float r02 = r0[r142 + 3];
        if (r15 >= r16) goto L12;
        if (r12 != 0) goto L12;
        r2 = r1;
        r1 = r2;
    L12:
        float r19 = r1 * r18;
        float r22 = r2 * r18;
        float r32 = r3 * r18;
        BatchParticlesDrawHelper.BatchParticlesBuffer r4 = batchBuffer;
        int r5 = r142 / 4;
        r4.setParticleVertexCords(r5, AndroidUtilities.dp(r19) - (AndroidUtilities.dp(r32) / 2.0f), AndroidUtilities.dp(r22) - (AndroidUtilities.dp(r32) / 2.0f), AndroidUtilities.dp(r19) + (AndroidUtilities.dp(r32) / 2.0f), AndroidUtilities.dp(r22) + (AndroidUtilities.dp(r32) / 2.0f));
        r4.setParticleColor(r5, ColorUtils.setAlphaComponent(-1, (int) ((255.0f * r17) * r02)));
        r142 = r142 + 4;
        goto L6
    L13:
        BatchParticlesDrawHelper.draw(r11, batchBuffer, r0.length / 4, r13);
    }

    public static void drawProfileAnimatedPattern(Canvas r38, Drawable r39, int r40, float r41, float r42, RectF r43, float r44) {
        if (r42 > 0.0f) goto L6;
        return;
    L6:
        if (r42 < 0.85f) goto L8;
        float r3 = 1.0f;
    L9:
        float r32 = Utilities.clamp01((r3 - 0.2f) / 0.8f);
        float r4 = r43.left;
        float r6 = r43.top;
        float r7 = r43.width();
        float r1 = r43.height();
        float r9 = (r7 / 2.0f) + r4;
        float r10 = (r1 / 2.0f) + r6;
        float r11 = AndroidUtilities.dpf2(96.0f);
        float r45 = Math.min(r4, (r40 - r11) / 2.0f);
        float r62 = Math.max(r6, (r41 - r11) / 2.0f);
        float r72 = Math.max(r7, r11);
        float r12 = Math.max(r1, r11);
        float r112 = r72 / 2.0f;
        float r122 = r45 + r112;
        float r13 = r12 / 2.0f;
        float r14 = r62 + r13;
        float r16 = AndroidUtilities.dpf2(24.0f);
        float r17 = AndroidUtilities.dpf2(16.0f);
        float r19 = AndroidUtilities.dpf2(12.0f);
        float r21 = AndroidUtilities.dpf2(8.0f);
        float r23 = AndroidUtilities.dpf2(4.0f);
        float r24 = r16 * 2.0f;
        float r25 = r24 * 2.0f;
        float r113 = (r24 + r112) * ((float) Math.cos(Math.toRadians(120.0d)));
        float r132 = (r17 + r13) * ((float) Math.cos(Math.toRadians(160.0d)));
        float r63 = r62 - r16;
        float r5 = r62 + r12;
        float r26 = r45 - r17;
        float r22 = r12 / 4.0f;
        float r27 = (r14 - r22) - r21;
        float r29 = r45 + r72;
        float r172 = r29 + r17;
        float r222 = (r14 + r22) + r21;
        float r30 = r45 - r24;
        float r31 = r29 + r24;
        float r322 = r122 + r113;
        float r33 = (r62 - r24) + r19;
        float r114 = r122 - r113;
        float r242 = (r5 + r24) - r19;
        float r192 = r30 - r21;
        float r34 = r14 + r132;
        float r212 = r31 + r21;
        float r133 = r14 - r132;
        float r46 = r45 - r25;
        float r292 = r29 + r25;
        int r8 = 54;
        int r252 = 0;
        int r2 = 18;
        int r64 = 19;
        float[] r15 = {r122, r63, 20.0f, r122, r5 + r16, 20.0f, r26, r27, 23.0f, r172, r27, 18.0f, r26, r222, 24.0f, r172 - r23, r222, 24.0f, r30, r14, 19.0f, r31, r14, 19.0f, r322, r33, 17.0f, r114, r33, 17.0f, r322, r242, 20.0f, r114, r242, 20.0f, r192, r34, 20.0f, r212, r34, 19.0f, r192, r133, 21.0f, r212, r133, 18.0f, r46, r14, 19.0f, r292, r14, 19.0f};
        float[] r47 = {0.02f, 0.42f, 0.0f, 0.32f, 0.0f, 0.4f, 0.0f, 0.4f, 0.0f, 0.4f, 0.0f, 0.4f, 0.14f, 0.6f, 0.16f, 0.64f, 0.14f, 0.7f, 0.14f, 0.9f, 0.2f, 0.75f, 0.2f, 0.85f, 0.09f, 0.45f, 0.09f, 0.45f, 0.09f, 0.45f, 0.11f, 0.45f, 0.14f, 0.75f, 0.2f, 0.8f};
        int r115 = 0;
    L10:
        if (r115 >= r8) goto L37;
        float r134 = r15[r115];
        float r193 = r15[r115 + 1];
        float r82 = AndroidUtilities.dpf2(r15[r115 + 2]) * 0.5f;
        float r213 = r47[r252];
        float r243 = r47[r252 + 1];
        float r262 = 1.0f - r32;
        if (r262 >= r213) goto L14;
        float r244 = r12;
        float r18 = 1.0f;
    L15:
        if (r115 == r2) goto L21;
        if (r115 == r64) goto L21;
        if (r115 == 6) goto L21;
        if (r115 == 7) goto L21;
    L22:
        float r28 = r193 - (AndroidUtilities.dp(12.0f) * (1.0f - r42));
        if (r18 >= 1.0f) goto L26;
        r134 = AndroidUtilities.lerp(r9, r134, CubicBezierInterpolator.EASE_IN.getInterpolation(r18));
        r28 = AndroidUtilities.lerp(r10, r28, r18);
        r82 = AndroidUtilities.lerp(AndroidUtilities.dpf2(8.0f), r82, r18);
    L26:
        if (r28 <= (AndroidUtilities.dp(8.0f) + r5)) goto L28;
        float r272 = r32;
        float r35 = 1.0f - Utilities.clamp01((((r28 - r62) - r244) - AndroidUtilities.dp(8.0f)) / AndroidUtilities.dp(56.0f));
    L29:
        float r65 = (((1.0f - Utilities.clamp01(MathUtils.distance(r122, r14, r134, r28) / (r72 * 2.0f))) * r44) * 0.5f) * r35;
        if (r18 >= 1.0f) goto L32;
        r65 = AndroidUtilities.lerp(0.0f, r65, r18);
    L32:
        r39.setBounds((int) (r134 - r82), (int) (r28 - r82), (int) (r134 + r82), (int) (r28 + r82));
        r39.setAlpha((int) (r65 * 255.0f));
        r39.draw(r38);
        r115 = r115 + 3;
        r252 = r252 + 2;
        r12 = r244;
        r32 = r272;
        r2 = 18;
        r64 = 19;
        r8 = 54;
        goto L10
    L28:
        r272 = r32;
        r35 = 1.0f;
    L21:
        r18 = CubicBezierInterpolator.EASE_IN.getInterpolation(r18);
        goto L22
    L14:
        float r214 = 1.0f - Utilities.clamp01((r262 - r213) / (r243 - r213));
        r244 = r12;
        r18 = r214;
        goto L15
    L37:
        return;
    L8:
        r3 = r42 / 0.85f;
        goto L9
    }

    public static void drawProfileAnimatedPattern(Canvas r6, Drawable r7, int r8, float r9, float r10, View r11, float r12) {
        RectF r112 = AndroidUtilities.rectTmp;
        r112.set(r11.getX(), r11.getY(), r11.getX() + (r11.getWidth() * r11.getScaleX()), r11.getY() + (r11.getHeight() * r11.getScaleY()));
        drawProfileAnimatedPattern(r6, r7, r8, r9, r10, r112, r12);
    }
}
