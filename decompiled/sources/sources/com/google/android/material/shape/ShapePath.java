package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ShapePath {
    protected static final float ANGLE_LEFT = 180.0f;
    private static final float ANGLE_UP = 270.0f;
    private boolean containsIncompatibleShadowOp;

    @Deprecated
    public float currentShadowAngle;

    @Deprecated
    public float endShadowAngle;

    @Deprecated
    public float endX;

    @Deprecated
    public float endY;
    private final List<PathOperation> operations;
    private final List<ShadowCompatOperation> shadowCompatOperations;

    @Deprecated
    public float startX;

    @Deprecated
    public float startY;

    /* renamed from: com.google.android.material.shape.ShapePath$1, reason: invalid class name */
    public class AnonymousClass1 extends ShadowCompatOperation {
        final /* synthetic */ ShapePath this$0;
        final /* synthetic */ List val$operations;
        final /* synthetic */ Matrix val$transformCopy;

        public AnonymousClass1(ShapePath r1, List r2, Matrix r3) {
            this.this$0 = r1;
            this.val$operations = r2;
            this.val$transformCopy = r3;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix r3, ShadowRenderer r4, int r5, Canvas r6) {
            Iterator r32 = this.val$operations.iterator();
        L4:
            if (r32.hasNext() == false) goto L6;
            ((ShadowCompatOperation) r32.next()).draw(this.val$transformCopy, r4, r5, r6);
            goto L4
        }
    }

    public static class ArcShadowOperation extends ShadowCompatOperation {
        private final PathArcOperation operation;

        public ArcShadowOperation(PathArcOperation r1) {
            this.operation = r1;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix r9, ShadowRenderer r10, int r11, Canvas r12) {
            float r6 = PathArcOperation.access$800(this.operation);
            float r7 = PathArcOperation.access$900(this.operation);
            r10.drawCornerShadow(r12, r9, new RectF(PathArcOperation.access$1000(this.operation), PathArcOperation.access$1100(this.operation), PathArcOperation.access$1200(this.operation), PathArcOperation.access$1300(this.operation)), r11, r6, r7);
        }
    }

    public static class InnerCornerShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation1;
        private final PathLineOperation operation2;
        private final float startX;
        private final float startY;

        public InnerCornerShadowOperation(PathLineOperation r1, PathLineOperation r2, float r3, float r4) {
            this.operation1 = r1;
            this.operation2 = r2;
            this.startX = r3;
            this.startY = r4;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix r18, ShadowRenderer r19, int r20, Canvas r21) {
            float r8 = getSweepAngle();
            if (r8 > 0.0f) goto L14;
            double r4 = Math.hypot(PathLineOperation.access$000(this.operation1) - this.startX, PathLineOperation.access$100(this.operation1) - this.startY);
            double r12 = Math.hypot(PathLineOperation.access$000(this.operation2) - PathLineOperation.access$000(this.operation1), PathLineOperation.access$100(this.operation2) - PathLineOperation.access$100(this.operation1));
            float r6 = (float) Math.min(r20, Math.min(r4, r12));
            double r14 = r6;
            double r9 = Math.tan(Math.toRadians((-r8) / 2.0f)) * r14;
            if (r4 <= r9) goto L8;
            RectF r7 = new RectF(0.0f, 0.0f, (float) (r4 - r9), 0.0f);
            this.renderMatrix.set(r18);
            this.renderMatrix.preTranslate(this.startX, this.startY);
            this.renderMatrix.preRotate(getStartAngle());
            int r11 = r20;
            r19.drawEdgeShadow(r21, this.renderMatrix, r7, r11);
        L9:
            float r42 = r6 * 2.0f;
            RectF r5 = new RectF(0.0f, 0.0f, r42, r42);
            this.renderMatrix.set(r18);
            this.renderMatrix.preTranslate(PathLineOperation.access$000(this.operation1), PathLineOperation.access$100(this.operation1));
            this.renderMatrix.preRotate(getStartAngle());
            this.renderMatrix.preTranslate((float) ((-r9) - r14), (-2.0f) * r6);
            r19.drawInnerCornerShadow(r21, this.renderMatrix, r5, (int) r6, 450.0f, r8, new float[]{(float) (r14 + r9), r42});
            if (r12 <= r9) goto L13;
            RectF r43 = new RectF(0.0f, 0.0f, (float) (r12 - r9), 0.0f);
            this.renderMatrix.set(r18);
            this.renderMatrix.preTranslate(PathLineOperation.access$000(this.operation1), PathLineOperation.access$100(this.operation1));
            this.renderMatrix.preRotate(getEndAngle());
            this.renderMatrix.preTranslate((float) r9, 0.0f);
            r19.drawEdgeShadow(r21, this.renderMatrix, r43, r11);
            return;
        L13:
            return;
        L8:
            r11 = r20;
            goto L9
        }

        public float getEndAngle() {
            return (float) Math.toDegrees(Math.atan((PathLineOperation.access$100(this.operation2) - PathLineOperation.access$100(this.operation1)) / (PathLineOperation.access$000(this.operation2) - PathLineOperation.access$000(this.operation1))));
        }

        public float getStartAngle() {
            return (float) Math.toDegrees(Math.atan((PathLineOperation.access$100(this.operation1) - this.startY) / (PathLineOperation.access$000(this.operation1) - this.startX)));
        }

        public float getSweepAngle() {
            float r0 = ((getEndAngle() - getStartAngle()) + 360.0f) % 360.0f;
            if (r0 > ShapePath.ANGLE_LEFT) goto L6;
            return r0;
        L6:
            return r0 - 360.0f;
        }
    }

    public static class LineShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation;
        private final float startX;
        private final float startY;

        public LineShadowOperation(PathLineOperation r1, float r2, float r3) {
            this.operation = r1;
            this.startX = r2;
            this.startY = r3;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix r6, ShadowRenderer r7, int r8, Canvas r9) {
            RectF r2 = new RectF(0.0f, 0.0f, (float) Math.hypot(PathLineOperation.access$100(this.operation) - this.startY, PathLineOperation.access$000(this.operation) - this.startX), 0.0f);
            this.renderMatrix.set(r6);
            this.renderMatrix.preTranslate(this.startX, this.startY);
            this.renderMatrix.preRotate(getAngle());
            r7.drawEdgeShadow(r9, this.renderMatrix, r2, r8);
        }

        public float getAngle() {
            return (float) Math.toDegrees(Math.atan((PathLineOperation.access$100(this.operation) - this.startY) / (PathLineOperation.access$000(this.operation) - this.startX)));
        }
    }

    public static class PathArcOperation extends PathOperation {
        private static final RectF rectF = null;

        @Deprecated
        public float bottom;

        @Deprecated
        public float left;

        @Deprecated
        public float right;

        @Deprecated
        public float startAngle;

        @Deprecated
        public float sweepAngle;

        @Deprecated
        public float top;

        static {
            rectF = new RectF();
        }

        public PathArcOperation(float r1, float r2, float r3, float r4) {
            setLeft(r1);
            setTop(r2);
            setRight(r3);
            setBottom(r4);
        }

        public static /* synthetic */ float access$1000(PathArcOperation r0) {
            return r0.getLeft();
        }

        public static /* synthetic */ float access$1100(PathArcOperation r0) {
            return r0.getTop();
        }

        public static /* synthetic */ float access$1200(PathArcOperation r0) {
            return r0.getRight();
        }

        public static /* synthetic */ float access$1300(PathArcOperation r0) {
            return r0.getBottom();
        }

        public static /* synthetic */ void access$600(PathArcOperation r0, float r1) {
            r0.setStartAngle(r1);
        }

        public static /* synthetic */ void access$700(PathArcOperation r0, float r1) {
            r0.setSweepAngle(r1);
        }

        public static /* synthetic */ float access$800(PathArcOperation r0) {
            return r0.getStartAngle();
        }

        public static /* synthetic */ float access$900(PathArcOperation r0) {
            return r0.getSweepAngle();
        }

        private float getBottom() {
            return this.bottom;
        }

        private float getLeft() {
            return this.left;
        }

        private float getRight() {
            return this.right;
        }

        private float getStartAngle() {
            return this.startAngle;
        }

        private float getSweepAngle() {
            return this.sweepAngle;
        }

        private float getTop() {
            return this.top;
        }

        private void setBottom(float r1) {
            this.bottom = r1;
        }

        private void setLeft(float r1) {
            this.left = r1;
        }

        private void setRight(float r1) {
            this.right = r1;
        }

        private void setStartAngle(float r1) {
            this.startAngle = r1;
        }

        private void setSweepAngle(float r1) {
            this.sweepAngle = r1;
        }

        private void setTop(float r1) {
            this.top = r1;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix r6, Path r7) {
            Matrix r0 = this.matrix;
            r6.invert(r0);
            r7.transform(r0);
            RectF r02 = rectF;
            r02.set(getLeft(), getTop(), getRight(), getBottom());
            r7.arcTo(r02, getStartAngle(), getSweepAngle(), false);
            r7.transform(r6);
        }
    }

    public static class PathCubicOperation extends PathOperation {
        private float controlX1;
        private float controlX2;
        private float controlY1;
        private float controlY2;
        private float endX;
        private float endY;

        public PathCubicOperation(float r1, float r2, float r3, float r4, float r5, float r6) {
            setControlX1(r1);
            setControlY1(r2);
            setControlX2(r3);
            setControlY2(r4);
            setEndX(r5);
            setEndY(r6);
        }

        private float getControlX1() {
            return this.controlX1;
        }

        private float getControlX2() {
            return this.controlX2;
        }

        private float getControlY1() {
            return this.controlY1;
        }

        private float getControlY2() {
            return this.controlY1;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        private void setControlX1(float r1) {
            this.controlX1 = r1;
        }

        private void setControlX2(float r1) {
            this.controlX2 = r1;
        }

        private void setControlY1(float r1) {
            this.controlY1 = r1;
        }

        private void setControlY2(float r1) {
            this.controlY2 = r1;
        }

        private void setEndX(float r1) {
            this.endX = r1;
        }

        private void setEndY(float r1) {
            this.endY = r1;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix r9, Path r10) {
            Matrix r0 = this.matrix;
            r9.invert(r0);
            r10.transform(r0);
            r10.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX, this.endY);
            r10.transform(r9);
        }
    }

    public static class PathLineOperation extends PathOperation {
        private float x;
        private float y;

        public PathLineOperation() {
        }

        public static /* synthetic */ float access$000(PathLineOperation r0) {
            return r0.x;
        }

        public static /* synthetic */ float access$002(PathLineOperation r0, float r1) {
            r0.x = r1;
            return r1;
        }

        public static /* synthetic */ float access$100(PathLineOperation r0) {
            return r0.y;
        }

        public static /* synthetic */ float access$102(PathLineOperation r0, float r1) {
            r0.y = r1;
            return r1;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix r2, Path r3) {
            Matrix r0 = this.matrix;
            r2.invert(r0);
            r3.transform(r0);
            r3.lineTo(this.x, this.y);
            r3.transform(r2);
        }
    }

    public static abstract class PathOperation {
        protected final Matrix matrix;

        public PathOperation() {
            this.matrix = new Matrix();
        }

        public abstract void applyToPath(Matrix r1, Path r2);
    }

    public static class PathQuadOperation extends PathOperation {

        @Deprecated
        public float controlX;

        @Deprecated
        public float controlY;

        @Deprecated
        public float endX;

        @Deprecated
        public float endY;

        public PathQuadOperation() {
        }

        public static /* synthetic */ void access$200(PathQuadOperation r0, float r1) {
            r0.setControlX(r1);
        }

        public static /* synthetic */ void access$300(PathQuadOperation r0, float r1) {
            r0.setControlY(r1);
        }

        public static /* synthetic */ void access$400(PathQuadOperation r0, float r1) {
            r0.setEndX(r1);
        }

        public static /* synthetic */ void access$500(PathQuadOperation r0, float r1) {
            r0.setEndY(r1);
        }

        private float getControlX() {
            return this.controlX;
        }

        private float getControlY() {
            return this.controlY;
        }

        private float getEndX() {
            return this.endX;
        }

        private float getEndY() {
            return this.endY;
        }

        private void setControlX(float r1) {
            this.controlX = r1;
        }

        private void setControlY(float r1) {
            this.controlY = r1;
        }

        private void setEndX(float r1) {
            this.endX = r1;
        }

        private void setEndY(float r1) {
            this.endY = r1;
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(Matrix r4, Path r5) {
            Matrix r0 = this.matrix;
            r4.invert(r0);
            r5.transform(r0);
            r5.quadTo(getControlX(), getControlY(), getEndX(), getEndY());
            r5.transform(r4);
        }
    }

    public static abstract class ShadowCompatOperation {
        static final Matrix IDENTITY_MATRIX = null;
        final Matrix renderMatrix;

        static {
            IDENTITY_MATRIX = new Matrix();
        }

        public ShadowCompatOperation() {
            this.renderMatrix = new Matrix();
        }

        public abstract void draw(Matrix r1, ShadowRenderer r2, int r3, Canvas r4);

        public final void draw(ShadowRenderer r2, int r3, Canvas r4) {
            draw(IDENTITY_MATRIX, r2, r3, r4);
        }
    }

    public ShapePath() {
        this.operations = new ArrayList();
        this.shadowCompatOperations = new ArrayList();
        reset(0.0f, 0.0f);
    }

    public ShapePath(float r2, float r3) {
        this.operations = new ArrayList();
        this.shadowCompatOperations = new ArrayList();
        reset(r2, r3);
    }

    private void addConnectingShadowIfNecessary(float r7) {
        if (getCurrentShadowAngle() == r7) goto L10;
        float r0 = ((r7 - getCurrentShadowAngle()) + 360.0f) % 360.0f;
        if (r0 <= ANGLE_LEFT) goto L8;
        return;
    L8:
        PathArcOperation r1 = new PathArcOperation(getEndX(), getEndY(), getEndX(), getEndY());
        PathArcOperation.access$600(r1, getCurrentShadowAngle());
        PathArcOperation.access$700(r1, r0);
        this.shadowCompatOperations.add(new ArcShadowOperation(r1));
        setCurrentShadowAngle(r7);
        return;
    }

    private void addShadowCompatOperation(ShadowCompatOperation r1, float r2, float r3) {
        addConnectingShadowIfNecessary(r2);
        this.shadowCompatOperations.add(r1);
        setCurrentShadowAngle(r3);
    }

    private float getCurrentShadowAngle() {
        return this.currentShadowAngle;
    }

    private float getEndShadowAngle() {
        return this.endShadowAngle;
    }

    private void setCurrentShadowAngle(float r1) {
        this.currentShadowAngle = r1;
    }

    private void setEndShadowAngle(float r1) {
        this.endShadowAngle = r1;
    }

    private void setEndX(float r1) {
        this.endX = r1;
    }

    private void setEndY(float r1) {
        this.endY = r1;
    }

    private void setStartX(float r1) {
        this.startX = r1;
    }

    private void setStartY(float r1) {
        this.startY = r1;
    }

    public void addArc(float r5, float r6, float r7, float r8, float r9, float r10) {
        PathArcOperation r0 = new PathArcOperation(r5, r6, r7, r8);
        PathArcOperation.access$600(r0, r9);
        PathArcOperation.access$700(r0, r10);
        this.operations.add(r0);
        ArcShadowOperation r1 = new ArcShadowOperation(r0);
        float r02 = r9 + r10;
        if (r10 >= 0.0f) goto L5;
        boolean r102 = true;
    L7:
        if (r102 == false) goto L9;
        r9 = (r9 + ANGLE_LEFT) % 360.0f;
    L9:
        if (r102 == false) goto L11;
        float r3 = (ANGLE_LEFT + r02) % 360.0f;
    L12:
        addShadowCompatOperation(r1, r9, r3);
        double r03 = r02;
        setEndX(((r5 + r7) * 0.5f) + (((r7 - r5) / 2.0f) * ((float) Math.cos(Math.toRadians(r03)))));
        setEndY(((r6 + r8) * 0.5f) + (((r8 - r6) / 2.0f) * ((float) Math.sin(Math.toRadians(r03)))));
        return;
    L11:
        r3 = r02;
        goto L12
    L5:
        r102 = false;
        goto L7
    }

    public void applyToPath(Matrix r4, Path r5) {
        int r0 = this.operations.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.operations.get(r1).applyToPath(r4, r5);
        r1 = r1 + 1;
        goto L3
    }

    public boolean containsIncompatibleShadowOp() {
        return this.containsIncompatibleShadowOp;
    }

    public ShadowCompatOperation createShadowCompatOperation(Matrix r3) {
        addConnectingShadowIfNecessary(getEndShadowAngle());
        Matrix r0 = new Matrix(r3);
        return new AnonymousClass1(this, new ArrayList(this.shadowCompatOperations), r0);
    }

    public void cubicToPoint(float r8, float r9, float r10, float r11, float r12, float r13) {
        PathCubicOperation r0 = new PathCubicOperation(r8, r9, r10, r11, r12, r13);
        this.operations.add(r0);
        this.containsIncompatibleShadowOp = true;
        setEndX(r12);
        setEndY(r13);
    }

    public float getEndX() {
        return this.endX;
    }

    public float getEndY() {
        return this.endY;
    }

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public void lineTo(float r5, float r6) {
        PathLineOperation r0 = new PathLineOperation();
        PathLineOperation.access$002(r0, r5);
        PathLineOperation.access$102(r0, r6);
        this.operations.add(r0);
        LineShadowOperation r1 = new LineShadowOperation(r0, getEndX(), getEndY());
        addShadowCompatOperation(r1, r1.getAngle() + ANGLE_UP, r1.getAngle() + ANGLE_UP);
        setEndX(r5);
        setEndY(r6);
    }

    public void lineTo(float r6, float r7, float r8, float r9) {
        if (Math.abs(r6 - getEndX()) >= 0.001f) goto L7;
        if (Math.abs(r7 - getEndY()) >= 0.001f) goto L7;
    L10:
        lineTo(r8, r9);
        return;
    L7:
        if (Math.abs(r6 - r8) < 0.001f) goto L9;
    L12:
        PathLineOperation r0 = new PathLineOperation();
        PathLineOperation.access$002(r0, r6);
        PathLineOperation.access$102(r0, r7);
        this.operations.add(r0);
        PathLineOperation r1 = new PathLineOperation();
        PathLineOperation.access$002(r1, r8);
        PathLineOperation.access$102(r1, r9);
        this.operations.add(r1);
        InnerCornerShadowOperation r2 = new InnerCornerShadowOperation(r0, r1, getEndX(), getEndY());
        if (r2.getSweepAngle() <= 0.0f) goto L16;
        lineTo(r6, r7);
        lineTo(r8, r9);
        return;
    L16:
        addShadowCompatOperation(r2, r2.getStartAngle() + ANGLE_UP, r2.getEndAngle() + ANGLE_UP);
        setEndX(r8);
        setEndY(r9);
        return;
    L9:
        if (Math.abs(r7 - r9) >= 0.001f) goto L12;
        goto L10
    }

    public void quadToPoint(float r2, float r3, float r4, float r5) {
        PathQuadOperation r0 = new PathQuadOperation();
        PathQuadOperation.access$200(r0, r2);
        PathQuadOperation.access$300(r0, r3);
        PathQuadOperation.access$400(r0, r4);
        PathQuadOperation.access$500(r0, r5);
        this.operations.add(r0);
        this.containsIncompatibleShadowOp = true;
        setEndX(r4);
        setEndY(r5);
    }

    public void reset(float r3, float r4) {
        reset(r3, r4, ANGLE_UP, 0.0f);
    }

    public void reset(float r1, float r2, float r3, float r4) {
        setStartX(r1);
        setStartY(r2);
        setEndX(r1);
        setEndY(r2);
        setCurrentShadowAngle(r3);
        setEndShadowAngle((r3 + r4) % 360.0f);
        this.operations.clear();
        this.shadowCompatOperations.clear();
        this.containsIncompatibleShadowOp = false;
    }
}
