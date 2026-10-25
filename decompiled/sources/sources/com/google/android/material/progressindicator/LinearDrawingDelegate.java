package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;
import androidx.core.math.MathUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.progressindicator.DrawingDelegate;

/* loaded from: classes5.dex */
final class LinearDrawingDelegate extends DrawingDelegate<LinearProgressIndicatorSpec> {
    private float adjustedWavelength;
    private int cachedWavelength;
    private float displayedAmplitude;
    private float displayedCornerRadius;
    private float displayedInnerCornerRadius;
    private float displayedTrackThickness;
    private boolean drawingDeterminateIndicator;
    Pair<DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint, DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint> endPoints;
    private float totalTrackLengthFraction;
    private float trackLength;

    public LinearDrawingDelegate(LinearProgressIndicatorSpec r3) {
        super(r3);
        this.trackLength = 300.0f;
        this.endPoints = new Pair(new DrawingDelegate.PathPoint(this), new DrawingDelegate.PathPoint(this));
    }

    private void calculateDisplayedPath(PathMeasure r8, Path r9, Pair<DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint, DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint> r10, float r11, float r12, float r13, float r14) {
        boolean r0 = this.drawingDeterminateIndicator;
        LinearProgressIndicatorSpec r1 = (LinearProgressIndicatorSpec) this.spec;
        if (r0 == false) goto L5;
        int r02 = r1.wavelengthDeterminate;
    L7:
        if (r8 == this.activePathMeasure) goto L9;
    L11:
        r9.rewind();
        float r03 = (-this.trackLength) / 2.0f;
        boolean r15 = ((LinearProgressIndicatorSpec) this.spec).hasWavyEffect(this.drawingDeterminateIndicator);
        if (r15 == false) goto L14;
        float r3 = this.trackLength;
        float r4 = this.adjustedWavelength;
        float r32 = r3 / r4;
        float r5 = r14 / r32;
        float r33 = r32 / (r32 + 1.0f);
        r11 = (r11 + r5) * r33;
        r12 = (r12 + r5) * r33;
        r03 = r03 - (r14 * r4);
    L14:
        float r112 = r11 * r8.getLength();
        float r122 = r12 * r8.getLength();
        r8.getSegment(r112, r122, r9, true);
        DrawingDelegate.PathPoint r142 = (DrawingDelegate.PathPoint) r10.first;
        r142.reset();
        r8.getPosTan(r112, r142.posVec, r142.tanVec);
        DrawingDelegate.PathPoint r102 = (DrawingDelegate.PathPoint) r10.second;
        r102.reset();
        r8.getPosTan(r122, r102.posVec, r102.tanVec);
        this.transform.reset();
        this.transform.setTranslate(r03, 0.0f);
        r142.translate(r03, 0.0f);
        r102.translate(r03, 0.0f);
        if (r15 == false) goto L17;
        float r82 = this.displayedAmplitude * r13;
        this.transform.postScale(1.0f, r82);
        r142.scale(1.0f, r82);
        r102.scale(1.0f, r82);
    L17:
        r9.transform(this.transform);
        return;
    L9:
        if (r02 == this.cachedWavelength) goto L11;
        this.cachedWavelength = r02;
        invalidateCachedPaths();
        goto L11
    L5:
        r02 = r1.wavelengthIndeterminate;
        goto L7
    }

    private void drawLine(Canvas r18, Paint r19, float r20, float r21, int r22, int r23, int r24, float r25, float r26, boolean r27) {
        float r3 = MathUtils.clamp(r20, 0.0f, 1.0f);
        float r4 = MathUtils.clamp(r21, 0.0f, 1.0f);
        float r32 = com.google.android.material.math.MathUtils.lerp(1.0f - this.totalTrackLengthFraction, 1.0f, r3);
        float r42 = com.google.android.material.math.MathUtils.lerp(1.0f - this.totalTrackLengthFraction, 1.0f, r4);
        int r5 = (int) ((r23 * MathUtils.clamp(r32, 0.0f, 0.01f)) / 0.01f);
        int r1 = (int) ((r24 * (1.0f - MathUtils.clamp(r42, 0.99f, 1.0f))) / 0.01f);
        float r6 = this.trackLength;
        int r33 = (int) ((r32 * r6) + r5);
        int r12 = (int) ((r42 * r6) - r1);
        float r43 = this.displayedCornerRadius;
        float r52 = this.displayedInnerCornerRadius;
        if (r43 == r52) goto L5;
        float r44 = Math.max(r43, r52);
        float r53 = this.trackLength;
        float r45 = r44 / r53;
        float r54 = com.google.android.material.math.MathUtils.lerp(this.displayedCornerRadius, this.displayedInnerCornerRadius, MathUtils.clamp(r33 / r53, 0.0f, r45) / r45);
        float r62 = this.displayedCornerRadius;
        float r7 = this.displayedInnerCornerRadius;
        float r9 = this.trackLength;
        float r10 = com.google.android.material.math.MathUtils.lerp(r62, r7, MathUtils.clamp((r9 - r12) / r9, 0.0f, r45) / r45);
        float r63 = r54;
    L6:
        float r46 = (-this.trackLength) / 2.0f;
        if (((LinearProgressIndicatorSpec) this.spec).hasWavyEffect(this.drawingDeterminateIndicator) == false) goto L12;
        if (r27 == false) goto L12;
        if (r25 <= 0.0f) goto L12;
        boolean r72 = true;
    L13:
        if (r33 > r12) goto L45;
        float r122 = r33 + r63;
        float r13 = r12 - r10;
        float r47 = r63 * 2.0f;
        float r55 = 2.0f * r10;
        r19.setColor(r22);
        r19.setAntiAlias(true);
        r19.setStrokeWidth(this.displayedTrackThickness);
        ((DrawingDelegate.PathPoint) this.endPoints.first).reset();
        ((DrawingDelegate.PathPoint) this.endPoints.second).reset();
        ((DrawingDelegate.PathPoint) this.endPoints.first).translate(r122 + r46, 0.0f);
        ((DrawingDelegate.PathPoint) this.endPoints.second).translate(r46 + r13, 0.0f);
        if (r33 != 0) goto L21;
        if ((r13 + r10) >= (r122 + r63)) goto L21;
        Pair<DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint, DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint> r14 = this.endPoints;
        DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint r34 = (DrawingDelegate.PathPoint) r14.first;
        float r56 = this.displayedTrackThickness;
        drawRoundedBlock(r18, r19, r34, r47, r56, r63, (DrawingDelegate.PathPoint) r14.second, r55, r56, r10, true);
        return;
    L21:
        if ((r122 - r63) <= (r13 - r10)) goto L24;
        Pair<DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint, DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint> r2 = this.endPoints;
        DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint r35 = (DrawingDelegate.PathPoint) r2.second;
        float r57 = this.displayedTrackThickness;
        drawRoundedBlock(r18, r19, r35, r55, r57, r10, (DrawingDelegate.PathPoint) r2.first, r47, r57, r63, false);
        return;
    L24:
        float r142 = r10;
        float r102 = r63;
        r19.setStyle(Paint.Style.STROKE);
        if (((LinearProgressIndicatorSpec) this.spec).useStrokeCap() == false) goto L27;
        Paint.Cap r15 = Paint.Cap.ROUND;
    L28:
        r19.setStrokeCap(r15);
        if (r72 == true) goto L31;
        Pair<DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint, DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint> r16 = this.endPoints;
        Object r36 = r16.first;
        float r48 = ((DrawingDelegate.PathPoint) r36).posVec[0];
        float r37 = ((DrawingDelegate.PathPoint) r36).posVec[1];
        Object r17 = r16.second;
        r18.drawLine(r48, r37, ((DrawingDelegate.PathPoint) r17).posVec[0], ((DrawingDelegate.PathPoint) r17).posVec[1], r19);
        Paint r92 = r19;
        Canvas r28 = r18;
    L33:
        if (((LinearProgressIndicatorSpec) this.spec).useStrokeCap() == false) goto L35;
        return;
    L35:
        if (r122 <= 0.0f) goto L40;
        if (r102 <= 0.0f) goto L40;
        drawRoundedBlock(r28, r92, (DrawingDelegate.PathPoint) this.endPoints.first, r47, this.displayedTrackThickness, r102);
    L40:
        if (r13 < this.trackLength) goto L42;
        return;
    L42:
        if (r142 <= 0.0f) goto L48;
        drawRoundedBlock(r18, r19, (DrawingDelegate.PathPoint) this.endPoints.second, r55, this.displayedTrackThickness, r142);
        return;
    L48:
        return;
    L31:
        r92 = r19;
        PathMeasure r110 = this.activePathMeasure;
        Path r29 = this.displayedActivePath;
        Pair<DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint, DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint> r38 = this.endPoints;
        float r49 = this.trackLength;
        calculateDisplayedPath(r110, r29, r38, r122 / r49, r13 / r49, r25, r26);
        r28 = r18;
        r28.drawPath(this.displayedActivePath, r92);
        goto L33
    L27:
        r15 = Paint.Cap.BUTT;
        goto L28
    L45:
        return;
    L12:
        r72 = false;
        goto L13
    L5:
        r63 = r43;
        r10 = r63;
        goto L6
    }

    private void drawRoundedBlock(Canvas r13, Paint r14, DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint r15, float r16, float r17, float r18) {
        drawRoundedBlock(r13, r14, r15, r16, r17, r18, null, 0.0f, 0.0f, 0.0f, false);
    }

    private void drawRoundedBlock(Canvas r18, Paint r19, DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint r20, float r21, float r22, float r23, DrawingDelegate<LinearProgressIndicatorSpec>.PathPoint r24, float r25, float r26, float r27, boolean r28) {
        float r7 = Math.min(r22, this.displayedTrackThickness);
        float r9 = (-r21) / 2.0f;
        float r11 = (-r7) / 2.0f;
        float r4 = r21 / 2.0f;
        float r72 = r7 / 2.0f;
        RectF r8 = new RectF(r9, r11, r4, r72);
        r19.setStyle(Paint.Style.FILL);
        r18.save();
        if (r24 == null) goto L17;
        float r14 = Math.min(r26, this.displayedTrackThickness);
        float r10 = Math.min(r25 / 2.0f, (r27 * r14) / this.displayedTrackThickness);
        RectF r15 = new RectF();
        float[] r12 = r24.posVec;
        if (r28 == false) goto L11;
        float r92 = (r12[0] - r10) - (r20.posVec[0] - r23);
        if (r92 <= 0.0f) goto L9;
        r24.translate((-r92) / 2.0f, 0.0f);
        float r93 = r25 + r92;
    L10:
        r15.set(0.0f, r11, r4, r72);
    L16:
        RectF r42 = new RectF((-r93) / 2.0f, (-r14) / 2.0f, r93 / 2.0f, r14 / 2.0f);
        float[] r73 = r24.posVec;
        r18.translate(r73[0], r73[1]);
        r18.rotate(vectorToCanvasRotation(r24.tanVec));
        Path r74 = new Path();
        r74.addRoundRect(r42, r10, r10, Path.Direction.CCW);
        r18.clipPath(r74);
        r18.rotate(-vectorToCanvasRotation(r24.tanVec));
        float[] r43 = r24.posVec;
        r18.translate(-r43[0], -r43[1]);
        float[] r44 = r20.posVec;
        r18.translate(r44[0], r44[1]);
        r18.rotate(vectorToCanvasRotation(r20.tanVec));
        r18.drawRect(r15, r19);
        r18.drawRoundRect(r8, r23, r23, r19);
    L18:
        r18.restore();
        return;
    L9:
        r93 = r25;
        goto L10
    L11:
        float r45 = (r12[0] + r10) - (r20.posVec[0] + r23);
        if (r45 >= 0.0f) goto L14;
        r24.translate((-r45) / 2.0f, 0.0f);
        float r46 = r25 - r45;
    L15:
        r15.set(r9, r11, 0.0f, r72);
        r93 = r46;
        goto L16
    L14:
        r46 = r25;
        goto L15
    L17:
        float[] r47 = r20.posVec;
        r18.translate(r47[0], r47[1]);
        r18.rotate(vectorToCanvasRotation(r20.tanVec));
        r18.drawRoundRect(r8, r23, r23, r19);
        goto L18
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void adjustCanvas(Canvas r8, Rect r9, float r10, boolean r11, boolean r12) {
        if (this.trackLength == r9.width()) goto L5;
        this.trackLength = r9.width();
        invalidateCachedPaths();
    L5:
        float r0 = getPreferredHeight();
        r8.translate(r9.left + (r9.width() / 2.0f), (r9.top + (r9.height() / 2.0f)) + Math.max(0.0f, (r9.height() - r0) / 2.0f));
        if (((LinearProgressIndicatorSpec) this.spec).drawHorizontallyInverse == false) goto L8;
        r8.scale(-1.0f, 1.0f);
    L8:
        float r92 = this.trackLength / 2.0f;
        float r02 = r0 / 2.0f;
        r8.clipRect(-r92, -r02, r92, r02);
        S r93 = this.spec;
        this.displayedTrackThickness = ((LinearProgressIndicatorSpec) r93).trackThickness * r10;
        this.displayedCornerRadius = Math.min(((LinearProgressIndicatorSpec) r93).trackThickness / 2, ((LinearProgressIndicatorSpec) r93).getTrackCornerRadiusInPx()) * r10;
        S r94 = this.spec;
        this.displayedAmplitude = ((LinearProgressIndicatorSpec) r94).waveAmplitude * r10;
        this.displayedInnerCornerRadius = Math.min(((LinearProgressIndicatorSpec) r94).trackThickness / 2.0f, ((LinearProgressIndicatorSpec) r94).getTrackInnerCornerRadiusInPx()) * r10;
        if (r11 == true) goto L11;
        if (r12 == true) goto L11;
    L23:
        if (r12 == true) goto L25;
    L28:
        this.totalTrackLengthFraction = 1.0f;
        return;
    L25:
        if (((LinearProgressIndicatorSpec) this.spec).hideAnimationBehavior != 3) goto L28;
        this.totalTrackLengthFraction = r10;
        return;
    L11:
        if (r11 == true) goto L13;
    L14:
        if (r12 == true) goto L16;
    L18:
        if (r11 == true) goto L22;
        if (r12 == false) goto L23;
        if (((LinearProgressIndicatorSpec) this.spec).hideAnimationBehavior == 3) goto L23;
    L22:
        r8.translate(0.0f, (((LinearProgressIndicatorSpec) this.spec).trackThickness * (1.0f - r10)) / 2.0f);
        goto L23
    L16:
        if (((LinearProgressIndicatorSpec) this.spec).hideAnimationBehavior != 1) goto L18;
    L17:
        r8.scale(1.0f, -1.0f);
        goto L18
    L13:
        if (((LinearProgressIndicatorSpec) this.spec).showAnimationBehavior == 2) goto L17;
        goto L14
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void drawStopIndicator(Canvas r11, Paint r12, int r13, int r14) {
        int r132 = MaterialColors.compositeARGBWithAlpha(r13, r14);
        this.drawingDeterminateIndicator = false;
        int r0 = ((LinearProgressIndicatorSpec) this.spec).getActualTrackStopIndicatorSize();
        if (r0 <= 0) goto L11;
        if (r132 == 0) goto L12;
        r12.setStyle(Paint.Style.FILL);
        r12.setColor(r132);
        S r133 = this.spec;
        if (((LinearProgressIndicatorSpec) r133).trackStopIndicatorPadding == null) goto L8;
        float r134 = ((LinearProgressIndicatorSpec) r133).trackStopIndicatorPadding.floatValue() + (((LinearProgressIndicatorSpec) this.spec).trackStopIndicatorSize / 2.0f);
    L9:
        float r7 = r0;
        drawRoundedBlock(r11, r12, new DrawingDelegate.PathPoint(this, new float[]{(this.trackLength / 2.0f) - r134, 0.0f}, new float[]{1.0f, 0.0f}), r7, r7, (this.displayedCornerRadius * r7) / this.displayedTrackThickness);
        return;
    L8:
        r134 = this.displayedTrackThickness / 2.0f;
        goto L9
    L12:
        return;
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillIndicator(Canvas r14, Paint r15, DrawingDelegate.ActiveIndicator r16, int r17) {
        int r7 = MaterialColors.compositeARGBWithAlpha(r16.color, r17);
        this.drawingDeterminateIndicator = r16.isDeterminate;
        float r5 = r16.startFraction;
        float r6 = r16.endFraction;
        int r8 = r16.gapSize;
        drawLine(r14, r15, r5, r6, r7, r8, r8, r16.amplitudeFraction, r16.phaseFraction, true);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillTrack(Canvas r12, Paint r13, float r14, float r15, int r16, int r17, int r18) {
        int r5 = MaterialColors.compositeARGBWithAlpha(r16, r17);
        this.drawingDeterminateIndicator = false;
        drawLine(r12, r13, r14, r15, r5, r18, r18, 0.0f, 0.0f, false);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredHeight() {
        S r1 = this.spec;
        return ((LinearProgressIndicatorSpec) r1).trackThickness + (((LinearProgressIndicatorSpec) r1).waveAmplitude * 2);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredWidth() {
        return -1;
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void invalidateCachedPaths() {
        this.cachedActivePath.rewind();
        if (((LinearProgressIndicatorSpec) this.spec).hasWavyEffect(this.drawingDeterminateIndicator) == false) goto L12;
        boolean r1 = this.drawingDeterminateIndicator;
        LinearProgressIndicatorSpec r4 = (LinearProgressIndicatorSpec) this.spec;
        if (r1 == false) goto L7;
        int r12 = r4.wavelengthDeterminate;
    L8:
        float r42 = this.trackLength;
        int r13 = (int) (r42 / r12);
        this.adjustedWavelength = r42 / r13;
        int r43 = 0;
    L9:
        if (r43 > r13) goto L11;
        int r122 = r43 * 2;
        float r10 = r122 + 1;
        this.cachedActivePath.cubicTo(r122 + 0.48f, 0.0f, r10 - 0.48f, 1.0f, r10, 1.0f);
        float r5 = r122 + 2;
        this.cachedActivePath.cubicTo(r10 + 0.48f, 1.0f, r5 - 0.48f, 0.0f, r5, 0.0f);
        r43 = r43 + 1;
        goto L9
    L11:
        this.transform.reset();
        this.transform.setScale(this.adjustedWavelength / 2.0f, -2.0f);
        this.transform.postTranslate(0.0f, 1.0f);
        this.cachedActivePath.transform(this.transform);
    L13:
        this.activePathMeasure.setPath(this.cachedActivePath, false);
        return;
    L7:
        r12 = r4.wavelengthIndeterminate;
        goto L8
    L12:
        this.cachedActivePath.lineTo(this.trackLength, 0.0f);
        goto L13
    }
}
