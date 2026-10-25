package org.webrtc;

import android.graphics.Matrix;
import android.graphics.Point;
import android.view.View;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public class RendererCommon {
    private static float BALANCED_VISIBLE_FRACTION = 0.5625f;

    /* renamed from: org.webrtc.RendererCommon$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$webrtc$RendererCommon$ScalingType = null;

        static {
            int[] r0 = new int[ScalingType.values().length];
            $SwitchMap$org$webrtc$RendererCommon$ScalingType = r0;
            r0[ScalingType.SCALE_ASPECT_FIT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$org$webrtc$RendererCommon$ScalingType[ScalingType.SCALE_ASPECT_FILL.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$org$webrtc$RendererCommon$ScalingType[ScalingType.SCALE_ASPECT_BALANCED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public interface GlDrawer {
        void drawOes(int r1, int r2, int r3, int r4, int r5, float[] r6, int r7, int r8, int r9, int r10, int r11, int r12, boolean r13);

        void drawRgb(int r1, int r2, int r3, int r4, int r5, float[] r6, int r7, int r8, int r9, int r10, int r11, int r12, boolean r13);

        void drawYuv(int[] r1, int r2, int r3, int r4, int r5, float[] r6, int r7, int r8, int r9, int r10, int r11, int r12, boolean r13);

        void release();
    }

    public interface RendererEvents {
        void onFirstFrameRendered();

        void onFrameResolutionChanged(int r1, int r2, int r3);
    }

    public enum ScalingType extends Enum<ScalingType> {
        private static final /* synthetic */ ScalingType[] $VALUES = null;
        public static final ScalingType SCALE_ASPECT_BALANCED = null;
        public static final ScalingType SCALE_ASPECT_FILL = null;
        public static final ScalingType SCALE_ASPECT_FIT = null;

        private static /* synthetic */ ScalingType[] $values() {
            return new ScalingType[]{SCALE_ASPECT_FIT, SCALE_ASPECT_FILL, SCALE_ASPECT_BALANCED};
        }

        static {
            SCALE_ASPECT_FIT = new ScalingType("SCALE_ASPECT_FIT", 0);
            SCALE_ASPECT_FILL = new ScalingType("SCALE_ASPECT_FILL", 1);
            SCALE_ASPECT_BALANCED = new ScalingType("SCALE_ASPECT_BALANCED", 2);
            $VALUES = $values();
        }

        ScalingType(String r1, int r2) {
        }

        public static ScalingType valueOf(String r1) {
            return (ScalingType) Enum.valueOf(ScalingType.class, r1);
        }

        public static ScalingType[] values() {
            return (ScalingType[]) $VALUES.clone();
        }
    }

    public static class VideoLayoutMeasure {
        private float visibleFractionMatchOrientation;
        private float visibleFractionMismatchOrientation;

        public VideoLayoutMeasure() {
            ScalingType r0 = ScalingType.SCALE_ASPECT_BALANCED;
            this.visibleFractionMatchOrientation = RendererCommon.m22375$$Nest$smconvertScalingTypeToVisibleFraction(r0);
            this.visibleFractionMismatchOrientation = RendererCommon.m22375$$Nest$smconvertScalingTypeToVisibleFraction(r0);
        }

        public Point measure(boolean r8, int r9, int r10, int r11, int r12) {
            int r1 = View.getDefaultSize(Integer.MAX_VALUE, r9);
            int r0 = View.getDefaultSize(Integer.MAX_VALUE, r10);
            if (r11 == 0) goto L35;
            if (r12 == 0) goto L35;
            if (r1 == 0) goto L35;
            if (r0 == 0) goto L35;
            float r112 = r11 / r12;
            float r122 = r1 / r0;
            boolean r4 = false;
            if (r112 <= 1.0f) goto L11;
            boolean r6 = true;
        L13:
            if (r122 <= 1.0f) goto L15;
            boolean r2 = true;
        L16:
            if (r6 != r2) goto L18;
            float r7 = this.visibleFractionMatchOrientation;
        L19:
            Point r72 = RendererCommon.getDisplaySize(r7, r112, r1, r0);
            if (r8 == false) goto L22;
        L33:
            return r72;
        L22:
            if (View.MeasureSpec.getMode(r9) != 1073741824) goto L25;
            r72.x = r1;
        L25:
            if (View.MeasureSpec.getMode(r10) == 1073741824) goto L32;
            if (r112 <= 1.0f) goto L28;
            boolean r82 = true;
        L29:
            if (r122 <= 1.0f) goto L31;
            r4 = true;
        L31:
            if (r82 != r4) goto L33;
        L28:
            r82 = false;
        L32:
            r72.y = r0;
            goto L33
        L18:
            r7 = this.visibleFractionMismatchOrientation;
            goto L19
        L15:
            r2 = false;
            goto L16
        L11:
            r6 = false;
        L35:
            return new Point(r1, r0);
        }

        public void setScalingType(ScalingType r1) {
            setScalingType(r1, r1);
        }

        public void setScalingType(ScalingType r1, ScalingType r2) {
            this.visibleFractionMatchOrientation = RendererCommon.m22375$$Nest$smconvertScalingTypeToVisibleFraction(r1);
            this.visibleFractionMismatchOrientation = RendererCommon.m22375$$Nest$smconvertScalingTypeToVisibleFraction(r2);
        }

        public void setVisibleFraction(float r1, float r2) {
            this.visibleFractionMatchOrientation = r1;
            this.visibleFractionMismatchOrientation = r2;
        }
    }

    /* renamed from: -$$Nest$smconvertScalingTypeToVisibleFraction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m22375$$Nest$smconvertScalingTypeToVisibleFraction(ScalingType r0) {
        return convertScalingTypeToVisibleFraction(r0);
    }

    static {
    }

    public RendererCommon() {
    }

    private static void adjustOrigin(float[] r7) {
        float r1 = r7[12] - ((r7[0] + r7[4]) * 0.5f);
        r7[12] = r1;
        float r4 = r7[13] - ((r7[1] + r7[5]) * 0.5f);
        r7[13] = r4;
        r7[12] = r1 + 0.5f;
        r7[13] = r4 + 0.5f;
    }

    public static float[] convertMatrixFromAndroidGraphicsMatrix(Matrix r20) {
        float[] r1 = new float[9];
        r20.getValues(r1);
        return new float[]{r1[0], r1[3], 0.0f, r1[6], r1[1], r1[4], 0.0f, r1[7], 0.0f, 0.0f, 1.0f, 0.0f, r1[2], r1[5], 0.0f, r1[8]};
    }

    public static Matrix convertMatrixToAndroidGraphicsMatrix(float[] r15) {
        float[] r14 = {r15[0], r15[4], r15[12], r15[1], r15[5], r15[13], r15[3], r15[7], r15[15]};
        Matrix r152 = new Matrix();
        r152.setValues(r14);
        return r152;
    }

    private static float convertScalingTypeToVisibleFraction(ScalingType r1) {
        int r12 = AnonymousClass1.$SwitchMap$org$webrtc$RendererCommon$ScalingType[r1.ordinal()];
        if (r12 != 1) goto L5;
        return 1.0f;
    L5:
        if (r12 != 2) goto L7;
        return 0.0f;
    L7:
        if (r12 == 3) goto L9;
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0.0f;
    L9:
        return BALANCED_VISIBLE_FRACTION;
    }

    public static Point getDisplaySize(float r2, float r3, int r4, int r5) {
        if (r2 == 0.0f) goto L10;
        if (r3 == 0.0f) goto L10;
        return new Point(Math.min(r4, Math.round((r5 / r2) * r3)), Math.min(r5, Math.round((r4 / r2) / r3)));
    L10:
        return new Point(r4, r5);
    }

    public static Point getDisplaySize(ScalingType r0, float r1, int r2, int r3) {
        return getDisplaySize(convertScalingTypeToVisibleFraction(r0), r1, r2, r3);
    }

    public static float[] getLayoutMatrix(boolean r2, float r3, float r4) {
        if (r4 <= r3) goto L5;
        float r32 = r3 / r4;
        float r42 = 1.0f;
    L6:
        if (r2 == false) goto L8;
        r42 = r42 * (-1.0f);
    L8:
        float[] r22 = new float[16];
        android.opengl.Matrix.setIdentityM(r22, 0);
        android.opengl.Matrix.scaleM(r22, 0, r42, r32, 1.0f);
        adjustOrigin(r22);
        return r22;
    L5:
        r42 = r4 / r3;
        r32 = 1.0f;
        goto L6
    }
}
