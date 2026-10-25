package androidx.camera.core.impl.utils;

import android.opengl.Matrix;

/* loaded from: classes3.dex */
public abstract class MatrixExt {
    private static final float[] sTemp = null;

    static {
        sTemp = new float[16];
    }

    private static void denormalize(float[] r2, float r3, float r4) {
        Matrix.translateM(r2, 0, -r3, -r4, 0.0f);
    }

    private static void normalize(float[] r2, float r3, float r4) {
        Matrix.translateM(r2, 0, r3, r4, 0.0f);
    }

    public static void preRotate(float[] r6, float r7, float r8, float r9) {
        normalize(r6, r8, r9);
        Matrix.rotateM(r6, 0, r7, 0.0f, 0.0f, 1.0f);
        denormalize(r6, r8, r9);
    }

    public static void preVerticalFlip(float[] r4, float r5) {
        normalize(r4, 0.0f, r5);
        Matrix.scaleM(r4, 0, 1.0f, -1.0f, 1.0f);
        denormalize(r4, 0.0f, r5);
    }
}
