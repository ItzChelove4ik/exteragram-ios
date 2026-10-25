package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.Pair;
import androidx.core.math.MathUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.progressindicator.DrawingDelegate;
import java.util.ArrayList;

/* loaded from: classes5.dex */
final class CircularDrawingDelegate extends DrawingDelegate<CircularProgressIndicatorSpec> {
    private static final float QUARTER_CIRCLE_CONTROL_HANDLE_LENGTH = 0.5522848f;
    private static final float ROUND_CAP_RAMP_DOWN_THRESHHOLD = 0.01f;
    private float adjustedRadius;
    private float adjustedWavelength;
    private final RectF arcBounds;
    private float cachedAmplitude;
    private float cachedRadius;
    private int cachedWavelength;
    private float displayedAmplitude;
    private float displayedCornerRadius;
    private float displayedTrackThickness;
    private boolean drawingDeterminateIndicator;
    private final Pair<DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint, DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint> endPoints;
    private float totalTrackLengthFraction;

    public CircularDrawingDelegate(CircularProgressIndicatorSpec r3) {
        super(r3);
        this.arcBounds = new RectF();
        this.endPoints = new Pair(new DrawingDelegate.PathPoint(this), new DrawingDelegate.PathPoint(this));
    }

    private void appendCubicPerHalfCycle(Path r9, DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint r10, DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint r11) {
        float r0 = (this.adjustedWavelength / 2.0f) * 0.48f;
        DrawingDelegate.PathPoint r1 = new DrawingDelegate.PathPoint(this, r10);
        DrawingDelegate.PathPoint r102 = new DrawingDelegate.PathPoint(this, r11);
        r1.moveAlong(r0);
        r102.moveAlong(-r0);
        float[] r8 = r1.posVec;
        float r2 = r8[0];
        float r3 = r8[1];
        float[] r82 = r102.posVec;
        float r4 = r82[0];
        float r5 = r82[1];
        float[] r83 = r11.posVec;
        r9.cubicTo(r2, r3, r4, r5, r83[0], r83[1]);
    }

    private void calculateDisplayedPath(PathMeasure r6, Path r7, Pair<DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint, DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint> r8, float r9, float r10, float r11, float r12) {
        float r0 = this.displayedAmplitude * r11;
        boolean r112 = this.drawingDeterminateIndicator;
        CircularProgressIndicatorSpec r1 = (CircularProgressIndicatorSpec) this.spec;
        if (r112 == false) goto L5;
        int r113 = r1.wavelengthDeterminate;
    L6:
        float r13 = this.adjustedRadius;
        if (r13 == this.cachedRadius) goto L9;
    L14:
        this.cachedAmplitude = r0;
        this.cachedWavelength = r113;
        this.cachedRadius = r13;
        invalidateCachedPaths();
    L15:
        r7.rewind();
        float r114 = 0.0f;
        float r102 = MathUtils.clamp(r10, 0.0f, 1.0f);
        if (((CircularProgressIndicatorSpec) this.spec).hasWavyEffect(this.drawingDeterminateIndicator) == false) goto L18;
        float r122 = r12 / ((float) ((this.adjustedRadius * 6.283185307179586d) / this.adjustedWavelength));
        r9 = r9 + r122;
        r114 = 0.0f - (r122 * 360.0f);
    L18:
        float r92 = r9 % 1.0f;
        float r123 = (r6.getLength() * r92) / 2.0f;
        float r93 = ((r92 + r102) * r6.getLength()) / 2.0f;
        r6.getSegment(r123, r93, r7, true);
        DrawingDelegate.PathPoint r103 = (DrawingDelegate.PathPoint) r8.first;
        r103.reset();
        r6.getPosTan(r123, r103.posVec, r103.tanVec);
        DrawingDelegate.PathPoint r82 = (DrawingDelegate.PathPoint) r8.second;
        r82.reset();
        r6.getPosTan(r93, r82.posVec, r82.tanVec);
        this.transform.reset();
        this.transform.setRotate(r114);
        r103.rotate(r114);
        r82.rotate(r114);
        r7.transform(this.transform);
        return;
    L9:
        if (r6 != this.activePathMeasure) goto L15;
        if (r0 != this.cachedAmplitude) goto L14;
        if (r113 == this.cachedWavelength) goto L15;
    L5:
        r113 = r1.wavelengthIndeterminate;
        goto L6
    }

    private void createWavyPath(PathMeasure r11, Path r12, float r13) {
        r12.rewind();
        float r0 = r11.getLength();
        boolean r1 = this.drawingDeterminateIndicator;
        CircularProgressIndicatorSpec r2 = (CircularProgressIndicatorSpec) this.spec;
        if (r1 == false) goto L5;
        int r14 = r2.wavelengthDeterminate;
    L6:
        int r15 = Math.max(3, (int) ((r0 / r14) / 2.0f)) * 2;
        this.adjustedWavelength = r0 / r15;
        ArrayList r02 = new ArrayList();
        int r4 = 0;
    L7:
        if (r4 >= r15) goto L9;
        DrawingDelegate.PathPoint r5 = new DrawingDelegate.PathPoint(this);
        float r7 = r4;
        r11.getPosTan(this.adjustedWavelength * r7, r5.posVec, r5.tanVec);
        DrawingDelegate.PathPoint r6 = new DrawingDelegate.PathPoint(this);
        float r8 = this.adjustedWavelength;
        r11.getPosTan((r7 * r8) + (r8 / 2.0f), r6.posVec, r6.tanVec);
        r02.add(r5);
        r6.moveAcross(r13 * 2.0f);
        r02.add(r6);
        r4 = r4 + 1;
        goto L7
    L9:
        r02.add((DrawingDelegate.PathPoint) r02.get(0));
        DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint r112 = (DrawingDelegate.PathPoint) r02.get(0);
        float[] r132 = r112.posVec;
        int r22 = 1;
        r12.moveTo(r132[0], r132[1]);
    L11:
        if (r22 >= r02.size()) goto L13;
        DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint r133 = (DrawingDelegate.PathPoint) r02.get(r22);
        appendCubicPerHalfCycle(r12, r112, r133);
        r22 = r22 + 1;
        r112 = r133;
        goto L11
    L13:
        return;
    L5:
        r14 = r2.wavelengthIndeterminate;
        goto L6
    }

    private void drawArc(Canvas r14, Paint r15, float r16, float r17, int r18, int r19, int r20, float r21, float r22, boolean r23) {
        if (r17 < r16) goto L5;
        float r1 = r17 - r16;
    L6:
        float r3 = r16 % 1.0f;
        if (r3 >= 0.0f) goto L10;
        r3 = r3 + 1.0f;
    L10:
        if (this.totalTrackLengthFraction >= 1.0f) goto L15;
        float r11 = r3 + r1;
        if (r11 <= 1.0f) goto L15;
        drawArc(r14, r15, r3, 1.0f, r18, r19, 0, r21, r22, r23);
        drawArc(r14, r15, 1.0f, r11, r18, 0, r20, r21, r22, r23);
        return;
    L15:
        float r5 = (float) Math.toDegrees(this.displayedCornerRadius / this.adjustedRadius);
        float r6 = r1 - 0.99f;
        if (r6 < 0.0f) goto L20;
        float r62 = ((r6 * r5) / 180.0f) / ROUND_CAP_RAMP_DOWN_THRESHHOLD;
        r1 = r1 + r62;
        if (r23 == true) goto L20;
        r3 = r3 - (r62 / 2.0f);
    L20:
        float r2 = com.google.android.material.math.MathUtils.lerp(1.0f - this.totalTrackLengthFraction, 1.0f, r3);
        float r12 = com.google.android.material.math.MathUtils.lerp(0.0f, this.totalTrackLengthFraction, r1);
        float r32 = (float) Math.toDegrees(r19 / this.adjustedRadius);
        float r13 = ((r12 * 360.0f) - r32) - ((float) Math.toDegrees(r20 / this.adjustedRadius));
        float r24 = (r2 * 360.0f) + r32;
        if (r13 > 0.0f) goto L24;
        return;
    L24:
        if (((CircularProgressIndicatorSpec) this.spec).hasWavyEffect(this.drawingDeterminateIndicator) == false) goto L29;
        if (r23 == false) goto L29;
        if (r21 <= 0.0f) goto L29;
        boolean r33 = true;
    L30:
        r15.setAntiAlias(true);
        r15.setColor(r18);
        r15.setStrokeWidth(this.displayedTrackThickness);
        float r63 = this.displayedCornerRadius * 2.0f;
        float r10 = r5 * 2.0f;
        if (r13 >= r10) goto L43;
        float r110 = r13 / r10;
        float r25 = r24 + (r5 * r110);
        DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint r52 = new DrawingDelegate.PathPoint(this);
        if (r33 == true) goto L35;
        r52.rotate(r25 + 90.0f);
        r52.moveAcross(-this.adjustedRadius);
    L41:
        r15.setStyle(Paint.Style.FILL);
        drawRoundedBlock(r14, r15, r52, r63, this.displayedTrackThickness, r110);
        return;
    L35:
        float r26 = ((r25 / 360.0f) * this.activePathMeasure.getLength()) / 2.0f;
        float r34 = this.displayedAmplitude * r21;
        float r7 = this.adjustedRadius;
        if (r7 == this.cachedRadius) goto L38;
    L39:
        this.cachedAmplitude = r34;
        this.cachedRadius = r7;
        invalidateCachedPaths();
    L40:
        this.activePathMeasure.getPosTan(r26, r52.posVec, r52.tanVec);
        goto L41
    L38:
        if (r34 == this.cachedAmplitude) goto L40;
    L43:
        r15.setStyle(Paint.Style.STROKE);
        if (((CircularProgressIndicatorSpec) this.spec).useStrokeCap() == false) goto L46;
        Paint.Cap r64 = Paint.Cap.ROUND;
    L47:
        r15.setStrokeCap(r64);
        float r27 = r24 + r5;
        float r111 = r13 - r10;
        ((DrawingDelegate.PathPoint) this.endPoints.first).reset();
        ((DrawingDelegate.PathPoint) this.endPoints.second).reset();
        if (r33 == true) goto L50;
        ((DrawingDelegate.PathPoint) this.endPoints.first).rotate(r27 + 90.0f);
        ((DrawingDelegate.PathPoint) this.endPoints.first).moveAcross(-this.adjustedRadius);
        ((DrawingDelegate.PathPoint) this.endPoints.second).rotate((r27 + r111) + 90.0f);
        ((DrawingDelegate.PathPoint) this.endPoints.second).moveAcross(-this.adjustedRadius);
        RectF r35 = this.arcBounds;
        float r53 = this.adjustedRadius;
        r35.set(-r53, -r53, r53, r53);
        r14.drawArc(this.arcBounds, r27, r111, false, r15);
    L52:
        if (((CircularProgressIndicatorSpec) this.spec).useStrokeCap() == false) goto L54;
        return;
    L54:
        if (this.displayedCornerRadius <= 0.0f) goto L58;
        r15.setStyle(Paint.Style.FILL);
        drawRoundedBlock(r14, r15, (DrawingDelegate.PathPoint) this.endPoints.first, r63, this.displayedTrackThickness);
        drawRoundedBlock(r14, r15, (DrawingDelegate.PathPoint) this.endPoints.second, r63, this.displayedTrackThickness);
        return;
    L58:
        return;
    L50:
        calculateDisplayedPath(this.activePathMeasure, this.displayedActivePath, this.endPoints, r27 / 360.0f, r111 / 360.0f, r21, r22);
        r14.drawPath(this.displayedActivePath, r15);
        goto L52
    L46:
        r64 = Paint.Cap.BUTT;
    L29:
        r33 = false;
        goto L30
    L5:
        r1 = (r17 + 1.0f) - r16;
        goto L6
    }

    private void drawRoundedBlock(Canvas r8, Paint r9, DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint r10, float r11, float r12) {
        drawRoundedBlock(r8, r9, r10, r11, r12, 1.0f);
    }

    private void drawRoundedBlock(Canvas r6, Paint r7, DrawingDelegate<CircularProgressIndicatorSpec>.PathPoint r8, float r9, float r10, float r11) {
        float r102 = Math.min(r10, this.displayedTrackThickness);
        float r2 = r9 / 2.0f;
        float r0 = Math.min(r2, (this.displayedCornerRadius * r102) / this.displayedTrackThickness);
        RectF r3 = new RectF((-r9) / 2.0f, (-r102) / 2.0f, r2, r102 / 2.0f);
        r6.save();
        float[] r92 = r8.posVec;
        r6.translate(r92[0], r92[1]);
        r6.rotate(vectorToCanvasRotation(r8.tanVec));
        r6.scale(r11, r11);
        r6.drawRoundRect(r3, r0, r0, r7);
        r6.restore();
    }

    private int getSize() {
        S r1 = this.spec;
        return ((CircularProgressIndicatorSpec) r1).indicatorSize + (((CircularProgressIndicatorSpec) r1).indicatorInset * 2);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void adjustCanvas(Canvas r8, Rect r9, float r10, boolean r11, boolean r12) {
        float r0 = r9.width() / getPreferredWidth();
        float r1 = r9.height() / getPreferredHeight();
        S r2 = this.spec;
        float r3 = (((CircularProgressIndicatorSpec) r2).indicatorSize / 2.0f) + ((CircularProgressIndicatorSpec) r2).indicatorInset;
        r8.translate((r3 * r0) + r9.left, (r3 * r1) + r9.top);
        r8.rotate(-90.0f);
        r8.scale(r0, r1);
        if (((CircularProgressIndicatorSpec) this.spec).indicatorDirection == 0) goto L7;
        r8.scale(1.0f, -1.0f);
        if (Build.VERSION.SDK_INT != 29) goto L7;
        r8.rotate(0.1f);
    L7:
        float r92 = -r3;
        r8.clipRect(r92, r92, r3, r3);
        S r82 = this.spec;
        this.displayedTrackThickness = ((CircularProgressIndicatorSpec) r82).trackThickness * r10;
        this.displayedCornerRadius = Math.min(((CircularProgressIndicatorSpec) r82).trackThickness / 2, ((CircularProgressIndicatorSpec) r82).getTrackCornerRadiusInPx()) * r10;
        S r83 = this.spec;
        this.displayedAmplitude = ((CircularProgressIndicatorSpec) r83).waveAmplitude * r10;
        float r93 = (((CircularProgressIndicatorSpec) r83).indicatorSize - ((CircularProgressIndicatorSpec) r83).trackThickness) / 2.0f;
        this.adjustedRadius = r93;
        if (r11 == true) goto L10;
        if (r12 == true) goto L10;
    L26:
        if (r12 == true) goto L28;
    L31:
        this.totalTrackLengthFraction = 1.0f;
        return;
    L28:
        if (((CircularProgressIndicatorSpec) r83).hideAnimationBehavior != 3) goto L31;
        this.totalTrackLengthFraction = r10;
        return;
    L10:
        float r22 = ((1.0f - r10) * ((CircularProgressIndicatorSpec) r83).trackThickness) / 2.0f;
        if (r11 == false) goto L15;
        if (((CircularProgressIndicatorSpec) r83).showAnimationBehavior != 2) goto L15;
    L18:
        this.adjustedRadius = r93 + r22;
    L15:
        if (r12 == true) goto L17;
    L19:
        if (r11 == true) goto L21;
    L22:
        if (r12 == false) goto L26;
        if (((CircularProgressIndicatorSpec) r83).hideAnimationBehavior != 2) goto L26;
    L25:
        this.adjustedRadius = r93 - r22;
        goto L26
    L21:
        if (((CircularProgressIndicatorSpec) r83).showAnimationBehavior == 1) goto L25;
    L17:
        if (((CircularProgressIndicatorSpec) r83).hideAnimationBehavior != 1) goto L19;
        goto L18
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void drawStopIndicator(Canvas r1, Paint r2, int r3, int r4) {
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillIndicator(Canvas r14, Paint r15, DrawingDelegate.ActiveIndicator r16, int r17) {
        int r7 = MaterialColors.compositeARGBWithAlpha(r16.color, r17);
        r14.save();
        r14.rotate(r16.rotationDegree);
        this.drawingDeterminateIndicator = r16.isDeterminate;
        float r5 = r16.startFraction;
        float r6 = r16.endFraction;
        int r8 = r16.gapSize;
        drawArc(r14, r15, r5, r6, r7, r8, r8, r16.amplitudeFraction, r16.phaseFraction, true);
        r14.restore();
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillTrack(Canvas r12, Paint r13, float r14, float r15, int r16, int r17, int r18) {
        int r5 = MaterialColors.compositeARGBWithAlpha(r16, r17);
        this.drawingDeterminateIndicator = false;
        drawArc(r12, r13, r14, r15, r5, r18, r18, 0.0f, 0.0f, false);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredHeight() {
        return getSize();
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredWidth() {
        return getSize();
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void invalidateCachedPaths() {
        this.cachedActivePath.rewind();
        this.cachedActivePath.moveTo(1.0f, 0.0f);
        int r2 = 0;
    L4:
        if (r2 >= 2) goto L6;
        this.cachedActivePath.cubicTo(1.0f, QUARTER_CIRCLE_CONTROL_HANDLE_LENGTH, QUARTER_CIRCLE_CONTROL_HANDLE_LENGTH, 1.0f, 0.0f, 1.0f);
        this.cachedActivePath.cubicTo(-0.5522848f, 1.0f, -1.0f, QUARTER_CIRCLE_CONTROL_HANDLE_LENGTH, -1.0f, 0.0f);
        this.cachedActivePath.cubicTo(-1.0f, -0.5522848f, -0.5522848f, -1.0f, 0.0f, -1.0f);
        this.cachedActivePath.cubicTo(QUARTER_CIRCLE_CONTROL_HANDLE_LENGTH, -1.0f, 1.0f, -0.5522848f, 1.0f, 0.0f);
        r2 = r2 + 1;
        goto L4
    L6:
        this.transform.reset();
        Matrix r22 = this.transform;
        float r3 = this.adjustedRadius;
        r22.setScale(r3, r3);
        this.cachedActivePath.transform(this.transform);
        if (((CircularProgressIndicatorSpec) this.spec).hasWavyEffect(this.drawingDeterminateIndicator) == false) goto L9;
        this.activePathMeasure.setPath(this.cachedActivePath, false);
        createWavyPath(this.activePathMeasure, this.cachedActivePath, this.cachedAmplitude);
    L9:
        this.activePathMeasure.setPath(this.cachedActivePath, false);
    }
}
