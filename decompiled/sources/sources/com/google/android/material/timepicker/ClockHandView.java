package com.google.android.material.timepicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.math.MathUtils;
import com.google.android.material.motion.MotionUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
class ClockHandView extends View {
    private static final int DEFAULT_ANIMATION_DURATION = 200;
    private boolean animatingOnTouchUp;
    private final int animationDuration;
    private final TimeInterpolator animationInterpolator;
    private final float centerDotRadius;
    private boolean changedDuringTouch;
    private int circleRadius;
    private int currentLevel;
    private double degRad;
    private float downX;
    private float downY;
    private boolean isInTapRegion;
    private boolean isMultiLevel;
    private final List<OnRotateListener> listeners;
    private OnActionUpListener onActionUpListener;
    private float originalDeg;
    private final Paint paint;
    private final ValueAnimator rotationAnimator;
    private final int scaledTouchSlop;
    private final RectF selectorBox;
    private final int selectorRadius;
    private final int selectorStrokeWidth;

    /* renamed from: com.google.android.material.timepicker.ClockHandView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ClockHandView this$0;

        public AnonymousClass1(ClockHandView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            r1.end();
        }
    }

    public interface OnActionUpListener {
        void onActionUp(float r1, boolean r2);
    }

    public interface OnRotateListener {
        void onRotate(float r1, boolean r2);
    }

    public static /* synthetic */ void $r8$lambda$PRvqYbjc7A5gQVhxbWP7FwHQ_H4(ClockHandView r1, ValueAnimator r2) {
        r1.getClass();
        r1.setHandRotationInternal(((Float) r2.getAnimatedValue()).floatValue(), true);
    }

    public ClockHandView(Context r2) {
        this(r2, null);
    }

    public ClockHandView(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialClockStyle);
    }

    public ClockHandView(Context r5, AttributeSet r6, int r7) {
        super(r5, r6, r7);
        this.rotationAnimator = new ValueAnimator();
        this.listeners = new ArrayList();
        Paint r0 = new Paint();
        this.paint = r0;
        this.selectorBox = new RectF();
        this.currentLevel = 1;
        TypedArray r62 = r5.obtainStyledAttributes(r6, R.styleable.ClockHandView, r7, R.style.Widget_MaterialComponents_TimePicker_Clock);
        this.animationDuration = MotionUtils.resolveThemeDuration(r5, R.attr.motionDurationLong2, 200);
        this.animationInterpolator = MotionUtils.resolveThemeInterpolator(r5, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.circleRadius = r62.getDimensionPixelSize(R.styleable.ClockHandView_materialCircleRadius, 0);
        this.selectorRadius = r62.getDimensionPixelSize(R.styleable.ClockHandView_selectorSize, 0);
        this.selectorStrokeWidth = getResources().getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width);
        this.centerDotRadius = r7.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius);
        int r72 = r62.getColor(R.styleable.ClockHandView_clockHandColor, 0);
        r0.setAntiAlias(true);
        r0.setColor(r72);
        setHandRotation(0.0f);
        this.scaledTouchSlop = ViewConfiguration.get(r5).getScaledTouchSlop();
        setImportantForAccessibility(2);
        r62.recycle();
        initRotationAnimator();
    }

    private void adjustLevel(float r4, float r5) {
        int r1 = 2;
        if (MathUtils.dist(getWidth() / 2, getHeight() / 2, r4, r5) <= (getLeveledCircleRadius(2) + ViewUtils.dpToPx(getContext(), 12))) goto L6;
        r1 = 1;
    L6:
        this.currentLevel = r1;
    }

    private void drawSelector(Canvas r11) {
        int r0 = getHeight() / 2;
        int r1 = getWidth() / 2;
        float r4 = r1;
        float r3 = getLeveledCircleRadius(this.currentLevel);
        float r5 = (((float) Math.cos(this.degRad)) * r3) + r4;
        float r52 = r0;
        float r32 = (r3 * ((float) Math.sin(this.degRad))) + r52;
        this.paint.setStrokeWidth(0.0f);
        r11.drawCircle(r5, r32, this.selectorRadius, this.paint);
        double r6 = Math.sin(this.degRad);
        this.paint.setStrokeWidth(this.selectorStrokeWidth);
        r11.drawLine(r4, r52, r1 + ((int) (Math.cos(this.degRad) * r2)), r0 + ((int) (r2 * r6)), this.paint);
        r11.drawCircle(r4, r52, this.centerDotRadius, this.paint);
    }

    private int getDegreesFromXY(float r3, float r4) {
        int r0 = getWidth() / 2;
        int r2 = (int) Math.toDegrees(Math.atan2(r4 - (getHeight() / 2), r3 - r0));
        int r32 = r2 + 90;
        if (r32 < 0) goto L5;
        return r32;
    L5:
        return r2 + 450;
    }

    private int getLeveledCircleRadius(int r2) {
        int r1 = this.circleRadius;
        if (r2 == 2) goto L5;
        return r1;
    L5:
        return Math.round(r1 * 0.66f);
    }

    private Pair<Float, Float> getValuesForAnimation(float r4) {
        float r3 = getHandRotation();
        if (Math.abs(r3 - r4) <= 180.0f) goto L15;
        if (r3 <= 180.0f) goto L10;
        if (r4 >= 180.0f) goto L10;
        r4 = r4 + 360.0f;
    L10:
        if (r3 >= 180.0f) goto L15;
        if (r4 <= 180.0f) goto L15;
        r3 = r3 + 360.0f;
    L15:
        return new Pair(Float.valueOf(r3), Float.valueOf(r4));
    }

    private boolean handleTouchInput(float r3, float r4, boolean r5, boolean r6, boolean r7) {
        float r32 = getDegreesFromXY(r3, r4);
        boolean r0 = false;
        if (getHandRotation() == r32) goto L5;
        boolean r42 = true;
    L6:
        if (r6 == false) goto L9;
        if (r42 == false) goto L9;
        return true;
    L9:
        if (r42 == true) goto L13;
        if (r5 == true) goto L13;
        return false;
    L13:
        if (r7 == true) goto L15;
    L17:
        setHandRotation(r32, r0);
        return true;
    L15:
        if (this.animatingOnTouchUp == false) goto L17;
        r0 = true;
        goto L17
    L5:
        r42 = false;
        goto L6
    }

    private void initRotationAnimator() {
        this.rotationAnimator.addUpdateListener(new ClockHandView$$ExternalSyntheticLambda0(this));
        this.rotationAnimator.addListener(new AnonymousClass1(this));
    }

    private void setHandRotationInternal(float r8, boolean r9) {
        float r82 = r8 % 360.0f;
        this.originalDeg = r82;
        this.degRad = Math.toRadians(r82 - 90.0f);
        int r0 = getHeight() / 2;
        int r1 = getWidth() / 2;
        float r2 = getLeveledCircleRadius(this.currentLevel);
        float r12 = r1 + (((float) Math.cos(this.degRad)) * r2);
        float r02 = r0 + (r2 * ((float) Math.sin(this.degRad)));
        RectF r22 = this.selectorBox;
        int r3 = this.selectorRadius;
        r22.set(r12 - r3, r02 - r3, r12 + r3, r02 + r3);
        Iterator<OnRotateListener> r03 = this.listeners.iterator();
    L4:
        if (r03.hasNext() == false) goto L6;
        r03.next().onRotate(r82, r9);
        goto L4
    L6:
        invalidate();
    }

    public void addOnRotateListener(OnRotateListener r1) {
        this.listeners.add(r1);
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public RectF getCurrentSelectorBox() {
        return this.selectorBox;
    }

    public float getHandRotation() {
        return this.originalDeg;
    }

    public int getSelectorRadius() {
        return this.selectorRadius;
    }

    @Override // android.view.View
    public void onDraw(Canvas r1) {
        super.onDraw(r1);
        drawSelector(r1);
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (this.rotationAnimator.isRunning() == true) goto L6;
        setHandRotation(getHandRotation());
        return;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r8) {
        int r0 = r8.getActionMasked();
        float r2 = r8.getX();
        float r3 = r8.getY();
        if (r0 == 0) goto L20;
        if (r0 != 1) goto L6;
    L8:
        int r4 = (int) (r2 - this.downX);
        int r5 = (int) (r3 - this.downY);
        if (((r4 * r4) + (r5 * r5)) <= this.scaledTouchSlop) goto L11;
        boolean r42 = true;
    L12:
        this.isInTapRegion = r42;
        boolean r43 = this.changedDuringTouch;
        if (r0 != 1) goto L15;
        boolean r02 = true;
    L17:
        if (this.isMultiLevel == false) goto L19;
        adjustLevel(r2, r3);
    L19:
        boolean r6 = r02;
        boolean r52 = false;
    L21:
        boolean r7 = handleTouchInput(r2, r3, r43, r52, r6) | this.changedDuringTouch;
        this.changedDuringTouch = r7;
        if (r7 == false) goto L27;
        if (r6 == false) goto L27;
        OnActionUpListener r72 = this.onActionUpListener;
        if (r72 == null) goto L27;
        r72.onActionUp(getDegreesFromXY(r2, r3), this.isInTapRegion);
    L27:
        return true;
    L15:
        r02 = false;
        goto L17
    L11:
        r42 = false;
        goto L12
    L6:
        if (r0 == 2) goto L8;
        r43 = false;
        r52 = false;
        r6 = false;
        goto L21
    L20:
        this.downX = r2;
        this.downY = r3;
        this.isInTapRegion = true;
        this.changedDuringTouch = false;
        r52 = true;
        r43 = false;
        r6 = false;
        goto L21
    }

    public void setAnimateOnTouchUp(boolean r1) {
        this.animatingOnTouchUp = r1;
    }

    public void setCircleRadius(int r1) {
        this.circleRadius = r1;
        invalidate();
    }

    public void setCurrentLevel(int r1) {
        this.currentLevel = r1;
        invalidate();
    }

    public void setHandRotation(float r2) {
        setHandRotation(r2, false);
    }

    public void setHandRotation(float r4, boolean r5) {
        this.rotationAnimator.cancel();
        if (r5 == true) goto L6;
        setHandRotationInternal(r4, false);
        return;
    L6:
        Pair<Float, Float> r42 = getValuesForAnimation(r4);
        this.rotationAnimator.setFloatValues(new float[]{((Float) r42.first).floatValue(), ((Float) r42.second).floatValue()});
        this.rotationAnimator.setDuration(this.animationDuration);
        this.rotationAnimator.setInterpolator(this.animationInterpolator);
        this.rotationAnimator.start();
    }

    public void setMultiLevel(boolean r2) {
        if (this.isMultiLevel == false) goto L6;
        if (r2 == true) goto L6;
        this.currentLevel = 1;
    L6:
        this.isMultiLevel = r2;
        invalidate();
    }

    public void setOnActionUpListener(OnActionUpListener r1) {
        this.onActionUpListener = r1;
    }
}
