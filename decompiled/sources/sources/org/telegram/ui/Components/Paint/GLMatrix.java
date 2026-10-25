package org.telegram.ui.Components.Paint;

import android.graphics.Matrix;

/* loaded from: classes7.dex */
public abstract class GLMatrix {
    public static float[] LoadGraphicsMatrix(Matrix r13) {
        float[] r1 = new float[9];
        r13.getValues(r1);
        return new float[]{r1[0], r1[1], 0.0f, 0.0f, r1[3], r1[4], 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, r1[2], r1[5], 0.0f, 1.0f};
    }

    public static float[] LoadOrtho(float r3, float r4, float r5, float r6, float r7, float r8) {
        float r0 = r4 - r3;
        float r1 = r6 - r5;
        float r2 = r8 - r7;
        float r32 = (-(r4 + r3)) / r0;
        float r42 = (-(r6 + r5)) / r1;
        float r52 = (-(r8 + r7)) / r2;
        return new float[]{2.0f / r0, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f / r1, 0.0f, 0.0f, 0.0f, 0.0f, (-2.0f) / r2, 0.0f, r32, r42, r52, 1.0f};
    }

    public static float[] MultiplyMat4f(float[] r48, float[] r49) {
        float r1 = r48[0];
        float r2 = r49[0];
        float r5 = r48[4];
        float r7 = r49[1];
        float r9 = r48[8];
        float r11 = r49[2];
        float r13 = r48[12];
        float r15 = r49[3];
        float r3 = (((r1 * r2) + (r5 * r7)) + (r9 * r11)) + (r13 * r15);
        float r16 = r48[1];
        float r19 = r48[5];
        float r21 = r48[9];
        float r23 = r48[13];
        float r17 = (((r16 * r2) + (r19 * r7)) + (r21 * r11)) + (r23 * r15);
        float r24 = r48[2];
        float r27 = r48[6];
        float r29 = r48[10];
        float r31 = r48[14];
        float r25 = (((r24 * r2) + (r27 * r7)) + (r29 * r11)) + (r31 * r15);
        float r32 = r48[3];
        float r34 = r48[7];
        float r35 = r48[11];
        float r36 = r48[15];
        float r22 = (((r2 * r32) + (r7 * r34)) + (r11 * r35)) + (r15 * r36);
        float r152 = r49[4];
        float r38 = r49[5];
        float r39 = r49[6];
        float r40 = r49[7];
        float r37 = (((r1 * r152) + (r5 * r38)) + (r9 * r39)) + (r13 * r40);
        float r41 = (((r16 * r152) + (r19 * r38)) + (r21 * r39)) + (r23 * r40);
        float r42 = (((r24 * r152) + (r27 * r38)) + (r29 * r39)) + (r31 * r40);
        float r153 = (((r152 * r32) + (r38 * r34)) + (r39 * r35)) + (r40 * r36);
        float r382 = r49[8];
        float r402 = r49[9];
        float r43 = r49[10];
        float r44 = r49[11];
        float r392 = (((r1 * r382) + (r5 * r402)) + (r9 * r43)) + (r13 * r44);
        float r45 = (((r16 * r382) + (r19 * r402)) + (r21 * r43)) + (r23 * r44);
        float r46 = (((r24 * r382) + (r27 * r402)) + (r29 * r43)) + (r31 * r44);
        float r383 = (((r382 * r32) + (r402 * r34)) + (r43 * r35)) + (r44 * r36);
        float r403 = r49[12];
        float r432 = r49[13];
        float r12 = (r1 * r403) + (r5 * r432);
        float r52 = r49[14];
        float r14 = r12 + (r9 * r52);
        float r92 = r49[15];
        return new float[]{r3, r17, r25, r22, r37, r41, r42, r153, r392, r45, r46, r383, r14 + (r13 * r92), (((r16 * r403) + (r19 * r432)) + (r21 * r52)) + (r23 * r92), (((r24 * r403) + (r27 * r432)) + (r29 * r52)) + (r31 * r92), (((r32 * r403) + (r34 * r432)) + (r35 * r52)) + (r36 * r92)};
    }
}
