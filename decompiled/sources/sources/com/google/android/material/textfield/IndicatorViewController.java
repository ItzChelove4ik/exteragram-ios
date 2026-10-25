package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
final class IndicatorViewController {
    private static final int CAPTION_STATE_ERROR = 1;
    private static final int CAPTION_STATE_HELPER_TEXT = 2;
    private static final int CAPTION_STATE_NONE = 0;
    static final int COUNTER_INDEX = 2;
    private static final int DEFAULT_CAPTION_FADE_ANIMATION_DURATION = 167;
    private static final int DEFAULT_CAPTION_TRANSLATION_Y_ANIMATION_DURATION = 217;
    static final int ERROR_INDEX = 0;
    static final int HELPER_INDEX = 1;
    private Animator captionAnimator;
    private FrameLayout captionArea;
    private int captionDisplayed;
    private final int captionFadeInAnimationDuration;
    private final TimeInterpolator captionFadeInAnimationInterpolator;
    private final int captionFadeOutAnimationDuration;
    private final TimeInterpolator captionFadeOutAnimationInterpolator;
    private int captionToShow;
    private final int captionTranslationYAnimationDuration;
    private final TimeInterpolator captionTranslationYAnimationInterpolator;
    private final float captionTranslationYPx;
    private final Context context;
    private boolean errorEnabled;
    private CharSequence errorText;
    private int errorTextAppearance;
    private TextView errorView;
    private int errorViewAccessibilityLiveRegion;
    private CharSequence errorViewContentDescription;
    private ColorStateList errorViewTextColor;
    private CharSequence helperText;
    private boolean helperTextEnabled;
    private int helperTextTextAppearance;
    private TextView helperTextView;
    private ColorStateList helperTextViewTextColor;
    private LinearLayout indicatorArea;
    private int indicatorsAdded;
    private final TextInputLayout textInputView;
    private Typeface typeface;

    /* renamed from: com.google.android.material.textfield.IndicatorViewController$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ IndicatorViewController this$0;
        final /* synthetic */ int val$captionToHide;
        final /* synthetic */ int val$captionToShow;
        final /* synthetic */ TextView val$captionViewToHide;
        final /* synthetic */ TextView val$captionViewToShow;

        public AnonymousClass1(IndicatorViewController r1, int r2, TextView r3, int r4, TextView r5) {
            this.this$0 = r1;
            this.val$captionToShow = r2;
            this.val$captionViewToHide = r3;
            this.val$captionToHide = r4;
            this.val$captionViewToShow = r5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            IndicatorViewController.access$002(this.this$0, this.val$captionToShow);
            IndicatorViewController.access$102(this.this$0, null);
            TextView r32 = this.val$captionViewToHide;
            if (r32 == null) goto L9;
            r32.setVisibility(4);
            if (this.val$captionToHide != 1) goto L9;
            if (IndicatorViewController.access$200(this.this$0) == null) goto L9;
            IndicatorViewController.access$200(this.this$0).setText(null);
        L9:
            TextView r33 = this.val$captionViewToShow;
            if (r33 == null) goto L13;
            r33.setTranslationY(0.0f);
            this.val$captionViewToShow.setAlpha(1.0f);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            TextView r22 = this.val$captionViewToShow;
            if (r22 == null) goto L6;
            r22.setVisibility(0);
            this.val$captionViewToShow.setAlpha(0.0f);
            return;
        }
    }

    public IndicatorViewController(TextInputLayout r3) {
        Context r0 = r3.getContext();
        this.context = r0;
        this.textInputView = r3;
        this.captionTranslationYPx = r0.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
        this.captionTranslationYAnimationDuration = MotionUtils.resolveThemeDuration(r0, R.attr.motionDurationShort4, 217);
        this.captionFadeInAnimationDuration = MotionUtils.resolveThemeDuration(r0, R.attr.motionDurationMedium4, 167);
        this.captionFadeOutAnimationDuration = MotionUtils.resolveThemeDuration(r0, R.attr.motionDurationShort4, 167);
        this.captionTranslationYAnimationInterpolator = MotionUtils.resolveThemeInterpolator(r0, R.attr.motionEasingEmphasizedDecelerateInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        int r32 = R.attr.motionEasingEmphasizedDecelerateInterpolator;
        TimeInterpolator r1 = AnimationUtils.LINEAR_INTERPOLATOR;
        this.captionFadeInAnimationInterpolator = MotionUtils.resolveThemeInterpolator(r0, r32, r1);
        this.captionFadeOutAnimationInterpolator = MotionUtils.resolveThemeInterpolator(r0, R.attr.motionEasingLinearInterpolator, r1);
    }

    public static /* synthetic */ int access$002(IndicatorViewController r0, int r1) {
        r0.captionDisplayed = r1;
        return r1;
    }

    public static /* synthetic */ Animator access$102(IndicatorViewController r0, Animator r1) {
        r0.captionAnimator = r1;
        return r1;
    }

    public static /* synthetic */ TextView access$200(IndicatorViewController r0) {
        return r0.errorView;
    }

    private boolean canAdjustIndicatorPadding() {
        if (this.indicatorArea != null) goto L5;
        return false;
    L5:
        if (this.textInputView.getEditText() == null) goto L10;
        return true;
    L10:
        return false;
    }

    private void createCaptionAnimators(List<Animator> r3, boolean r4, TextView r5, int r6, int r7, int r8) {
        if (r5 == null) goto L21;
        if (r4 == false) goto L24;
        if (r6 == r8) goto L9;
        if (r6 == r7) goto L9;
        return;
    L9:
        if (r8 != r6) goto L11;
        boolean r42 = true;
    L12:
        ObjectAnimator r43 = createCaptionOpacityAnimator(r5, r42);
        if (r6 != r8) goto L16;
        if (r7 == 0) goto L16;
        r43.setStartDelay(this.captionFadeOutAnimationDuration);
    L16:
        r3.add(r43);
        if (r8 != r6) goto L22;
        if (r7 == 0) goto L23;
        ObjectAnimator r44 = createCaptionTranslationYAnimator(r5);
        r44.setStartDelay(this.captionFadeOutAnimationDuration);
        r3.add(r44);
        return;
    L23:
        return;
    L22:
        return;
    L11:
        r42 = false;
        goto L12
    L24:
        return;
    }

    private ObjectAnimator createCaptionOpacityAnimator(TextView r5, boolean r6) {
        if (r6 == false) goto L4;
        float r0 = 1.0f;
    L5:
        ObjectAnimator r52 = ObjectAnimator.ofFloat(r5, View.ALPHA, new float[]{r0});
        if (r6 == false) goto L9;
        int r02 = this.captionFadeInAnimationDuration;
    L10:
        r52.setDuration(r02);
        if (r6 == false) goto L13;
        TimeInterpolator r4 = this.captionFadeInAnimationInterpolator;
    L14:
        r52.setInterpolator(r4);
        return r52;
    L13:
        r4 = this.captionFadeOutAnimationInterpolator;
        goto L14
    L9:
        r02 = this.captionFadeOutAnimationDuration;
        goto L10
    L4:
        r0 = 0.0f;
        goto L5
    }

    private ObjectAnimator createCaptionTranslationYAnimator(TextView r5) {
        ObjectAnimator r52 = ObjectAnimator.ofFloat(r5, View.TRANSLATION_Y, new float[]{-this.captionTranslationYPx, 0.0f});
        r52.setDuration(this.captionTranslationYAnimationDuration);
        r52.setInterpolator(this.captionTranslationYAnimationInterpolator);
        return r52;
    }

    private TextView getCaptionViewFromDisplayState(int r2) {
        if (r2 == 1) goto L11;
        if (r2 == 2) goto L9;
        return null;
    L9:
        return this.helperTextView;
    L11:
        return this.errorView;
    }

    private int getIndicatorPadding(boolean r1, int r2, int r3) {
        if (r1 == true) goto L4;
        return r3;
    L4:
        return this.context.getResources().getDimensionPixelSize(r2);
    }

    private boolean isCaptionStateError(int r2) {
        if (r2 == 1) goto L5;
        return false;
    L5:
        if (this.errorView != null) goto L7;
        return false;
    L7:
        if (TextUtils.isEmpty(this.errorText) == true) goto L12;
        return true;
    L12:
        return false;
    }

    private boolean isCaptionStateHelperText(int r2) {
        if (r2 == 2) goto L5;
        return false;
    L5:
        if (this.helperTextView != null) goto L7;
        return false;
    L7:
        if (TextUtils.isEmpty(this.helperText) == true) goto L13;
        return true;
    L13:
        return false;
    }

    private void setCaptionViewVisibilities(int r3, int r4) {
        if (r3 != r4) goto L4;
        return;
    L4:
        if (r4 == 0) goto L8;
        TextView r0 = getCaptionViewFromDisplayState(r4);
        if (r0 == null) goto L8;
        r0.setVisibility(0);
        r0.setAlpha(1.0f);
    L8:
        if (r3 == 0) goto L14;
        TextView r02 = getCaptionViewFromDisplayState(r3);
        if (r02 == null) goto L14;
        r02.setVisibility(4);
        if (r3 != 1) goto L14;
        r02.setText(null);
    L14:
        this.captionDisplayed = r4;
    }

    private void setTextViewTypeface(TextView r1, Typeface r2) {
        if (r1 == null) goto L5;
        r1.setTypeface(r2);
        return;
    }

    private void setViewGroupGoneIfEmpty(ViewGroup r1, int r2) {
        if (r2 != 0) goto L5;
        r1.setVisibility(8);
        return;
    }

    private boolean shouldAnimateCaptionView(TextView r2, CharSequence r3) {
        if (this.textInputView.isLaidOut() == true) goto L5;
        return false;
    L5:
        if (this.textInputView.isEnabled() == true) goto L7;
        return false;
    L7:
        if (this.captionToShow != this.captionDisplayed) goto L11;
        if (r2 != null) goto L10;
        return true;
    L10:
        if (TextUtils.equals(r2.getText(), r3) == true) goto L18;
        return true;
    L18:
        return false;
    L11:
        return true;
    }

    private void updateCaptionViewsVisibility(int r13, int r14, boolean r15) {
        if (r13 != r14) goto L4;
        return;
    L4:
        if (r15 == false) goto L6;
        AnimatorSet r0 = new AnimatorSet();
        this.captionAnimator = r0;
        ArrayList r2 = new ArrayList();
        IndicatorViewController r1 = this;
        r1.createCaptionAnimators(r2, this.helperTextEnabled, this.helperTextView, 2, r13, r14);
        r1.createCaptionAnimators(r2, r1.errorEnabled, r1.errorView, 1, r13, r14);
        AnimatorSetCompat.playTogether(r0, r2);
        r0.addListener(new AnonymousClass1(r1, r14, r1.getCaptionViewFromDisplayState(r13), r13, r1.getCaptionViewFromDisplayState(r14)));
        r0.start();
    L7:
        r1.textInputView.updateEditTextBackground();
        r1.textInputView.updateLabelState(r15);
        r1.textInputView.updateTextInputBoxState();
        return;
    L6:
        r1 = this;
        r1.setCaptionViewVisibilities(r13, r14);
        goto L7
    }

    public void addIndicator(TextView r6, int r7) {
        if (this.indicatorArea != null) goto L10;
        if (this.captionArea != null) goto L10;
        LinearLayout r0 = new LinearLayout(this.context);
        this.indicatorArea = r0;
        r0.setOrientation(0);
        this.textInputView.addView(this.indicatorArea, -1, -2);
        this.captionArea = new FrameLayout(this.context);
        this.indicatorArea.addView(this.captionArea, new LinearLayout.LayoutParams(0, -2, 1.0f));
        if (this.textInputView.getEditText() == null) goto L10;
        adjustIndicatorPadding();
    L10:
        if (isCaptionView(r7) == false) goto L12;
        this.captionArea.setVisibility(0);
        this.captionArea.addView(r6);
    L13:
        this.indicatorArea.setVisibility(0);
        this.indicatorsAdded++;
        return;
    L12:
        this.indicatorArea.addView(r6, new LinearLayout.LayoutParams(-2, -2));
        goto L13
    }

    public void adjustIndicatorPadding() {
        if (canAdjustIndicatorPadding() == false) goto L6;
        EditText r0 = this.textInputView.getEditText();
        boolean r1 = MaterialResources.isFontScaleAtLeast1_3(this.context);
        this.indicatorArea.setPaddingRelative(getIndicatorPadding(r1, R.dimen.material_helper_text_font_1_3_padding_horizontal, r0.getPaddingStart()), getIndicatorPadding(r1, R.dimen.material_helper_text_font_1_3_padding_top, this.context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_default_padding_top)), getIndicatorPadding(r1, R.dimen.material_helper_text_font_1_3_padding_horizontal, r0.getPaddingEnd()), 0);
        return;
    }

    public void cancelCaptionAnimator() {
        Animator r0 = this.captionAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        return;
    }

    public boolean errorIsDisplayed() {
        return isCaptionStateError(this.captionDisplayed);
    }

    public boolean errorShouldBeShown() {
        return isCaptionStateError(this.captionToShow);
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.errorViewAccessibilityLiveRegion;
    }

    public CharSequence getErrorContentDescription() {
        return this.errorViewContentDescription;
    }

    public CharSequence getErrorText() {
        return this.errorText;
    }

    public int getErrorViewCurrentTextColor() {
        TextView r0 = this.errorView;
        if (r0 != null) goto L5;
        return -1;
    L5:
        return r0.getCurrentTextColor();
    }

    public ColorStateList getErrorViewTextColors() {
        TextView r0 = this.errorView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getTextColors();
    }

    public CharSequence getHelperText() {
        return this.helperText;
    }

    public View getHelperTextView() {
        return this.helperTextView;
    }

    public ColorStateList getHelperTextViewColors() {
        TextView r0 = this.helperTextView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getTextColors();
    }

    public int getHelperTextViewCurrentTextColor() {
        TextView r0 = this.helperTextView;
        if (r0 != null) goto L5;
        return -1;
    L5:
        return r0.getCurrentTextColor();
    }

    public boolean helperTextIsDisplayed() {
        return isCaptionStateHelperText(this.captionDisplayed);
    }

    public boolean helperTextShouldBeShown() {
        return isCaptionStateHelperText(this.captionToShow);
    }

    public void hideError() {
        this.errorText = null;
        cancelCaptionAnimator();
        if (this.captionDisplayed == 1) goto L5;
    L10:
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, _UrlKt.FRAGMENT_ENCODE_SET));
        return;
    L5:
        if (this.helperTextEnabled == true) goto L7;
    L9:
        this.captionToShow = 0;
        goto L10
    L7:
        if (TextUtils.isEmpty(this.helperText) == true) goto L9;
        this.captionToShow = 2;
        goto L10
    }

    public void hideHelperText() {
        cancelCaptionAnimator();
        int r0 = this.captionDisplayed;
        if (r0 != 2) goto L5;
        this.captionToShow = 0;
    L5:
        updateCaptionViewsVisibility(r0, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, _UrlKt.FRAGMENT_ENCODE_SET));
    }

    public boolean isCaptionView(int r1) {
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L9;
        return false;
    L9:
        return true;
    L8:
        return true;
    }

    public boolean isErrorEnabled() {
        return this.errorEnabled;
    }

    public boolean isHelperTextEnabled() {
        return this.helperTextEnabled;
    }

    public void removeIndicator(TextView r2, int r3) {
        if (this.indicatorArea != null) goto L6;
        return;
    L6:
        if (isCaptionView(r3) == false) goto L10;
        FrameLayout r32 = this.captionArea;
        if (r32 == null) goto L10;
        r32.removeView(r2);
    L11:
        int r22 = this.indicatorsAdded - 1;
        this.indicatorsAdded = r22;
        setViewGroupGoneIfEmpty(this.indicatorArea, r22);
        return;
    L10:
        this.indicatorArea.removeView(r2);
        goto L11
    }

    public void setErrorAccessibilityLiveRegion(int r1) {
        this.errorViewAccessibilityLiveRegion = r1;
        TextView r0 = this.errorView;
        if (r0 == null) goto L6;
        r0.setAccessibilityLiveRegion(r1);
        return;
    }

    public void setErrorContentDescription(CharSequence r1) {
        this.errorViewContentDescription = r1;
        TextView r0 = this.errorView;
        if (r0 == null) goto L6;
        r0.setContentDescription(r1);
        return;
    }

    public void setErrorEnabled(boolean r4) {
        if (this.errorEnabled != r4) goto L5;
        return;
    L5:
        cancelCaptionAnimator();
        if (r4 == false) goto L11;
        AppCompatTextView r1 = new AppCompatTextView(this.context);
        this.errorView = r1;
        r1.setId(R.id.textinput_error);
        this.errorView.setTextAlignment(5);
        Typeface r12 = this.typeface;
        if (r12 == null) goto L10;
        this.errorView.setTypeface(r12);
    L10:
        setErrorTextAppearance(this.errorTextAppearance);
        setErrorViewTextColor(this.errorViewTextColor);
        setErrorContentDescription(this.errorViewContentDescription);
        setErrorAccessibilityLiveRegion(this.errorViewAccessibilityLiveRegion);
        this.errorView.setVisibility(4);
        addIndicator(this.errorView, 0);
    L12:
        this.errorEnabled = r4;
        return;
    L11:
        hideError();
        removeIndicator(this.errorView, 0);
        this.errorView = null;
        this.textInputView.updateEditTextBackground();
        this.textInputView.updateTextInputBoxState();
        goto L12
    }

    public void setErrorTextAppearance(int r2) {
        this.errorTextAppearance = r2;
        TextView r0 = this.errorView;
        if (r0 == null) goto L6;
        this.textInputView.setTextAppearanceCompatWithErrorFallback(r0, r2);
        return;
    }

    public void setErrorViewTextColor(ColorStateList r1) {
        this.errorViewTextColor = r1;
        TextView r0 = this.errorView;
        if (r0 == null) goto L7;
        if (r1 == null) goto L8;
        r0.setTextColor(r1);
        return;
    L8:
        return;
    }

    public void setHelperTextAppearance(int r1) {
        this.helperTextTextAppearance = r1;
        TextView r0 = this.helperTextView;
        if (r0 == null) goto L6;
        TextViewCompat.setTextAppearance(r0, r1);
        return;
    }

    public void setHelperTextEnabled(boolean r4) {
        if (this.helperTextEnabled != r4) goto L5;
        return;
    L5:
        cancelCaptionAnimator();
        if (r4 == false) goto L11;
        AppCompatTextView r1 = new AppCompatTextView(this.context);
        this.helperTextView = r1;
        r1.setId(R.id.textinput_helper_text);
        this.helperTextView.setTextAlignment(5);
        Typeface r12 = this.typeface;
        if (r12 == null) goto L10;
        this.helperTextView.setTypeface(r12);
    L10:
        this.helperTextView.setVisibility(4);
        this.helperTextView.setImportantForAccessibility(2);
        setHelperTextAppearance(this.helperTextTextAppearance);
        setHelperTextViewTextColor(this.helperTextViewTextColor);
        addIndicator(this.helperTextView, 1);
    L12:
        this.helperTextEnabled = r4;
        return;
    L11:
        hideHelperText();
        removeIndicator(this.helperTextView, 1);
        this.helperTextView = null;
        this.textInputView.updateEditTextBackground();
        this.textInputView.updateTextInputBoxState();
        goto L12
    }

    public void setHelperTextViewTextColor(ColorStateList r1) {
        this.helperTextViewTextColor = r1;
        TextView r0 = this.helperTextView;
        if (r0 == null) goto L7;
        if (r1 == null) goto L8;
        r0.setTextColor(r1);
        return;
    L8:
        return;
    }

    public void setTypefaces(Typeface r2) {
        if (r2 == this.typeface) goto L6;
        this.typeface = r2;
        setTextViewTypeface(this.errorView, r2);
        setTextViewTypeface(this.helperTextView, r2);
        return;
    }

    public void showError(CharSequence r4) {
        cancelCaptionAnimator();
        this.errorText = r4;
        this.errorView.setText(r4);
        int r0 = this.captionDisplayed;
        if (r0 == 1) goto L5;
        this.captionToShow = 1;
    L5:
        updateCaptionViewsVisibility(r0, this.captionToShow, shouldAnimateCaptionView(this.errorView, r4));
    }

    public void showHelper(CharSequence r4) {
        cancelCaptionAnimator();
        this.helperText = r4;
        this.helperTextView.setText(r4);
        int r0 = this.captionDisplayed;
        if (r0 == 2) goto L5;
        this.captionToShow = 2;
    L5:
        updateCaptionViewsVisibility(r0, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, r4));
    }
}
