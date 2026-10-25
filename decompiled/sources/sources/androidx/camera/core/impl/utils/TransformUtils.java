package androidx.camera.core.impl.utils;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import android.util.SizeF;
import androidx.core.util.Preconditions;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public abstract class TransformUtils {
    public static final RectF NORMALIZED_RECT = null;

    static {
        NORMALIZED_RECT = new RectF(-1.0f, -1.0f, 1.0f, 1.0f);
    }

    public static float calculateSignedAngle(float r4, float r5, float r6, float r7) {
        float r0 = (r4 * r6) + (r5 * r7);
        float r1 = (r4 * r7) - (r5 * r6);
        double r42 = Math.sqrt((r4 * r4) + (r5 * r5)) * Math.sqrt((r6 * r6) + (r7 * r7));
        return (float) Math.toDegrees(Math.atan2(r1 / r42, r0 / r42));
    }

    public static Matrix getNormalizedToBuffer(RectF r3) {
        Matrix r0 = new Matrix();
        r0.setRectToRect(NORMALIZED_RECT, r3, Matrix.ScaleToFit.FILL);
        return r0;
    }

    public static Matrix getRectToRect(RectF r1, RectF r2, int r3) {
        return getRectToRect(r1, r2, r3, false);
    }

    public static Matrix getRectToRect(RectF r3, RectF r4, int r5, boolean r6) {
        Matrix r0 = new Matrix();
        r0.setRectToRect(r3, NORMALIZED_RECT, Matrix.ScaleToFit.FILL);
        r0.postRotate(r5);
        if (r6 == false) goto L5;
        r0.postScale(-1.0f, 1.0f);
    L5:
        r0.postConcat(getNormalizedToBuffer(r4));
        return r0;
    }

    public static Size getRotatedSize(Rect r0, int r1) {
        return rotateSize(rectToSize(r0), r1);
    }

    public static int getRotationDegrees(Matrix r4) {
        float[] r0 = new float[9];
        r4.getValues(r0);
        return within360((int) Math.round(Math.atan2(r0[3], r0[0]) * 57.29577951308232d));
    }

    public static boolean is90or270(int r1) {
        if (r1 != 90) goto L5;
        return true;
    L5:
        if (r1 == 270) goto L18;
        if (r1 != 0) goto L9;
        return false;
    L9:
        if (r1 == 180) goto L17;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Invalid rotation degrees: ", r1);
        return false;
    L17:
        return false;
    L18:
        return true;
    }

    public static boolean isAspectRatioMatchingWithRoundingError(Size r1, Size r2) {
        return isAspectRatioMatchingWithRoundingError(r1, false, r2, false);
    }

    public static boolean isAspectRatioMatchingWithRoundingError(Size r3, boolean r4, Size r5, boolean r6) {
        if (r4 == false) goto L5;
        float r42 = r3.getWidth() / r3.getHeight();
        float r32 = r42;
    L6:
        if (r6 == false) goto L8;
        float r62 = r5.getWidth() / r5.getHeight();
        float r52 = r62;
    L10:
        if (r42 >= r62) goto L12;
        return false;
    L12:
        if (r52 < r32) goto L17;
        return true;
    L17:
        return false;
    L8:
        r62 = (r5.getWidth() - 1.0f) / (r5.getHeight() + 1.0f);
        r52 = (r5.getWidth() + 1.0f) / (r5.getHeight() - 1.0f);
        goto L10
    L5:
        r42 = (r3.getWidth() + 1.0f) / (r3.getHeight() - 1.0f);
        r32 = (r3.getWidth() - 1.0f) / (r3.getHeight() + 1.0f);
        goto L6
    }

    public static boolean isMirrored(Matrix r6) {
        float[] r0 = {0.0f, 1.0f, 1.0f, 0.0f};
        r6.mapVectors(r0);
        if (calculateSignedAngle(r0[0], r0[1], r0[2], r0[3]) <= 0.0f) goto L5;
        return true;
    L5:
        return false;
    }

    public static Size rectToSize(Rect r2) {
        return new Size(r2.width(), r2.height());
    }

    public static Size reverseSize(Size r2) {
        return new Size(r2.getHeight(), r2.getWidth());
    }

    public static SizeF reverseSizeF(SizeF r2) {
        return new SizeF(r2.getHeight(), r2.getWidth());
    }

    public static Size rotateSize(Size r3, int r4) {
        if ((r4 % 90) != 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "Invalid rotation degrees: " + r4);
        if (is90or270(within360(r4)) == true) goto L9;
        return r3;
    L9:
        return reverseSize(r3);
    L5:
        r0 = false;
        goto L6
    }

    public static Rect sizeToRect(Size r1) {
        return sizeToRect(r1, 0, 0);
    }

    public static Rect sizeToRect(Size r2, int r3, int r4) {
        return new Rect(r3, r4, r2.getWidth() + r3, r2.getHeight() + r4);
    }

    public static RectF sizeToRectF(Size r1) {
        return sizeToRectF(r1, 0, 0);
    }

    public static RectF sizeToRectF(Size r4, int r5, int r6) {
        return new RectF(r5, r6, r5 + r4.getWidth(), r6 + r4.getHeight());
    }

    public static Matrix updateSensorToBufferTransform(Matrix r1, Rect r2) {
        Matrix r0 = new Matrix(r1);
        r0.postTranslate(-r2.left, -r2.top);
        return r0;
    }

    public static int within360(int r0) {
        return ((r0 % 360) + 360) % 360;
    }
}
