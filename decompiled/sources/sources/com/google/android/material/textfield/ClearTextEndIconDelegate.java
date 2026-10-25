package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;

/* loaded from: classes5.dex */
class ClearTextEndIconDelegate extends EndIconDelegate {
    private static final float ANIMATION_SCALE_FROM_VALUE = 0.8f;
    private static final int DEFAULT_ANIMATION_FADE_DURATION = 100;
    private static final int DEFAULT_ANIMATION_SCALE_DURATION = 150;
    private final int animationFadeDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationScaleDuration;
    private final TimeInterpolator animationScaleInterpolator;
    private EditText editText;
    private AnimatorSet iconInAnim;
    private ValueAnimator iconOutAnim;
    private final View.OnFocusChangeListener onFocusChangeListener;
    private final View.OnClickListener onIconClickListener;

    /* renamed from: com.google.android.material.textfield.ClearTextEndIconDelegate$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ClearTextEndIconDelegate this$0;

        public AnonymousClass1(ClearTextEndIconDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.endLayout.setEndIconVisible(true);
        }
    }

    /* renamed from: com.google.android.material.textfield.ClearTextEndIconDelegate$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ ClearTextEndIconDelegate this$0;

        public AnonymousClass2(ClearTextEndIconDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.endLayout.setEndIconVisible(false);
        }
    }

    public static /* synthetic */ void $r8$lambda$0hH44JQ_Ha7XcbZ_7uARPxidtUk(ClearTextEndIconDelegate r1, View r2) {
        EditText r0 = r1.editText;
        if (r0 != null) goto L5;
        return;
    L5:
        Editable r02 = r0.getText();
        if (r2.hasFocus() == false) goto L8;
        r1.editText.requestFocus();
    L8:
        if (r02 == null) goto L10;
        r02.clear();
    L10:
        r1.refreshIconState();
    }

    public static /* synthetic */ void $r8$lambda$2GhBDFS202lnXYe8K_Yk9MODhfY(ClearTextEndIconDelegate r0, View r1, boolean r2) {
        r0.animateIcon(r0.shouldBeVisible());
    }

    public static /* synthetic */ void $r8$lambda$o3nCOWRDa9a4Qn8eyddKdCWDWRM(ClearTextEndIconDelegate r0, ValueAnimator r1) {
        r0.getClass();
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        r0.endIconView.setAlpha(r12);
    }

    public static /* synthetic */ void $r8$lambda$uIZV0fdyhL28ssalFhIWtJjVwUo(ClearTextEndIconDelegate r1, ValueAnimator r2) {
        r1.getClass();
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        r1.endIconView.setScaleX(r22);
        r1.endIconView.setScaleY(r22);
    }

    public static /* synthetic */ void $r8$lambda$umzUqPTy4X6rZQaupl_00QyU43c(ClearTextEndIconDelegate r1) {
        r1.animateIcon(true);
    }

    public ClearTextEndIconDelegate(EndCompoundLayout r4) {
        super(r4);
        this.onIconClickListener = new ClearTextEndIconDelegate$$ExternalSyntheticLambda0(this);
        this.onFocusChangeListener = new ClearTextEndIconDelegate$$ExternalSyntheticLambda1(this);
        this.animationFadeDuration = MotionUtils.resolveThemeDuration(r4.getContext(), R.attr.motionDurationShort3, 100);
        this.animationScaleDuration = MotionUtils.resolveThemeDuration(r4.getContext(), R.attr.motionDurationShort3, 150);
        this.animationFadeInterpolator = MotionUtils.resolveThemeInterpolator(r4.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        this.animationScaleInterpolator = MotionUtils.resolveThemeInterpolator(r4.getContext(), R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
    }

    private void animateIcon(boolean r3) {
        if (this.endLayout.isEndIconVisible() != r3) goto L5;
        boolean r0 = true;
    L6:
        if (r3 == true) goto L8;
    L13:
        if (r3 == true) goto L19;
        this.iconInAnim.cancel();
        this.iconOutAnim.start();
        if (r0 == false) goto L20;
        this.iconOutAnim.end();
        return;
    L20:
        return;
    L19:
        return;
    L8:
        if (this.iconInAnim.isRunning() == true) goto L13;
        this.iconOutAnim.cancel();
        this.iconInAnim.start();
        if (r0 == false) goto L18;
        this.iconInAnim.end();
        return;
    L18:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private ValueAnimator getAlphaAnimator(float... r3) {
        ValueAnimator r32 = ValueAnimator.ofFloat(r3);
        r32.setInterpolator(this.animationFadeInterpolator);
        r32.setDuration(this.animationFadeDuration);
        r32.addUpdateListener(new ClearTextEndIconDelegate$$ExternalSyntheticLambda2(this));
        return r32;
    }

    private ValueAnimator getScaleAnimator() {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{ANIMATION_SCALE_FROM_VALUE, 1.0f});
        r0.setInterpolator(this.animationScaleInterpolator);
        r0.setDuration(this.animationScaleDuration);
        r0.addUpdateListener(new ClearTextEndIconDelegate$$ExternalSyntheticLambda4(this));
        return r0;
    }

    private void initAnimators() {
        ValueAnimator r0 = getScaleAnimator();
        ValueAnimator r2 = getAlphaAnimator(new float[]{0.0f, 1.0f});
        AnimatorSet r3 = new AnimatorSet();
        this.iconInAnim = r3;
        r3.playTogether(new Animator[]{r0, r2});
        this.iconInAnim.addListener(new AnonymousClass1(this));
        ValueAnimator r02 = getAlphaAnimator(new float[]{1.0f, 0.0f});
        this.iconOutAnim = r02;
        r02.addListener(new AnonymousClass2(this));
    }

    private boolean shouldBeVisible() {
        EditText r0 = this.editText;
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r0.hasFocus() == false) goto L8;
    L11:
        boolean r02 = true;
    L13:
        if (this.editText.getText().length() <= 0) goto L15;
        boolean r3 = true;
    L17:
        if (this.endLayout.getSuffixText() == null) goto L19;
        boolean r4 = true;
    L20:
        if (r02 == false) goto L24;
        if (r3 == true) goto L23;
        if (r4 == false) goto L24;
    L23:
        return true;
    L24:
        return false;
    L19:
        r4 = false;
        goto L20
    L15:
        r3 = false;
        goto L17
    L8:
        if (this.endIconView.hasFocus() == true) goto L11;
        r02 = false;
        goto L13
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void afterEditTextChanged(Editable r1) {
        if (this.endLayout.getSuffixText() == null) goto L5;
        return;
    L5:
        animateIcon(shouldBeVisible());
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public int getIconContentDescriptionResId() {
        return R.string.clear_text_end_icon_content_description;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public int getIconDrawableResId() {
        return R.drawable.mtrl_ic_cancel;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return this.onFocusChangeListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public View.OnFocusChangeListener getOnIconViewFocusChangeListener() {
        return this.onFocusChangeListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void onEditTextAttached(EditText r1) {
        this.editText = r1;
        this.textInputLayout.setEndIconVisible(shouldBeVisible());
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void onSuffixVisibilityChanged(boolean r2) {
        if (this.endLayout.getSuffixText() != null) goto L5;
        return;
    L5:
        animateIcon(r2);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void setUp() {
        initAnimators();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void tearDown() {
        EditText r0 = this.editText;
        if (r0 == null) goto L6;
        r0.post(new ClearTextEndIconDelegate$$ExternalSyntheticLambda3(this));
        return;
    }
}
