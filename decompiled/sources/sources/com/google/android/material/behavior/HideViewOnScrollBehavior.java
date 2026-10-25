package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityManager;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;
import kotlin.text.CharsKt__CharKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class HideViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final int DEFAULT_ENTER_ANIMATION_DURATION_MS = 225;
    private static final int DEFAULT_EXIT_ANIMATION_DURATION_MS = 175;
    public static final int EDGE_BOTTOM = 1;
    public static final int EDGE_LEFT = 2;
    public static final int EDGE_RIGHT = 0;
    private static final int ENTER_ANIM_DURATION_ATTR = 0;
    private static final int ENTER_EXIT_ANIM_EASING_ATTR = 0;
    private static final int EXIT_ANIM_DURATION_ATTR = 0;
    public static final int STATE_SCROLLED_IN = 2;
    public static final int STATE_SCROLLED_OUT = 1;
    private AccessibilityManager accessibilityManager;
    private int additionalHiddenOffset;
    private ViewPropertyAnimator currentAnimator;
    private int currentState;
    private boolean disableOnTouchExploration;
    private int enterAnimDuration;
    private TimeInterpolator enterAnimInterpolator;
    private int exitAnimDuration;
    private TimeInterpolator exitAnimInterpolator;
    private HideViewOnScrollDelegate hideOnScrollViewDelegate;
    private final LinkedHashSet<OnScrollStateChangedListener> onScrollStateChangedListeners;
    private int savedImportantForAccessibility;
    private int savedVisibility;
    private int size;
    private AccessibilityManager.TouchExplorationStateChangeListener touchExplorationListener;
    private boolean viewEdgeOverride;

    /* renamed from: com.google.android.material.behavior.HideViewOnScrollBehavior$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ HideViewOnScrollBehavior this$0;

        public AnonymousClass1(HideViewOnScrollBehavior r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r2) {
            if (HideViewOnScrollBehavior.access$000(this.this$0) != null) goto L5;
            return;
        L5:
            if (HideViewOnScrollBehavior.access$100(this.this$0) == null) goto L9;
            HideViewOnScrollBehavior.access$100(this.this$0).removeTouchExplorationStateChangeListener(HideViewOnScrollBehavior.access$000(this.this$0));
            HideViewOnScrollBehavior.access$002(this.this$0, null);
            return;
        }
    }

    /* renamed from: com.google.android.material.behavior.HideViewOnScrollBehavior$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ HideViewOnScrollBehavior this$0;
        final /* synthetic */ View val$child;

        public AnonymousClass2(HideViewOnScrollBehavior r1, View r2) {
            this.this$0 = r1;
            this.val$child = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            HideViewOnScrollBehavior.access$202(this.this$0, null);
            if (HideViewOnScrollBehavior.access$300(this.this$0) == 1) goto L5;
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

    public static /* synthetic */ void $r8$lambda$O4yxcwWX0oqhtqJeVevJwrB8hK4(HideViewOnScrollBehavior r1, View r2, boolean r3) {
        if (r1.disableOnTouchExploration == false) goto L9;
        if (r3 == true) goto L6;
        return;
    L6:
        if (r1.isScrolledOut() == false) goto L11;
        r1.slideIn(r2);
        return;
    L11:
        return;
    }

    static {
        ENTER_ANIM_DURATION_ATTR = R.attr.motionDurationLong2;
        EXIT_ANIM_DURATION_ATTR = R.attr.motionDurationMedium4;
        ENTER_EXIT_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    }

    public HideViewOnScrollBehavior() {
        this.disableOnTouchExploration = true;
        this.onScrollStateChangedListeners = new LinkedHashSet();
        this.size = 0;
        this.currentState = 2;
        this.additionalHiddenOffset = 0;
        this.savedImportantForAccessibility = 0;
        this.savedVisibility = 0;
        this.viewEdgeOverride = false;
    }

    public HideViewOnScrollBehavior(int r1) {
        this();
        setViewEdge(r1);
    }

    public HideViewOnScrollBehavior(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.disableOnTouchExploration = true;
        this.onScrollStateChangedListeners = new LinkedHashSet();
        this.size = 0;
        this.currentState = 2;
        this.additionalHiddenOffset = 0;
        this.savedImportantForAccessibility = 0;
        this.savedVisibility = 0;
        this.viewEdgeOverride = false;
    }

    public static /* synthetic */ AccessibilityManager.TouchExplorationStateChangeListener access$000(HideViewOnScrollBehavior r0) {
        return r0.touchExplorationListener;
    }

    public static /* synthetic */ AccessibilityManager.TouchExplorationStateChangeListener access$002(HideViewOnScrollBehavior r0, AccessibilityManager.TouchExplorationStateChangeListener r1) {
        r0.touchExplorationListener = r1;
        return r1;
    }

    public static /* synthetic */ AccessibilityManager access$100(HideViewOnScrollBehavior r0) {
        return r0.accessibilityManager;
    }

    public static /* synthetic */ ViewPropertyAnimator access$202(HideViewOnScrollBehavior r0, ViewPropertyAnimator r1) {
        r0.currentAnimator = r1;
        return r1;
    }

    public static /* synthetic */ int access$300(HideViewOnScrollBehavior r0) {
        return r0.currentState;
    }

    private void animateChildTo(V r2, int r3, long r4, TimeInterpolator r6) {
        this.currentAnimator = this.hideOnScrollViewDelegate.getViewTranslationAnimator(r2, r3).setInterpolator(r6).setDuration(r4).setListener(new AnonymousClass2(this, r2));
    }

    private void disableIfTouchExplorationEnabled(V r3) {
        if (this.accessibilityManager != null) goto L6;
        this.accessibilityManager = (AccessibilityManager) ContextCompat.getSystemService(r3.getContext(), AccessibilityManager.class);
    L6:
        if (this.accessibilityManager != null) goto L8;
        return;
    L8:
        if (this.touchExplorationListener != null) goto L12;
        HideViewOnScrollBehavior$$ExternalSyntheticLambda0 r0 = new HideViewOnScrollBehavior$$ExternalSyntheticLambda0(this, r3);
        this.touchExplorationListener = r0;
        this.accessibilityManager.addTouchExplorationStateChangeListener(r0);
        r3.addOnAttachStateChangeListener(new AnonymousClass1(this));
        return;
    }

    public static <V extends View> HideViewOnScrollBehavior<V> from(V r2) {
        ViewGroup.LayoutParams r22 = r2.getLayoutParams();
        if ((r22 instanceof CoordinatorLayout.LayoutParams) == false) goto L10;
        CoordinatorLayout.Behavior r23 = ((CoordinatorLayout.LayoutParams) r22).getBehavior();
        if ((r23 instanceof HideViewOnScrollBehavior) == true) goto L7;
        f$$ExternalSyntheticBUOutline1.m("The view is not associated with HideViewOnScrollBehavior");
        return null;
    L7:
        return (HideViewOnScrollBehavior) r23;
    L10:
        f$$ExternalSyntheticBUOutline1.m("The view is not a child of CoordinatorLayout");
        return null;
    }

    private boolean isGravityBottom(int r1) {
        if (r1 != 80) goto L5;
        return true;
    L5:
        if (r1 == 81) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isGravityLeft(int r1) {
        if (r1 != 3) goto L5;
        return true;
    L5:
        if (r1 == 19) goto L11;
        return false;
    L11:
        return true;
    }

    private void setViewEdge(V r2, int r3) {
        if (this.viewEdgeOverride == false) goto L5;
        return;
    L5:
        int r22 = ((CoordinatorLayout.LayoutParams) r2.getLayoutParams()).gravity;
        if (isGravityBottom(r22) == false) goto L10;
        setViewEdgeInternal(1);
        return;
    L10:
        if (isGravityLeft(Gravity.getAbsoluteGravity(r22, r3)) == false) goto L12;
        int r23 = 2;
    L13:
        setViewEdgeInternal(r23);
        return;
    L12:
        r23 = 0;
        goto L13
    }

    private void setViewEdgeInternal(int r2) {
        HideViewOnScrollDelegate r0 = this.hideOnScrollViewDelegate;
        if (r0 != null) goto L5;
    L8:
        if (r2 != 0) goto L10;
        this.hideOnScrollViewDelegate = new HideRightViewOnScrollDelegate();
        return;
    L10:
        if (r2 != 1) goto L12;
        this.hideOnScrollViewDelegate = new HideBottomViewOnScrollDelegate();
        return;
    L12:
        if (r2 != 2) goto L15;
        this.hideOnScrollViewDelegate = new HideLeftViewOnScrollDelegate();
        return;
    L15:
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Invalid view edge position value: ", r2, ". Must be 0, 1 or 2.");
        return;
    L5:
        if (r0.getViewEdge() != r2) goto L8;
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

    public boolean isScrolledIn() {
        if (this.currentState != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isScrolledOut() {
        if (this.currentState != 1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout r4, V r5, int r6) {
        disableIfTouchExplorationEnabled(r5);
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r5.getLayoutParams();
        setViewEdge(r5, r6);
        this.size = this.hideOnScrollViewDelegate.getSize(r5, r0);
        this.enterAnimDuration = MotionUtils.resolveThemeDuration(r5.getContext(), ENTER_ANIM_DURATION_ATTR, 225);
        this.exitAnimDuration = MotionUtils.resolveThemeDuration(r5.getContext(), EXIT_ANIM_DURATION_ATTR, 175);
        Context r02 = r5.getContext();
        int r1 = ENTER_EXIT_ANIM_EASING_ATTR;
        this.enterAnimInterpolator = MotionUtils.resolveThemeInterpolator(r02, r1, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        this.exitAnimInterpolator = MotionUtils.resolveThemeInterpolator(r5.getContext(), r1, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        return super.onLayoutChild(r4, r5, r6);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout r1, V r2, View r3, int r4, int r5, int r6, int r7, int r8, int[] r9) {
        if (r5 <= 0) goto L5;
        slideOut(r2);
        return;
    L5:
        if (r5 >= 0) goto L8;
        slideIn(r2);
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

    public void setAdditionalHiddenOffset(V r3, int r4) {
        this.additionalHiddenOffset = r4;
        if (this.currentState != 1) goto L6;
        this.hideOnScrollViewDelegate.setAdditionalHiddenOffset(r3, this.size, r4);
        return;
    }

    public void setViewEdge(int r2) {
        this.viewEdgeOverride = true;
        setViewEdgeInternal(r2);
    }

    public void slideIn(V r2) {
        slideIn(r2, true);
    }

    public void slideIn(V r8, boolean r9) {
        if (isScrolledIn() == false) goto L5;
        return;
    L5:
        updateCurrentState(r8, 2);
        ViewPropertyAnimator r0 = this.currentAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        r8.clearAnimation();
    L8:
        int r3 = this.hideOnScrollViewDelegate.getTargetTranslation();
        if (r9 == false) goto L12;
        animateChildTo(r8, r3, this.enterAnimDuration, this.enterAnimInterpolator);
        return;
    L12:
        this.hideOnScrollViewDelegate.setViewTranslation(r8, r3);
    }

    public void slideOut(V r2) {
        slideOut(r2, true);
    }

    public void slideOut(V r9, boolean r10) {
        if (isScrolledOut() == false) goto L6;
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
        int r4 = this.size + this.additionalHiddenOffset;
        if (r10 == false) goto L19;
        animateChildTo(r9, r4, this.exitAnimDuration, this.exitAnimInterpolator);
        return;
    L19:
        this.hideOnScrollViewDelegate.setViewTranslation(r9, r4);
        if (r9.getVisibility() != 0) goto L23;
        r9.setVisibility(4);
        return;
    }
}
