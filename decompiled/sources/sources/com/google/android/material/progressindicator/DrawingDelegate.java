package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import java.util.Arrays;

/* loaded from: classes5.dex */
abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    static final float WAVE_SMOOTHNESS = 0.48f;
    final PathMeasure activePathMeasure;
    final Path cachedActivePath;
    final Path displayedActivePath;
    S spec;
    final Matrix transform;

    public static class ActiveIndicator {
        float amplitudeFraction;
        int color;
        float endFraction;
        int gapSize;
        boolean isDeterminate;
        float phaseFraction;
        float rotationDegree;
        float startFraction;

        public ActiveIndicator() {
            this.amplitudeFraction = 1.0f;
        }
    }

    public class PathPoint {
        float[] posVec;
        float[] tanVec;
        final /* synthetic */ DrawingDelegate this$0;
        final Matrix transform;

        public PathPoint(DrawingDelegate r3) {
            this.this$0 = r3;
            this.posVec = new float[2];
            this.tanVec = new float[]{1.0f, 0.0f};
            this.transform = new Matrix();
        }

        public PathPoint(DrawingDelegate r2, DrawingDelegate<S>.PathPoint r3) {
            this(r2, r3.posVec, r3.tanVec);
        }

        public PathPoint(DrawingDelegate r3, float[] r4, float[] r5) {
            this.this$0 = r3;
            float[] r0 = new float[2];
            this.posVec = r0;
            this.tanVec = new float[2];
            System.arraycopy(r4, 0, r0, 0, 2);
            System.arraycopy(r5, 0, this.tanVec, 0, 2);
            this.transform = new Matrix();
        }

        public float distance(DrawingDelegate<S>.PathPoint r4) {
            float r1 = r4.posVec[0];
            float[] r3 = this.posVec;
            return (float) Math.hypot(r1 - r3[0], r4[1] - r3[1]);
        }

        public void moveAcross(float r14) {
            float[] r0 = this.tanVec;
            float r02 = (float) (Math.atan2(r0[1], r0[0]) + 1.5707963267948966d);
            double r7 = r14;
            double r9 = r02;
            this.posVec[0] = (float) (r2[0] + (Math.cos(r9) * r7));
            this.posVec[1] = (float) (r13[1] + (r7 * Math.sin(r9)));
        }

        public void moveAlong(float r14) {
            float[] r0 = this.tanVec;
            float r02 = (float) Math.atan2(r0[1], r0[0]);
            double r7 = r14;
            double r9 = r02;
            this.posVec[0] = (float) (r2[0] + (Math.cos(r9) * r7));
            this.posVec[1] = (float) (r13[1] + (r7 * Math.sin(r9)));
        }

        public void reset() {
            Arrays.fill(this.posVec, 0.0f);
            Arrays.fill(this.tanVec, 0.0f);
            this.tanVec[0] = 1.0f;
            this.transform.reset();
        }

        public void rotate(float r2) {
            this.transform.reset();
            this.transform.setRotate(r2);
            this.transform.mapPoints(this.posVec);
            this.transform.mapPoints(this.tanVec);
        }

        public void scale(float r5, float r6) {
            float[] r0 = this.posVec;
            r0[0] = r0[0] * r5;
            r0[1] = r0[1] * r6;
            float[] r4 = this.tanVec;
            r4[0] = r4[0] * r5;
            r4[1] = r4[1] * r6;
        }

        public void translate(float r3, float r4) {
            float[] r2 = this.posVec;
            r2[0] = r2[0] + r3;
            r2[1] = r2[1] + r4;
        }
    }

    public DrawingDelegate(S r4) {
        Path r0 = new Path();
        this.cachedActivePath = r0;
        this.displayedActivePath = new Path();
        this.activePathMeasure = new PathMeasure(r0, false);
        this.spec = r4;
        this.transform = new Matrix();
    }

    public abstract void adjustCanvas(Canvas r1, Rect r2, float r3, boolean r4, boolean r5);

    public abstract void drawStopIndicator(Canvas r1, Paint r2, int r3, int r4);

    public abstract void fillIndicator(Canvas r1, Paint r2, ActiveIndicator r3, int r4);

    public abstract void fillTrack(Canvas r1, Paint r2, float r3, float r4, int r5, int r6, int r7);

    public abstract int getPreferredHeight();

    public abstract int getPreferredWidth();

    public abstract void invalidateCachedPaths();

    public void validateSpecAndAdjustCanvas(Canvas r2, Rect r3, float r4, boolean r5, boolean r6) {
        this.spec.validateSpec();
        adjustCanvas(r2, r3, r4, r5, r6);
    }

    public float vectorToCanvasRotation(float[] r3) {
        return (float) Math.toDegrees(Math.atan2(r3[1], r3[0]));
    }
}
