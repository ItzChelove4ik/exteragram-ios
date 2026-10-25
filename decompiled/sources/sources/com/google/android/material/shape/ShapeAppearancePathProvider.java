package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Looper;

/* loaded from: classes5.dex */
public class ShapeAppearancePathProvider {
    protected static final int BOTTOM_LEFT_CORNER_INDEX = 2;
    protected static final int BOTTOM_RIGHT_CORNER_INDEX = 1;
    protected static final int TOP_LEFT_CORNER_INDEX = 3;
    protected static final int TOP_RIGHT_CORNER_INDEX = 0;
    private final Path boundsPath;
    private final Path cornerPath;
    private final ShapePath[] cornerPaths;
    private final Matrix[] cornerTransforms;
    private boolean edgeIntersectionCheckEnabled;
    private final Path edgePath;
    private final Matrix[] edgeTransforms;
    private final Path overlappedEdgePath;
    private final PointF pointF;
    private final float[] scratch;
    private final float[] scratch2;
    private final ShapePath shapePath;

    public static class Lazy {
        static final ShapeAppearancePathProvider INSTANCE = null;

        static {
            INSTANCE = new ShapeAppearancePathProvider();
        }

        private Lazy() {
        }
    }

    public interface PathListener {
        void onCornerPathCreated(ShapePath r1, Matrix r2, int r3);

        void onEdgePathCreated(ShapePath r1, Matrix r2, int r3);
    }

    public static final class ShapeAppearancePathSpec {
        public final RectF bounds;
        public final float interpolation;
        public final Path path;
        public final PathListener pathListener;
        public final ShapeAppearanceModel shapeAppearanceModel;

        public ShapeAppearancePathSpec(ShapeAppearanceModel r1, float r2, RectF r3, PathListener r4, Path r5) {
            this.pathListener = r4;
            this.shapeAppearanceModel = r1;
            this.interpolation = r2;
            this.bounds = r3;
            this.path = r5;
        }
    }

    public ShapeAppearancePathProvider() {
        this.cornerPaths = new ShapePath[4];
        this.cornerTransforms = new Matrix[4];
        this.edgeTransforms = new Matrix[4];
        this.pointF = new PointF();
        this.overlappedEdgePath = new Path();
        this.boundsPath = new Path();
        this.shapePath = new ShapePath();
        this.scratch = new float[2];
        this.scratch2 = new float[2];
        this.edgePath = new Path();
        this.cornerPath = new Path();
        this.edgeIntersectionCheckEnabled = true;
        int r1 = 0;
    L3:
        if (r1 >= 4) goto L5;
        this.cornerPaths[r1] = new ShapePath();
        this.cornerTransforms[r1] = new Matrix();
        this.edgeTransforms[r1] = new Matrix();
        r1 = r1 + 1;
        goto L3
    }

    private float angleOfEdge(int r1) {
        return ((r1 + 1) % 4) * 90;
    }

    private void appendCornerPath(ShapeAppearancePathSpec r5, int r6) {
        this.scratch[0] = this.cornerPaths[r6].getStartX();
        this.scratch[1] = this.cornerPaths[r6].getStartY();
        this.cornerTransforms[r6].mapPoints(this.scratch);
        if (r6 != 0) goto L5;
        Path r0 = r5.path;
        float[] r1 = this.scratch;
        r0.moveTo(r1[0], r1[1]);
    L6:
        this.cornerPaths[r6].applyToPath(this.cornerTransforms[r6], r5.path);
        PathListener r52 = r5.pathListener;
        if (r52 == null) goto L10;
        r52.onCornerPathCreated(this.cornerPaths[r6], this.cornerTransforms[r6], r6);
        return;
    L10:
        return;
    L5:
        Path r02 = r5.path;
        float[] r12 = this.scratch;
        r02.lineTo(r12[0], r12[1]);
        goto L6
    }

    private void appendEdgePath(ShapeAppearancePathSpec r9, int r10) {
        int r0 = (r10 + 1) % 4;
        this.scratch[0] = this.cornerPaths[r10].getEndX();
        this.scratch[1] = this.cornerPaths[r10].getEndY();
        this.cornerTransforms[r10].mapPoints(this.scratch);
        this.scratch2[0] = this.cornerPaths[r0].getStartX();
        this.scratch2[1] = this.cornerPaths[r0].getStartY();
        this.cornerTransforms[r0].mapPoints(this.scratch2);
        float r2 = this.scratch[0];
        float[] r5 = this.scratch2;
        float r1 = Math.max(((float) Math.hypot(r2 - r5[0], r1[1] - r5[1])) - 0.001f, 0.0f);
        float r52 = getEdgeCenterForIndex(r9.bounds, r10);
        this.shapePath.reset(0.0f, 0.0f);
        EdgeTreatment r22 = getEdgeTreatmentForIndex(r10, r9.shapeAppearanceModel);
        r22.getEdgePath(r1, r52, r9.interpolation, this.shapePath);
        this.edgePath.reset();
        this.shapePath.applyToPath(this.edgeTransforms[r10], this.edgePath);
        if (this.edgeIntersectionCheckEnabled == true) goto L5;
    L11:
        this.shapePath.applyToPath(this.edgeTransforms[r10], r9.path);
    L12:
        PathListener r92 = r9.pathListener;
        if (r92 == null) goto L16;
        r92.onEdgePathCreated(this.shapePath, this.edgeTransforms[r10], r10);
        return;
    L16:
        return;
    L5:
        if (r22.forceIntersection() == false) goto L7;
    L10:
        Path r02 = this.edgePath;
        r02.op(r02, this.boundsPath, Path.Op.DIFFERENCE);
        this.scratch[0] = this.shapePath.getStartX();
        this.scratch[1] = this.shapePath.getStartY();
        this.edgeTransforms[r10].mapPoints(this.scratch);
        Path r03 = this.overlappedEdgePath;
        float[] r12 = this.scratch;
        r03.moveTo(r12[0], r12[1]);
        this.shapePath.applyToPath(this.edgeTransforms[r10], this.overlappedEdgePath);
        goto L12
    L7:
        if (pathOverlapsCorner(this.edgePath, r10) == true) goto L10;
        if (pathOverlapsCorner(this.edgePath, r0) == false) goto L11;
        goto L10
    }

    private void getCoordinatesOfCorner(int r1, RectF r2, PointF r3) {
        if (r1 != 1) goto L5;
        r3.set(r2.right, r2.bottom);
        return;
    L5:
        if (r1 != 2) goto L7;
        r3.set(r2.left, r2.bottom);
        return;
    L7:
        if (r1 == 3) goto L10;
        r3.set(r2.right, r2.top);
        return;
    L10:
        r3.set(r2.left, r2.top);
    }

    private CornerTreatment getCornerTreatmentForIndex(int r1, ShapeAppearanceModel r2) {
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L11;
        return r2.getTopRightCorner();
    L11:
        return r2.getTopLeftCorner();
    L13:
        return r2.getBottomLeftCorner();
    L15:
        return r2.getBottomRightCorner();
    }

    private float getEdgeCenterForIndex(RectF r5, int r6) {
        float[] r0 = this.scratch;
        ShapePath r1 = this.cornerPaths[r6];
        r0[0] = r1.endX;
        r0[1] = r1.endY;
        this.cornerTransforms[r6].mapPoints(r0);
        if (r6 == 1) goto L9;
        if (r6 == 3) goto L9;
        return Math.abs(r5.centerY() - this.scratch[1]);
    L9:
        return Math.abs(r5.centerX() - this.scratch[0]);
    }

    private EdgeTreatment getEdgeTreatmentForIndex(int r1, ShapeAppearanceModel r2) {
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L11;
        return r2.getRightEdge();
    L11:
        return r2.getTopEdge();
    L13:
        return r2.getLeftEdge();
    L15:
        return r2.getBottomEdge();
    }

    public static ShapeAppearancePathProvider getInstance() {
        return Lazy.INSTANCE;
    }

    public static ShapeAppearancePathProvider getInstanceOrCreate() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) goto L7;
        return getInstance();
    L7:
        return new ShapeAppearancePathProvider();
    }

    private boolean pathOverlapsCorner(Path r3, int r4) {
        this.cornerPath.reset();
        this.cornerPaths[r4].applyToPath(this.cornerTransforms[r4], this.cornerPath);
        RectF r42 = new RectF();
        r3.computeBounds(r42, true);
        this.cornerPath.computeBounds(r42, true);
        r3.op(this.cornerPath, Path.Op.INTERSECT);
        r3.computeBounds(r42, true);
        if (r42.isEmpty() == true) goto L5;
    L11:
        return true;
    L5:
        if (r42.width() > 1.0f) goto L7;
        return false;
    L7:
        if (r42.height() > 1.0f) goto L11;
        return false;
    }

    private void setCornerPathAndTransform(ShapeAppearancePathSpec r8, int r9, float[] r10) {
        if (r10 != null) goto L4;
        CornerSize r6 = getCornerSizeForIndex(r9, r8.shapeAppearanceModel);
    L5:
        getCornerTreatmentForIndex(r9, r8.shapeAppearanceModel).getCornerPath(this.cornerPaths[r9], 90.0f, r8.interpolation, r8.bounds, r6);
        float r102 = angleOfEdge(r9);
        this.cornerTransforms[r9].reset();
        getCoordinatesOfCorner(r9, r8.bounds, this.pointF);
        Matrix r82 = this.cornerTransforms[r9];
        PointF r0 = this.pointF;
        r82.setTranslate(r0.x, r0.y);
        this.cornerTransforms[r9].preRotate(r102);
        return;
    L4:
        r6 = new ClampedCornerSize(r10[r9]);
        goto L5
    }

    private void setEdgePathAndTransform(int r6) {
        this.scratch[0] = this.cornerPaths[r6].getEndX();
        this.scratch[1] = this.cornerPaths[r6].getEndY();
        this.cornerTransforms[r6].mapPoints(this.scratch);
        float r0 = angleOfEdge(r6);
        this.edgeTransforms[r6].reset();
        Matrix r1 = this.edgeTransforms[r6];
        float[] r4 = this.scratch;
        r1.setTranslate(r4[0], r4[1]);
        this.edgeTransforms[r6].preRotate(r0);
    }

    public void calculatePath(ShapeAppearanceModel r7, float r8, RectF r9, Path r10) {
        calculatePath(r7, r8, r9, null, r10);
    }

    public void calculatePath(ShapeAppearanceModel r8, float r9, RectF r10, PathListener r11, Path r12) {
        calculatePath(r8, null, r9, r10, r11, r12);
    }

    public void calculatePath(ShapeAppearanceModel r9, float[] r10, float r11, RectF r12, PathListener r13, Path r14) {
        r14.rewind();
        this.overlappedEdgePath.rewind();
        this.boundsPath.rewind();
        this.boundsPath.addRect(r12, Path.Direction.CW);
        ShapeAppearancePathSpec r2 = new ShapeAppearancePathSpec(r9, r11, r12, r13, r14);
        int r92 = 0;
        int r112 = 0;
    L4:
        if (r112 >= 4) goto L6;
        setCornerPathAndTransform(r2, r112, r10);
        setEdgePathAndTransform(r112);
        r112 = r112 + 1;
    L6:
        if (r92 >= 4) goto L8;
        appendCornerPath(r2, r92);
        appendEdgePath(r2, r92);
        r92 = r92 + 1;
        goto L6
    L8:
        r14.close();
        this.overlappedEdgePath.close();
        if (this.overlappedEdgePath.isEmpty() == true) goto L14;
        r14.op(this.overlappedEdgePath, Path.Op.UNION);
        return;
    }

    public CornerSize getCornerSizeForIndex(int r1, ShapeAppearanceModel r2) {
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L11;
        return r2.getTopRightCornerSize();
    L11:
        return r2.getTopLeftCornerSize();
    L13:
        return r2.getBottomLeftCornerSize();
    L15:
        return r2.getBottomRightCornerSize();
    }

    public void setEdgeIntersectionCheckEnable(boolean r1) {
        this.edgeIntersectionCheckEnabled = r1;
    }
}
