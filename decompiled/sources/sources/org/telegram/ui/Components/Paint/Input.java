package org.telegram.ui.Components.Paint;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.zxing.common.detector.MathUtils;
import java.util.List;
import java.util.Vector;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Paint.Brush;
import org.telegram.ui.Components.Size;

/* loaded from: classes7.dex */
public class Input {
    private static final CubicBezierInterpolator PRESSURE_INTERPOLATOR = null;
    private ValueAnimator arrowAnimator;
    private boolean beganDrawing;
    private boolean canFill;
    private boolean clearBuffer;
    private final ShapeDetector detector;
    private long drawingStart;
    private ValueAnimator fillAnimator;
    private final Runnable fillWithCurrentBrush;
    private boolean hasMoved;
    private boolean ignore;
    private Matrix invertMatrix;
    private boolean isFirst;
    private float lastAngle;
    private boolean lastAngleSet;
    private Point lastLocation;
    private double lastRemainder;
    private float lastScale;
    private Point lastThickLocation;
    private long lastVelocityUpdate;
    private Point[] points;
    private int pointsCount;
    private int realPointsCount;
    private RenderView renderView;
    private Brush switchedBrushByStylusFrom;
    private float[] tempPoint;
    private double thicknessCount;
    private double thicknessSum;
    private float velocity;

    /* renamed from: org.telegram.ui.Components.Paint.Input$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ Input this$0;
        final /* synthetic */ float val$R;
        final /* synthetic */ Brush val$finalBrush;
        final /* synthetic */ Runnable val$onDone;
        final /* synthetic */ Point val$point;
        final /* synthetic */ boolean val$registerUndo;

        public AnonymousClass1(Input r1, Point r2, float r3, Brush r4, boolean r5, Runnable r6) {
            this.this$0 = r1;
            this.val$point = r2;
            this.val$R = r3;
            this.val$finalBrush = r4;
            this.val$registerUndo = r5;
            this.val$onDone = r6;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            Input.m11352$$Nest$fputfillAnimator(this.this$0, null);
            Path r52 = new Path(new Point[]{this.val$point});
            r52.setup(Input.m11350$$Nest$fgetrenderView(this.this$0).getCurrentColor(), this.val$R * 1.0f, this.val$finalBrush);
            if (this.val$finalBrush.isEraser() == false) goto L5;
            int r1 = -1;
        L6:
            Input.m11350$$Nest$fgetrenderView(this.this$0).getPainting().commitPath(r52, r1, this.val$registerUndo, null);
            if (this.val$registerUndo == false) goto L9;
            Input.m11350$$Nest$fgetrenderView(this.this$0).onFinishedDrawing(true);
        L9:
            Runnable r4 = this.val$onDone;
            if (r4 == null) goto L13;
            r4.run();
            return;
        L13:
            return;
        L5:
            r1 = Input.m11350$$Nest$fgetrenderView(this.this$0).getCurrentColor();
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Input$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ Input this$0;

        public AnonymousClass2(Input r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            Input.m11350$$Nest$fgetrenderView(this.this$0).getPainting().commitPath(null, Input.m11350$$Nest$fgetrenderView(this.this$0).getCurrentColor());
            Input.m11351$$Nest$fputarrowAnimator(this.this$0, null);
        }
    }

    /* renamed from: $r8$lambda$3ZBjqsUsvDQo6ChesZIWbr2GN-s, reason: not valid java name */
    public static /* synthetic */ void m11346$r8$lambda$3ZBjqsUsvDQo6ChesZIWbr2GNs(Input r0, float r1, Point r2, float r3, float[] r4, double r5, boolean[] r7, ValueAnimator r8) {
        r0.lambda$process$2(r1, r2, r3, r4, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$GYWuaylacIBqJMrVgLBpsYb7auo(Input r0, Path r1) {
        r0.lambda$paintPath$5(r1);
    }

    /* renamed from: $r8$lambda$L-MJHS-PxWQ9zuMpKE336YHXQgE, reason: not valid java name */
    public static /* synthetic */ void m11347$r8$lambda$LMJHSPxWQ9zuMpKE336YHXQgE(Input r0) {
        r0.lambda$process$3();
    }

    public static /* synthetic */ void $r8$lambda$YSZCbMMyMXrdHt8NCGZAF48nASE(Input r0, Shape r1) {
        r0.setShapeHelper(r1);
    }

    public static /* synthetic */ void $r8$lambda$oWrlCbaN01XPN3U9A_Wid6Av8J0(Input r0, Point r1, Brush r2, float r3, ValueAnimator r4) {
        r0.lambda$fill$0(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$reJ-eu7dDp5JaqDZ8vlPH3YxWpY, reason: not valid java name */
    public static /* synthetic */ void m11348$r8$lambda$reJeu7dDp5JaqDZ8vlPH3YxWpY(Input r0) {
        r0.lambda$new$1();
    }

    /* renamed from: $r8$lambda$y1Dixthxpa-EAoqAgUbPZ1WoStw, reason: not valid java name */
    public static /* synthetic */ void m11349$r8$lambda$y1DixthxpaEAoqAgUbPZ1WoStw(Input r0, Path r1) {
        r0.lambda$paintPath$4(r1);
    }

    /* renamed from: -$$Nest$fgetrenderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RenderView m11350$$Nest$fgetrenderView(Input r0) {
        return r0.renderView;
    }

    /* renamed from: -$$Nest$fputarrowAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11351$$Nest$fputarrowAnimator(Input r0, ValueAnimator r1) {
        r0.arrowAnimator = r1;
    }

    /* renamed from: -$$Nest$fputfillAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11352$$Nest$fputfillAnimator(Input r0, ValueAnimator r1) {
        r0.fillAnimator = r1;
    }

    static {
        PRESSURE_INTERPOLATOR = new CubicBezierInterpolator(0.0d, 0.5d, 0.0d, 1.0d);
    }

    public Input(RenderView r3) {
        this.points = new Point[3];
        this.tempPoint = new float[2];
        this.fillWithCurrentBrush = new Input$$ExternalSyntheticLambda2(this);
        this.renderView = r3;
        this.detector = new ShapeDetector(r3.getContext(), new Input$$ExternalSyntheticLambda3(this));
    }

    private void fill(Brush r13, boolean r14, Runnable r15) {
        if (this.canFill == true) goto L5;
        return;
    L5:
        if (this.renderView.getPainting().masking == false) goto L7;
        return;
    L7:
        if (this.lastLocation == null) goto L37;
        if (r13 != null) goto L12;
        r13 = this.renderView.getCurrentBrush();
    L12:
        if ((r13 instanceof Brush.Elliptical) == false) goto L14;
    L17:
        r13 = new Brush.Radial();
    L16:
        Brush r4 = r13;
        this.canFill = false;
        if ((r4 instanceof Brush.Eraser) == false) goto L21;
        this.renderView.getPainting().hasBlur = false;
    L21:
        this.renderView.getPainting().clearStroke();
        this.pointsCount = 0;
        this.realPointsCount = 0;
        this.lastAngleSet = false;
        this.beganDrawing = false;
        if (r14 == false) goto L24;
        this.renderView.onBeganDrawing();
    L24:
        Size r132 = this.renderView.getPainting().getSize();
        Point r0 = this.lastLocation;
        float r02 = MathUtils.distance((float) r0.x, (float) r0.y, 0.0f, 0.0f);
        Point r1 = this.lastLocation;
        float r03 = Math.max(r02, MathUtils.distance((float) r1.x, (float) r1.y, r132.width, 0.0f));
        Point r12 = this.lastLocation;
        float r16 = MathUtils.distance((float) r12.x, (float) r12.y, 0.0f, r132.height);
        Point r2 = this.lastLocation;
        float r3 = Math.max(r03, Math.max(r16, MathUtils.distance((float) r2.x, (float) r2.y, r132.width, r132.height))) / 0.84f;
        ValueAnimator r133 = this.arrowAnimator;
        if (r133 == null) goto L27;
        r133.cancel();
        this.arrowAnimator = null;
    L27:
        ValueAnimator r134 = this.fillAnimator;
        if (r134 == null) goto L30;
        r134.cancel();
        this.fillAnimator = null;
    L30:
        Point r135 = this.lastLocation;
        Point r22 = new Point(r135.x, r135.y, 1.0d);
        ValueAnimator r136 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.fillAnimator = r136;
        r136.addUpdateListener(new Input$$ExternalSyntheticLambda4(this, r22, r4, r3));
        this.fillAnimator.addListener(new AnonymousClass1(this, r22, r3, r4, r14, r15));
        this.fillAnimator.setDuration(450);
        this.fillAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.fillAnimator.start();
        if (r14 == false) goto L36;
        BotWebViewVibrationEffect.IMPACT_HEAVY.vibrate();
        return;
    L36:
        return;
    L14:
        if ((r13 instanceof Brush.Neon) == false) goto L16;
    }

    private /* synthetic */ void lambda$fill$0(Point r2, Brush r3, float r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        Path r0 = new Path(new Point[]{r2});
        if (r3.isEraser() == false) goto L5;
        int r22 = -1;
    L6:
        r0.setup(r22, r52 * r4, r3);
        this.renderView.getPainting().paintStroke(r0, true, true, null);
        return;
    L5:
        r22 = this.renderView.getCurrentColor();
        goto L6
    }

    private /* synthetic */ void lambda$new$1() {
        fill(null, true, null);
    }

    private /* synthetic */ void lambda$paintPath$4(Path r3) {
        this.lastRemainder = r3.remainder;
    }

    private /* synthetic */ void lambda$paintPath$5(Path r2) {
        AndroidUtilities.runOnUIThread(new Input$$ExternalSyntheticLambda6(this, r2));
    }

    private /* synthetic */ void lambda$process$2(float r37, Point r38, float r39, float[] r40, double r41, boolean[] r43, ValueAnimator r44) {
        float r2 = ((Float) r44.getAnimatedValue()).floatValue();
        double r3 = r37;
        double r7 = Math.cos(r3 - 2.5918139392115793d);
        double r11 = Math.sin(r3 - 2.748893571891069d);
        double r5 = r38.x;
        double r9 = r39;
        double r72 = r7 * r9;
        float r15 = r40[0];
        double r112 = r11 * r9;
        Point r14 = new Point(r5 + (r15 * r72), r38.y + (r15 * r112), r41);
        double r52 = r2;
        paintPath(new Path(new Point[]{r14, new Point(r38.x + (r72 * r52), r38.y + (r112 * r52), r41, true)}));
        double r32 = Math.cos(r3 + 2.5918139392115793d);
        double r73 = Math.sin(r3 + 2.748893571891069d);
        double r12 = r38.x;
        double r33 = r32 * r9;
        float r142 = r40[0];
        double r74 = r73 * r9;
        paintPath(new Path(new Point[]{new Point(r12 + (r33 * r142), r38.y + (r142 * r74), r41), new Point(r38.x + (r33 * r52), r38.y + (r74 * r52), r41, true)}));
        if (r43[0] == false) goto L5;
    L7:
        r40[0] = r2;
        return;
    L5:
        if (r2 <= 0.4f) goto L7;
        r43[0] = true;
        BotWebViewVibrationEffect.SELECTION_CHANGE.vibrate();
        goto L7
    }

    private /* synthetic */ void lambda$process$3() {
        Brush r0 = this.switchedBrushByStylusFrom;
        if (r0 == null) goto L6;
        this.renderView.selectBrush(r0);
        this.switchedBrushByStylusFrom = null;
        return;
    }

    private float lerpAngle(float r9, float r10, float r11) {
        double r0 = 1.0f - r11;
        double r8 = r9;
        double r4 = r11;
        double r102 = r10;
        return (float) Math.atan2((Math.sin(r8) * r0) + (Math.sin(r102) * r4), (r0 * Math.cos(r8)) + (r4 * Math.cos(r102)));
    }

    private void paintPath(Path r5) {
        r5.setup(this.renderView.getCurrentColor(), this.renderView.getCurrentWeight(), this.renderView.getCurrentBrush());
        if (this.clearBuffer == false) goto L5;
        this.lastRemainder = 0.0d;
    L5:
        r5.remainder = this.lastRemainder;
        this.renderView.getPainting().paintStroke(r5, this.clearBuffer, false, new Input$$ExternalSyntheticLambda5(this, r5));
        this.clearBuffer = false;
    }

    private void reset() {
        this.pointsCount = 0;
    }

    private void setShapeHelper(Shape r8) {
        if (r8 == null) goto L9;
        float r0 = this.renderView.getCurrentWeight();
        r8.thickness = r0;
        double r1 = this.thicknessSum;
        if (r1 <= 0.0d) goto L7;
        r8.thickness = (float) (r0 * (r1 / this.thicknessCount));
    L7:
        if (r8.getType() != 4) goto L9;
        r8.arrowTriangleLength *= r8.thickness;
    L9:
        this.renderView.getPainting().setHelperShape(r8);
    }

    private Point smoothPoint(Point r30, Point r31, Point r32, float r33, float r34) {
        float r5 = 1.0f - r33;
        double r6 = r5;
        double r14 = r33 * r33;
        double r4 = r5 * r5;
        double r3 = r33;
        return new Point(((r30.x * r4) + (((r32.x * 2.0d) * r3) * r6)) + (r31.x * r14), ((r30.y * r4) + (((r32.y * 2.0d) * r3) * r6)) + (r31.y * r14), (((((r30.z * Math.pow(r6, 2.0d)) + (r32.z * ((2.0f * r5) * r33))) + (r31.z * r14)) - 1.0d) * AndroidUtilities.lerp(r34, 1.0f, androidx.core.math.MathUtils.clamp(this.realPointsCount / 16.0f, 0.0f, 1.0f))) + 1.0d);
    }

    private void smoothenAndPaintPoints(boolean r17, float r18) {
        int r1 = this.pointsCount;
        boolean r6 = false;
        if (r1 <= 2) goto L24;
        Vector r8 = new Vector();
        Point[] r12 = this.points;
        Point r2 = r12[0];
        Point r3 = r12[1];
        Point r13 = r12[2];
        if (r13 == null) goto L29;
        if (r3 == null) goto L30;
        if (r2 == null) goto L31;
        Point r22 = r3.multiplySum(r2, 0.5d);
        Point r14 = r13.multiplySum(r3, 0.5d);
        int r10 = (int) Math.min(48.0d, Math.max(Math.floor(r22.getDistanceTo(r14) / 1.0f), 24.0d));
        float r11 = 1.0f / r10;
        float r4 = 0.0f;
        int r122 = 0;
    L10:
        if (r122 >= r10) goto L15;
        Point r5 = r22;
        Point r23 = r14;
        Point r132 = smoothPoint(r5, r23, r3, r4, r18);
        if (this.isFirst == false) goto L14;
        r132.edge = true;
        this.isFirst = r6;
    L14:
        r8.add(r132);
        this.thicknessSum += r132.z;
        this.thicknessCount += 1.0d;
        r4 = r4 + r11;
        r122 = r122 + 1;
        r22 = r5;
        r14 = r23;
        r6 = false;
        goto L10
    L15:
        Point r24 = r14;
        if (r17 == false) goto L18;
        r24.edge = true;
    L18:
        r8.add(r24);
        Point[] r15 = new Point[r8.size()];
        r8.toArray(r15);
        paintPath(new Path(r15));
        Point[] r16 = this.points;
        System.arraycopy(r16, 1, r16, 0, 2);
        if (r17 == false) goto L22;
        this.pointsCount = 0;
        return;
    L22:
        this.pointsCount = 2;
        return;
    L31:
        return;
    L30:
        return;
    L29:
        return;
    L24:
        Point[] r25 = new Point[r1];
        System.arraycopy(this.points, 0, r25, 0, r1);
        paintPath(new Path(r25));
    }

    public void clear(Runnable r9) {
        this.lastLocation = new Point(this.renderView.getPainting().getSize().width, 0.0d, 1.0d);
        this.canFill = true;
        fill(new Brush.Eraser(), false, r9);
    }

    public void ignoreOnce() {
        this.ignore = true;
    }

    public void process(MotionEvent r23, float r24) {
        if (this.fillAnimator == null) goto L5;
        return;
    L5:
        if (this.arrowAnimator != null) goto L123;
        int r2 = r23.getActionMasked();
        float r3 = r23.getX();
        float r4 = this.renderView.getHeight() - r23.getY();
        float[] r5 = this.tempPoint;
        r5[0] = r3;
        r5[1] = r4;
        this.invertMatrix.mapPoints(r5);
        float r42 = System.currentTimeMillis() - this.lastVelocityUpdate;
        this.velocity = androidx.core.math.MathUtils.clamp(this.velocity - (r42 / 125.0f), 0.6f, 1.0f);
        if (this.renderView.getCurrentBrush() != null) goto L10;
    L12:
        this.lastScale = r24;
        this.lastVelocityUpdate = System.currentTimeMillis();
        float r6 = this.velocity;
        if (r23.getToolType(r23.getActionIndex()) != 2) goto L17;
        r6 = Math.max(0.1f, PRESSURE_INTERPOLATOR.getInterpolation(r23.getPressure()));
        if ((r23.getButtonState() & 32) != 32) goto L17;
        boolean r8 = true;
    L19:
        if (this.renderView.getCurrentBrush() == null) goto L21;
        r6 = ((r6 - 1.0f) * AndroidUtilities.lerp(this.renderView.getCurrentBrush().getSmoothThicknessRate(), 1.0f, androidx.core.math.MathUtils.clamp(this.realPointsCount / 16.0f, 0.0f, 1.0f))) + 1.0f;
    L21:
        float[] r10 = this.tempPoint;
        boolean r232 = r8;
        Point r14 = new Point(r10[0], r10[1], r6);
        if (r2 == 0) goto L65;
        if (r2 == 1) goto L37;
        if (r2 == 2) goto L65;
        if (r2 == 3) goto L29;
        return;
    L29:
        if (this.ignore == false) goto L32;
        this.ignore = false;
        return;
    L32:
        this.canFill = false;
        this.detector.clear();
        this.renderView.getPainting().setHelperShape(null);
        AndroidUtilities.cancelRunOnUIThread(this.fillWithCurrentBrush);
        this.renderView.getPainting().clearStroke();
        this.pointsCount = 0;
        this.realPointsCount = 0;
        this.lastAngleSet = false;
        this.beganDrawing = false;
        this.thicknessSum = 0.0d;
        this.thicknessCount = 0.0d;
        Brush r0 = this.switchedBrushByStylusFrom;
        if (r0 == null) goto L122;
        this.renderView.selectBrush(r0);
        this.switchedBrushByStylusFrom = null;
        return;
    L122:
        return;
    L37:
        if (this.ignore == false) goto L40;
        this.ignore = false;
        return;
    L40:
        this.canFill = false;
        this.detector.clear();
        AndroidUtilities.cancelRunOnUIThread(this.fillWithCurrentBrush);
        if (this.renderView.getPainting().applyHelperShape() == false) goto L43;
    L62:
        this.pointsCount = 0;
        this.realPointsCount = 0;
        this.lastAngleSet = false;
        this.beganDrawing = false;
        this.thicknessSum = 0.0d;
        this.thicknessCount = 0.0d;
        this.renderView.onFinishedDrawing(this.hasMoved);
        return;
    L43:
        if (this.hasMoved == true) goto L49;
        if (this.renderView.shouldDraw() == false) goto L47;
        r14.edge = true;
        paintPath(new Path(r14));
    L47:
        reset();
    L61:
        this.renderView.getPainting().commitPath(null, this.renderView.getCurrentColor(), true, new Input$$ExternalSyntheticLambda1(this));
        goto L62
    L49:
        if (this.pointsCount <= 0) goto L61;
        smoothenAndPaintPoints(true, this.renderView.getCurrentBrush().getSmoothThicknessRate());
        if ((this.renderView.getCurrentBrush() instanceof Brush.Arrow) == false) goto L61;
        float r22 = this.lastAngle;
        Point r02 = this.points[this.pointsCount - 1];
        Point r43 = this.lastThickLocation;
        if (r43 != null) goto L56;
        double r44 = r14.z;
    L55:
        double r62 = r44;
        float r45 = (this.renderView.getCurrentWeight() * ((float) r62)) * 12.0f;
        ValueAnimator r52 = this.arrowAnimator;
        if (r52 == null) goto L60;
        r52.cancel();
    L60:
        ValueAnimator r102 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.arrowAnimator = r102;
        r102.addUpdateListener(new Input$$ExternalSyntheticLambda0(this, r22, r02, r45, new float[1], r62, new boolean[1]));
        this.arrowAnimator.addListener(new AnonymousClass2(this));
        this.arrowAnimator.setDuration(240);
        this.arrowAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.arrowAnimator.start();
        goto L62
    L56:
        r44 = r43.z;
    L65:
        if (this.ignore == false) goto L68;
        return;
    L68:
        if (this.beganDrawing == true) goto L71;
        this.beganDrawing = true;
        this.hasMoved = false;
        this.isFirst = true;
        this.lastLocation = r14;
        this.drawingStart = System.currentTimeMillis();
        this.points[0] = r14;
        this.pointsCount = 1;
        this.realPointsCount = 1;
        this.lastAngleSet = false;
        this.clearBuffer = true;
        this.canFill = true;
        AndroidUtilities.runOnUIThread(this.fillWithCurrentBrush, ViewConfiguration.getLongPressTimeout());
        return;
    L71:
        float r25 = r14.getDistanceTo(this.lastLocation);
        if (r25 >= (AndroidUtilities.dp(5.0f) / r24)) goto L75;
        return;
    L75:
        if (this.canFill == false) goto L82;
        if (r25 <= (AndroidUtilities.dp(6.0f) / r24)) goto L79;
    L80:
        this.canFill = false;
        AndroidUtilities.cancelRunOnUIThread(this.fillWithCurrentBrush);
        goto L82
    L79:
        if (this.pointsCount > 4) goto L80;
    L82:
        if (this.hasMoved == true) goto L88;
        this.renderView.onBeganDrawing();
        this.hasMoved = true;
        if (r232 == false) goto L88;
        if ((this.renderView.getCurrentBrush() instanceof Brush.Radial) == false) goto L88;
        this.switchedBrushByStylusFrom = this.renderView.getCurrentBrush();
        RenderView r7 = this.renderView;
        List<Brush> r12 = Brush.BRUSHES_LIST;
        r7.selectBrush(r12.get(r12.size() - 1));
    L88:
        this.points[this.pointsCount] = r14;
        if (this.renderView.getPainting() != null) goto L91;
    L94:
        char r72 = 2;
        if ((System.currentTimeMillis() - this.drawingStart) <= 3000) goto L98;
        this.detector.clear();
        this.renderView.getPainting().setHelperShape(null);
    L107:
        int r63 = this.pointsCount + 1;
        this.pointsCount = r63;
        this.realPointsCount++;
        if (r63 != 3) goto L116;
        Point[] r53 = this.points;
        Point r64 = r53[r72];
        double r73 = r64.y;
        Point r54 = r53[1];
        float r55 = (float) Math.atan2(r73 - r54.y, r64.x - r54.x);
        if (this.lastAngleSet == true) goto L112;
        this.lastAngle = r55;
        this.lastAngleSet = true;
    L115:
        smoothenAndPaintPoints(false, this.renderView.getCurrentBrush().getSmoothThicknessRate());
        goto L116
    L112:
        float r32 = androidx.core.math.MathUtils.clamp(r25 / (AndroidUtilities.dp(16.0f) / r24), 0.0f, 1.0f);
        if (r32 <= 0.4f) goto L115;
        this.lastAngle = lerpAngle(this.lastAngle, r55, r32);
    L116:
        this.lastLocation = r14;
        if (r25 <= (AndroidUtilities.dp(8.0f) / r24)) goto L119;
        this.lastThickLocation = r14;
    L119:
        this.velocity = androidx.core.math.MathUtils.clamp(this.velocity + (r42 / 75.0f), 0.6f, 1.0f);
        return;
    L98:
        if ((this.renderView.getCurrentBrush() instanceof Brush.Radial) == false) goto L100;
    L101:
        ShapeDetector r15 = this.detector;
        double r103 = r14.x;
        double r122 = r14.y;
        if (r25 <= (AndroidUtilities.dp(6.0f) / r24)) goto L105;
        boolean r20 = true;
    L106:
        r15.append(r103, r122, r20);
        goto L107
    L105:
        r20 = false;
        goto L106
    L100:
        if ((this.renderView.getCurrentBrush() instanceof Brush.Elliptical) == false) goto L107;
    L91:
        if (this.renderView.getPainting().masking == false) goto L94;
        r72 = 2;
    L17:
        r8 = false;
        goto L19
    L10:
        if ((this.renderView.getCurrentBrush() instanceof Brush.Arrow) == false) goto L12;
        this.velocity = 1.0f - this.velocity;
        goto L12
    }

    public void setMatrix(Matrix r2) {
        Matrix r0 = new Matrix();
        this.invertMatrix = r0;
        r2.invert(r0);
    }
}
