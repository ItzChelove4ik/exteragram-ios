package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.motion.MotionUtils;
import kotlin.jvm.internal.LongCompanionObject;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
class DropdownMenuEndIconDelegate extends EndIconDelegate {
    private static final int DEFAULT_ANIMATION_FADE_IN_DURATION = 67;
    private static final int DEFAULT_ANIMATION_FADE_OUT_DURATION = 50;
    private AccessibilityManager accessibilityManager;
    private final int animationFadeInDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationFadeOutDuration;
    private AutoCompleteTextView autoCompleteTextView;
    private long dropdownPopupActivatedAt;
    private boolean dropdownPopupDirty;
    private boolean editTextHasFocus;
    private ValueAnimator fadeInAnim;
    private ValueAnimator fadeOutAnim;
    private boolean isEndIconChecked;
    private final View.OnFocusChangeListener onEditTextFocusChangeListener;
    private final View.OnClickListener onIconClickListener;
    private final AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateChangeListener;

    /* renamed from: com.google.android.material.textfield.DropdownMenuEndIconDelegate$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ DropdownMenuEndIconDelegate this$0;

        public AnonymousClass1(DropdownMenuEndIconDelegate r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.refreshIconState();
            DropdownMenuEndIconDelegate.access$000(this.this$0).start();
        }
    }

    /* renamed from: $r8$lambda$-y8fy3mWjKkDKEPGKU3W34ch_Uw, reason: not valid java name */
    public static /* synthetic */ void m1905$r8$lambda$y8fy3mWjKkDKEPGKU3W34ch_Uw(DropdownMenuEndIconDelegate r1) {
        boolean r0 = r1.autoCompleteTextView.isPopupShowing();
        r1.setEndIconChecked(r0);
        r1.dropdownPopupDirty = r0;
    }

    public static /* synthetic */ void $r8$lambda$B9_pgNgtuRR7l72K3jIEKbhb2Io(DropdownMenuEndIconDelegate r1, boolean r2) {
        AutoCompleteTextView r0 = r1.autoCompleteTextView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (EditTextUtils.isEditable(r0) == true) goto L13;
        CheckableImageButton r12 = r1.endIconView;
        if (r2 == false) goto L9;
        int r22 = 2;
    L10:
        r12.setImportantForAccessibility(r22);
        return;
    L9:
        r22 = 1;
        goto L10
    }

    public static /* synthetic */ void $r8$lambda$CZy5WigobS3oySmsGDEVS50zVNw(DropdownMenuEndIconDelegate r0, ValueAnimator r1) {
        r0.getClass();
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        r0.endIconView.setAlpha(r12);
    }

    public static /* synthetic */ void $r8$lambda$nNUP5cjMWoIMzjM1rxTH_Aso8Io(DropdownMenuEndIconDelegate r0, View r1, boolean r2) {
        r0.editTextHasFocus = r2;
        r0.refreshIconState();
        if (r2 == true) goto L6;
        r0.setEndIconChecked(false);
        r0.dropdownPopupDirty = false;
        return;
    }

    /* renamed from: $r8$lambda$rHz86UpD1kaG-bWDU0-jLf2Nncs, reason: not valid java name */
    public static /* synthetic */ boolean m1906$r8$lambda$rHz86UpD1kaGbWDU0jLf2Nncs(DropdownMenuEndIconDelegate r1, View r2, MotionEvent r3) {
        r1.getClass();
        if (r3.getAction() == 1) goto L5;
    L8:
        return false;
    L5:
        if (r1.isDropdownPopupActive() == false) goto L7;
        r1.dropdownPopupDirty = false;
    L7:
        r1.showHideDropdown();
        r1.updateDropdownPopupDirty();
        goto L8
    }

    /* renamed from: $r8$lambda$scsW-sP1aqfljH19IzwcrEYD_Bo, reason: not valid java name */
    public static /* synthetic */ void m1907$r8$lambda$scsWsP1aqfljH19IzwcrEYD_Bo(DropdownMenuEndIconDelegate r1) {
        r1.updateDropdownPopupDirty();
        r1.setEndIconChecked(false);
    }

    public static /* synthetic */ void $r8$lambda$zx3DI8DlKFahyTGg_bpZxeFMOVk(DropdownMenuEndIconDelegate r0, View r1) {
        r0.showHideDropdown();
    }

    public DropdownMenuEndIconDelegate(EndCompoundLayout r4) {
        super(r4);
        this.onIconClickListener = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda3(this);
        this.onEditTextFocusChangeListener = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda4(this);
        this.touchExplorationStateChangeListener = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda5(this);
        this.dropdownPopupActivatedAt = LongCompanionObject.MAX_VALUE;
        this.animationFadeInDuration = MotionUtils.resolveThemeDuration(r4.getContext(), R.attr.motionDurationShort3, 67);
        this.animationFadeOutDuration = MotionUtils.resolveThemeDuration(r4.getContext(), R.attr.motionDurationShort3, 50);
        this.animationFadeInterpolator = MotionUtils.resolveThemeInterpolator(r4.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
    }

    public static /* synthetic */ ValueAnimator access$000(DropdownMenuEndIconDelegate r0) {
        return r0.fadeInAnim;
    }

    private static AutoCompleteTextView castAutoCompleteTextViewOrThrow(EditText r1) {
        if ((r1 instanceof AutoCompleteTextView) == true) goto L5;
        GlShader$$ExternalSyntheticBUOutline1.m("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
        return null;
    L5:
        return (AutoCompleteTextView) r1;
    }

    private ValueAnimator getAlphaAnimator(int r3, float... r4) {
        ValueAnimator r42 = ValueAnimator.ofFloat(r4);
        r42.setInterpolator(this.animationFadeInterpolator);
        r42.setDuration(r3);
        r42.addUpdateListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda0(this));
        return r42;
    }

    private void initAnimators() {
        this.fadeInAnim = getAlphaAnimator(this.animationFadeInDuration, new float[]{0.0f, 1.0f});
        ValueAnimator r0 = getAlphaAnimator(this.animationFadeOutDuration, new float[]{1.0f, 0.0f});
        this.fadeOutAnim = r0;
        r0.addListener(new AnonymousClass1(this));
    }

    private boolean isDropdownPopupActive() {
        long r0 = SystemClock.uptimeMillis() - this.dropdownPopupActivatedAt;
        if (r0 >= 0) goto L5;
        return true;
    L5:
        if (r0 > 300) goto L11;
        return false;
    L11:
        return true;
    }

    private void setEndIconChecked(boolean r2) {
        if (this.isEndIconChecked == r2) goto L6;
        this.isEndIconChecked = r2;
        this.fadeInAnim.cancel();
        this.fadeOutAnim.start();
        return;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void setUpDropdownShowHideBehavior() {
        this.autoCompleteTextView.setOnTouchListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1(this));
        this.autoCompleteTextView.setOnDismissListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda2(this));
        this.autoCompleteTextView.setThreshold(0);
    }

    private void showHideDropdown() {
        if (this.autoCompleteTextView != null) goto L6;
        return;
    L6:
        if (isDropdownPopupActive() == false) goto L9;
        this.dropdownPopupDirty = false;
    L9:
        if (this.dropdownPopupDirty == true) goto L16;
        setEndIconChecked(!this.isEndIconChecked);
        boolean r0 = this.isEndIconChecked;
        AutoCompleteTextView r1 = this.autoCompleteTextView;
        if (r0 == false) goto L14;
        r1.requestFocus();
        this.autoCompleteTextView.showDropDown();
        return;
    L14:
        r1.dismissDropDown();
        return;
    L16:
        this.dropdownPopupDirty = false;
    }

    private void updateDropdownPopupDirty() {
        this.dropdownPopupDirty = true;
        this.dropdownPopupActivatedAt = SystemClock.uptimeMillis();
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void afterEditTextChanged(Editable r2) {
        if (this.accessibilityManager.isTouchExplorationEnabled() == true) goto L5;
    L9:
        this.autoCompleteTextView.post(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda6(this));
        return;
    L5:
        if (EditTextUtils.isEditable(this.autoCompleteTextView) == false) goto L9;
        if (this.endIconView.hasFocus() == true) goto L9;
        this.autoCompleteTextView.dismissDropDown();
        goto L9
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public int getIconContentDescriptionResId() {
        return R.string.exposed_dropdown_menu_content_description;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public int getIconDrawableResId() {
        return R.drawable.mtrl_dropdown_arrow;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return this.onEditTextFocusChangeListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public AccessibilityManager.TouchExplorationStateChangeListener getTouchExplorationStateChangeListener() {
        return this.touchExplorationStateChangeListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean isBoxBackgroundModeSupported(int r1) {
        if (r1 == 0) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean isIconActivable() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean isIconActivated() {
        return this.editTextHasFocus;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean isIconCheckable() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean isIconChecked() {
        return this.isEndIconChecked;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void onEditTextAttached(EditText r3) {
        this.autoCompleteTextView = castAutoCompleteTextViewOrThrow(r3);
        setUpDropdownShowHideBehavior();
        this.textInputLayout.setErrorIconDrawable(null);
        if (EditTextUtils.isEditable(r3) == false) goto L5;
    L7:
        this.textInputLayout.setEndIconVisible(true);
        return;
    L5:
        if (this.accessibilityManager.isTouchExplorationEnabled() == false) goto L7;
        this.endIconView.setImportantForAccessibility(2);
        goto L7
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
        if (EditTextUtils.isEditable(this.autoCompleteTextView) == true) goto L6;
        r2.setClassName(Spinner.class.getName());
    L6:
        if (r2.isShowingHintText() == false) goto L9;
        r2.setHintText(null);
        return;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    @SuppressLint({"WrongConstant"})
    public void onPopulateAccessibilityEvent(View r3, AccessibilityEvent r4) {
        if (this.accessibilityManager.isEnabled() == true) goto L5;
        return;
    L5:
        if (EditTextUtils.isEditable(this.autoCompleteTextView) == false) goto L8;
        return;
    L8:
        if (r4.getEventType() == 32768) goto L12;
        if (r4.getEventType() == 8) goto L12;
    L16:
        boolean r32 = false;
    L18:
        if (r4.getEventType() == 1) goto L20;
        if (r32 == true) goto L20;
        return;
    L20:
        showHideDropdown();
        updateDropdownPopupDirty();
        return;
    L12:
        if (this.isEndIconChecked == false) goto L16;
        if (this.autoCompleteTextView.isPopupShowing() == true) goto L16;
        r32 = true;
        goto L18
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void setUp() {
        initAnimators();
        this.accessibilityManager = (AccessibilityManager) this.context.getSystemService("accessibility");
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public boolean shouldTintIconOnError() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    @SuppressLint({"ClickableViewAccessibility"})
    public void tearDown() {
        AutoCompleteTextView r0 = this.autoCompleteTextView;
        if (r0 == null) goto L6;
        r0.setOnTouchListener(null);
        this.autoCompleteTextView.setOnDismissListener(null);
        return;
    }
}
