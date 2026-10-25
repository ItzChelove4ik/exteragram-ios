package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityManager;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

@Deprecated
/* loaded from: classes5.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final int DEFAULT_ENTER_ANIMATION_DURATION_MS = 225;
    private static final int DEFAULT_EXIT_ANIMATION_DURATION_MS = 175;
    private static final int ENTER_ANIM_DURATION_ATTR = 0;
    private static final int ENTER_EXIT_ANIM_EASING_ATTR = 0;
    private static final int EXIT_ANIM_DURATION_ATTR = 0;
    public static final int STATE_SCROLLED_DOWN = 1;
    public static final int STATE_SCROLLED_UP = 2;
    private AccessibilityManager accessibilityManager;
    private int additionalHiddenOffsetY;
    private ViewPropertyAnimator currentAnimator;
    private int currentState;
    private boolean disableOnTouchExploration;
    private int enterAnimDuration;
    private TimeInterpolator enterAnimInterpolator;
    private int exitAnimDuration;
    private TimeInterpolator exitAnimInterpolator;
    private int height;
    private final LinkedHashSet<OnScrollStateChangedListener> onScrollStateChangedListeners;
    private int savedImportantForAccessibility;
    private int savedVisibility;
    private AccessibilityManager.TouchExplorationStateChangeListener touchExplorationListener;

    /* renamed from: com.google.android.material.behavior.HideBottomViewOnScrollBehavior$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ HideBottomViewOnScrollBehavior this$0;

        public AnonymousClass1(HideBottomViewOnScrollBehavior r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r2) {
            if (HideBottomViewOnScrollBehavior.access$000(this.this$0) != null) goto L5;
            return;
        L5:
            if (HideBottomViewOnScrollBehavior.access$100(this.this$0) == null) goto L9;
            HideBottomViewOnScrollBehavior.access$100(this.this$0).removeTouchExplorationStateChangeListener(HideBottomViewOnScrollBehavior.access$000(this.this$0));
            HideBottomViewOnScrollBehavior.access$002(this.this$0, null);
            return;
        }
    }

    /* renamed from: com.google.android.material.behavior.HideBottomViewOnScrollBehavior$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ HideBottomViewOnScrollBehavior this$0;
        final /* synthetic */ View val$child;

        public AnonymousClass2(HideBottomViewOnScrollBehavior r1, View r2) {
            this.this$0 = r1;
            this.val$child = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            HideBottomViewOnScrollBehavior.access$202(this.this$0, null);
            if (HideBottomViewOnScrollBehavior.access$300(this.this$0) == 1) goto L5;
            return;
        L5:
            if (this.val$child.getVisibility() != 0) goto L9;
            this.val$child.setVisibility(4);
            return;
        }
    }

    public interface OnScrollStateChangedListener {
        void onStateChanged(View r1, int r2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollState {
    }

    /* renamed from: $r8$lambda$u8hW3V09BoGAavr-LZRTR540LS0, reason: not valid java name */
    public static /* synthetic */ void m1820$r8$lambda$u8hW3V09BoGAavrLZRTR540LS0(HideBottomViewOnScrollBehavior r0, View r1, boolean r2) {
        if (r2 == true) goto L4;
        r0.getClass();
        return;
    L4:
        if (r0.isScrolledDown() == false) goto L9;
        r0.slideUp(r1);
        return;
    }

    static {
        ENTER_ANIM_DURATION_ATTR = R.attr.motionDurationLong2;
        EXIT_ANIM_DURATION_ATTR = R.attr.motionDurationMedium4;
        ENTER_EXIT_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    }

    public HideBottomViewOnScrollBehavior() {
        this.onScrollStateChangedListeners = new LinkedHashSet();
        this.height = 0;
        this.disableOnTouchExploration = true;
        this.currentState = 2;
        this.additionalHiddenOffsetY = 0;
        this.savedImportantForAccessibility = 0;
        this.savedVisibility = 0;
    }

    public HideBottomViewOnScrollBehavior(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.onScrollStateChangedListeners = new LinkedHashSet();
        this.height = 0;
        this.disableOnTouchExploration = true;
        this.currentState = 2;
        this.additionalHiddenOffsetY = 0;
        this.savedImportantForAccessibility = 0;
        this.savedVisibility = 0;
    }

    public static /* synthetic */ AccessibilityManager.TouchExplorationStateChangeListener access$000(HideBottomViewOnScrollBehavior r0) {
        return r0.touchExplorationListener;
    }

    public static /* synthetic */ AccessibilityManager.TouchExplorationStateChangeListener access$002(HideBottomViewOnScrollBehavior r0, AccessibilityManager.TouchExplorationStateChangeListener r1) {
        r0.touchExplorationListener = r1;
        return r1;
    }

    public static /* synthetic */ AccessibilityManager access$100(HideBottomViewOnScrollBehavior r0) {
        return r0.accessibilityManager;
    }

    public static /* synthetic */ ViewPropertyAnimator access$202(HideBottomViewOnScrollBehavior r0, ViewPropertyAnimator r1) {
        r0.currentAnimator = r1;
        return r1;
    }

    public static /* synthetic */ int access$300(HideBottomViewOnScrollBehavior r0) {
        return r0.currentState;
    }

    private void animateChildTo(V r2, int r3, long r4, TimeInterpolator r6) {
        this.currentAnimator = r2.animate().translationY(r3).setInterpolator(r6).setDuration(r4).setListener(new AnonymousClass2(this, r2));
    }

    private void disableIfTouchExplorationEnabled(V r3) {
        if (this.accessibilityManager != null) goto L6;
        this.accessibilityManager = (AccessibilityManager) ContextCompat.getSystemService(r3.getContext(), AccessibilityManager.class);
    L6:
        if (this.accessibilityManager != null) goto L8;
        return;
    L8:
        if (this.touchExplorationListener != null) goto L12;
        HideBottomViewOnScrollBehavior$$ExternalSyntheticLambda0 r0 = new HideBottomViewOnScrollBehavior$$ExternalSyntheticLambda0(this, r3);
        this.touchExplorationListener = r0;
        this.accessibilityManager.addTouchExplorationStateChangeListener(r0);
        r3.addOnAttachStateChangeListener(new AnonymousClass1(this));
        return;
    }

    private void updateCurrentState(V r3, int r4) {
        this.currentState = r4;
        updateViewPropertiesForState(r3, r4);
        Iterator<OnScrollStateChangedListener> r42 = this.onScrollStateChangedListeners.iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        r42.next().onStateChanged(r3, this.currentState);
        goto L4
    }

    private void updateViewPropertiesForState(V r3, int r4) {
        if (r4 != 1) goto L16;
        if (r3.hasFocus() == false) goto L8;
        r3.clearFocus();
    L8:
        if (r3.getImportantForAccessibility() == 4) goto L11;
        this.savedImportantForAccessibility = r3.getImportantForAccessibility();
    L11:
        if (r3.getVisibility() == 4) goto L13;
        this.savedVisibility = r3.getVisibility();
    L13:
        r3.setImportantForAccessibility(4);
        return;
    L16:
        if (r4 == 2) goto L18;
        return;
    L18:
        if (r3.getImportantForAccessibility() != 4) goto L21;
        r3.setImportantForAccessibility(this.savedImportantForAccessibility);
    L21:
        if (r3.getVisibility() != 4) goto L25;
        r3.setVisibility(this.savedVisibility);
        return;
    }

    public void addOnScrollStateChangedListener(OnScrollStateChangedListener r1) {
        this.onScrollStateChangedListeners.add(r1);
    }

    public void clearOnScrollStateChangedListeners() {
        this.onScrollStateChangedListeners.clear();
    }

    public void disableOnTouchExploration(boolean r1) {
        this.disableOnTouchExploration = r1;
    }

    public boolean isDisabledOnTouchExploration() {
        return this.disableOnTouchExploration;
    }

    public boolean isScrolledDown() {
        if (this.currentState != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isScrolledUp() {
        if (this.currentState != 2) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout r4, V r5, int r6) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r5.getLayoutParams();
        this.height = r5.getMeasuredHeight() + r0.bottomMargin;
        this.enterAnimDuration = MotionUtils.resolveThemeDuration(r5.getContext(), ENTER_ANIM_DURATION_ATTR, 225);
        this.exitAnimDuration = MotionUtils.resolveThemeDuration(r5.getContext(), EXIT_ANIM_DURATION_ATTR, 175);
        Context r02 = r5.getContext();
        int r1 = ENTER_EXIT_ANIM_EASING_ATTR;
        this.enterAnimInterpolator = MotionUtils.resolveThemeInterpolator(r02, r1, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        this.exitAnimInterpolator = MotionUtils.resolveThemeInterpolator(r5.getContext(), r1, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        disableIfTouchExplorationEnabled(r5);
        return super.onLayoutChild(r4, r5, r6);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout r1, V r2, View r3, int r4, int r5, int r6, int r7, int r8, int[] r9) {
        if (r5 <= 0) goto L5;
        slideDown(r2);
        return;
    L5:
        if (r5 >= 0) goto L8;
        slideUp(r2);
        return;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout r1, V r2, View r3, View r4, int r5, int r6) {
        if (r5 != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public void removeOnScrollStateChangedListener(OnScrollStateChangedListener r1) {
        this.onScrollStateChangedListeners.remove(r1);
    }

    public void setAdditionalHiddenOffsetY(V r3, int r4) {
        this.additionalHiddenOffsetY = r4;
        if (this.currentState != 1) goto L6;
        r3.setTranslationY(this.height + r4);
        return;
    }

    public void slideDown(V r2) {
        slideDown(r2, true);
    }

    public void slideDown(V r9, boolean r10) {
        if (isScrolledDown() == false) goto L6;
        return;
    L6:
        if (this.disableOnTouchExploration == false) goto L12;
        AccessibilityManager r0 = this.accessibilityManager;
        if (r0 == null) goto L12;
        if (r0.isTouchExplorationEnabled() == false) goto L12;
        return;
    L12:
        ViewPropertyAnimator r02 = this.currentAnimator;
        if (r02 == null) goto L15;
        r02.cancel();
        r9.clearAnimation();
    L15:
        updateCurrentState(r9, 1);
        int r4 = this.height + this.additionalHiddenOffsetY;
        if (r10 == false) goto L19;
        animateChildTo(r9, r4, this.exitAnimDuration, this.exitAnimInterpolator);
        return;
    L19:
        r9.setTranslationY(r4);
        if (r9.getVisibility() != 0) goto L23;
        r9.setVisibility(4);
        return;
    }

    public void slideUp(V r2) {
        slideUp(r2, true);
    }

    public void slideUp(V r7, boolean r8) {
        if (isScrolledUp() == false) goto L5;
        return;
    L5:
        updateCurrentState(r7, 2);
        ViewPropertyAnimator r0 = this.currentAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        r7.clearAnimation();
    L8:
        if (r8 == false) goto L11;
        animateChildTo(r7, 0, this.enterAnimDuration, this.enterAnimInterpolator);
        return;
    L11:
        r7.setTranslationY(0.0f);
    }
}
