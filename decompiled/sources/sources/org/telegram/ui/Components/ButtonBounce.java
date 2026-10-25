package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/* loaded from: classes3.dex */
public class ButtonBounce {
    private Runnable additionalInvalidate;
    private ValueAnimator animator;
    private final float durationPressMultiplier;
    private final float durationReleaseMultiplier;
    private boolean isPressed;
    private final float overshoot;
    private float pressedT;
    private long releaseDelay;
    public View view;

    /* renamed from: org.telegram.ui.Components.ButtonBounce$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ButtonBounce this$0;
        final /* synthetic */ boolean val$pressed;

        public AnonymousClass1(ButtonBounce r1, boolean r2) {
            this.this$0 = r1;
            this.val$pressed = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 != ButtonBounce.m9097$$Nest$fgetanimator(this.this$0)) goto L10;
            ButtonBounce.m9098$$Nest$fputanimator(this.this$0, null);
            ButtonBounce r22 = this.this$0;
            if (this.val$pressed == false) goto L7;
            float r0 = 1.0f;
        L8:
            ButtonBounce.m9099$$Nest$fputpressedT(r22, r0);
            this.this$0.invalidate();
            return;
        L7:
            r0 = 0.0f;
            goto L8
        }
    }

    /* renamed from: $r8$lambda$2WVLUe5Go-p8HgUaYuR_46g1z4c, reason: not valid java name */
    public static /* synthetic */ void m9096$r8$lambda$2WVLUe5Gop8HgUaYuR_46g1z4c(ButtonBounce r0, ValueAnimator r1) {
        r0.lambda$setPressed$0(r1);
    }

    /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m9097$$Nest$fgetanimator(ButtonBounce r0) {
        return r0.animator;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9098$$Nest$fputanimator(ButtonBounce r0, ValueAnimator r1) {
        r0.animator = r1;
    }

    /* renamed from: -$$Nest$fputpressedT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9099$$Nest$fputpressedT(ButtonBounce r0, float r1) {
        r0.pressedT = r1;
    }

    public ButtonBounce(View r3) {
        this(r3, 1.0f, 5.0f);
    }

    public ButtonBounce(View r3, float r4, float r5) {
        this.releaseDelay = 0;
        this.view = r3;
        this.durationReleaseMultiplier = r4;
        this.durationPressMultiplier = r4;
        this.overshoot = r5;
    }

    public ButtonBounce(View r3, float r4, float r5, float r6) {
        this.releaseDelay = 0;
        this.view = r3;
        this.durationPressMultiplier = r4;
        this.durationReleaseMultiplier = r5;
        this.overshoot = r6;
    }

    private /* synthetic */ void lambda$setPressed$0(ValueAnimator r1) {
        this.pressedT = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    public float getScale(float r3) {
        return (1.0f - r3) + (r3 * (1.0f - this.pressedT));
    }

    public View getView() {
        return this.view;
    }

    public void invalidate() {
        View r0 = this.view;
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        Runnable r1 = this.additionalInvalidate;
        if (r1 == null) goto L9;
        r1.run();
        return;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public void setAdditionalInvalidate(Runnable r1) {
        this.additionalInvalidate = r1;
    }

    public void setPressed(boolean r5) {
        if (this.isPressed == r5) goto L17;
        this.isPressed = r5;
        ValueAnimator r0 = this.animator;
        this.animator = null;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        float r02 = this.pressedT;
        if (r5 == false) goto L10;
        float r1 = 1.0f;
    L11:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.animator = r03;
        r03.addUpdateListener(new ButtonBounce$$ExternalSyntheticLambda0(this));
        this.animator.addListener(new AnonymousClass1(this, r5));
        boolean r52 = this.isPressed;
        ValueAnimator r04 = this.animator;
        if (r52 == false) goto L14;
        r04.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animator.setDuration((long) (this.durationPressMultiplier * 60.0f));
        this.animator.setStartDelay(0);
    L15:
        this.animator.start();
        return;
    L14:
        r04.setInterpolator(new OvershootInterpolator(this.overshoot));
        this.animator.setDuration((long) (this.durationReleaseMultiplier * 350.0f));
        this.animator.setStartDelay(this.releaseDelay);
        goto L15
    L10:
        r1 = 0.0f;
        goto L11
    }

    public ButtonBounce setReleaseDelay(long r1) {
        this.releaseDelay = r1;
        return this;
    }

    public void setView(View r1) {
        this.view = r1;
    }
}
