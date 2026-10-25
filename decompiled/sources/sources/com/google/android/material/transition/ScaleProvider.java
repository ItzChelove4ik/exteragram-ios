package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes5.dex */
public final class ScaleProvider implements VisibilityAnimatorProvider {
    private boolean growing;
    private float incomingEndScale;
    private float incomingStartScale;
    private float outgoingEndScale;
    private float outgoingStartScale;
    private boolean scaleOnDisappear;

    /* renamed from: com.google.android.material.transition.ScaleProvider$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ float val$originalScaleX;
        final /* synthetic */ float val$originalScaleY;
        final /* synthetic */ View val$view;

        public AnonymousClass1(View r1, float r2, float r3) {
            this.val$view = r1;
            this.val$originalScaleX = r2;
            this.val$originalScaleY = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.val$view.setScaleX(this.val$originalScaleX);
            this.val$view.setScaleY(this.val$originalScaleY);
        }
    }

    public ScaleProvider() {
        this(true);
    }

    public ScaleProvider(boolean r3) {
        this.outgoingStartScale = 1.0f;
        this.outgoingEndScale = 1.1f;
        this.incomingStartScale = 0.8f;
        this.incomingEndScale = 1.0f;
        this.scaleOnDisappear = true;
        this.growing = r3;
    }

    private static Animator createScaleAnimator(View r8, float r9, float r10) {
        float r0 = r8.getScaleX();
        float r1 = r8.getScaleY();
        ObjectAnimator r92 = ObjectAnimator.ofPropertyValuesHolder(r8, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{r0 * r9, r0 * r10}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{r9 * r1, r10 * r1})});
        r92.addListener(new AnonymousClass1(r8, r0, r1));
        return r92;
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup r1, View r2) {
        if (this.growing == false) goto L7;
        return createScaleAnimator(r2, this.incomingStartScale, this.incomingEndScale);
    L7:
        return createScaleAnimator(r2, this.outgoingEndScale, this.outgoingStartScale);
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup r1, View r2) {
        if (this.scaleOnDisappear == true) goto L7;
        return null;
    L7:
        if (this.growing == false) goto L11;
        return createScaleAnimator(r2, this.outgoingStartScale, this.outgoingEndScale);
    L11:
        return createScaleAnimator(r2, this.incomingEndScale, this.incomingStartScale);
    }

    public float getIncomingEndScale() {
        return this.incomingEndScale;
    }

    public float getIncomingStartScale() {
        return this.incomingStartScale;
    }

    public float getOutgoingEndScale() {
        return this.outgoingEndScale;
    }

    public float getOutgoingStartScale() {
        return this.outgoingStartScale;
    }

    public boolean isGrowing() {
        return this.growing;
    }

    public boolean isScaleOnDisappear() {
        return this.scaleOnDisappear;
    }

    public void setGrowing(boolean r1) {
        this.growing = r1;
    }

    public void setIncomingEndScale(float r1) {
        this.incomingEndScale = r1;
    }

    public void setIncomingStartScale(float r1) {
        this.incomingStartScale = r1;
    }

    public void setOutgoingEndScale(float r1) {
        this.outgoingEndScale = r1;
    }

    public void setOutgoingStartScale(float r1) {
        this.outgoingStartScale = r1;
    }

    public void setScaleOnDisappear(boolean r1) {
        this.scaleOnDisappear = r1;
    }
}
