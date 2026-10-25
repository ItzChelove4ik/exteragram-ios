package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class Visibility extends Transition {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
    static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private static final String[] sTransitionProperties = null;
    private int mMode;

    public static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        boolean mCanceled;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        public DisappearListener(View r2, int r3, boolean r4) {
            this.mCanceled = false;
            this.mView = r2;
            this.mFinalVisibility = r3;
            this.mParent = (ViewGroup) r2.getParent();
            this.mSuppressLayout = r4;
            suppressLayout(true);
        }

        private void hideViewWhenNotCanceled() {
            if (this.mCanceled == true) goto L7;
            ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            ViewGroup r0 = this.mParent;
            if (r0 == null) goto L7;
            r0.invalidate();
        L7:
            suppressLayout(false);
        }

        private void suppressLayout(boolean r2) {
            if (this.mSuppressLayout == true) goto L5;
            return;
        L5:
            if (this.mLayoutSuppressed == r2) goto L11;
            ViewGroup r0 = this.mParent;
            if (r0 == null) goto L12;
            this.mLayoutSuppressed = r2;
            ViewGroupUtils.suppressLayout(r0, r2);
            return;
        L12:
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.mCanceled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            hideViewWhenNotCanceled();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1, boolean r2) {
            if (r2 == true) goto L5;
            hideViewWhenNotCanceled();
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator r1) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1, boolean r2) {
            if (r2 == false) goto L7;
            ViewUtils.setTransitionVisibility(this.mView, 0);
            ViewGroup r0 = this.mParent;
            if (r0 == null) goto L8;
            r0.invalidate();
            return;
        L8:
            return;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
            r1.removeListener(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r1) {
            suppressLayout(false);
            if (this.mCanceled == true) goto L6;
            ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            return;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r1) {
            suppressLayout(true);
            if (this.mCanceled == true) goto L6;
            ViewUtils.setTransitionVisibility(this.mView, 0);
            return;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
        }
    }

    public class OverlayListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private boolean mHasOverlay;
        private final ViewGroup mOverlayHost;
        private final View mOverlayView;
        private final View mStartView;
        final /* synthetic */ Visibility this$0;

        public OverlayListener(Visibility r1, ViewGroup r2, View r3, View r4) {
            this.this$0 = r1;
            this.mHasOverlay = true;
            this.mOverlayHost = r2;
            this.mOverlayView = r3;
            this.mStartView = r4;
        }

        private void removeFromOverlay() {
            this.mStartView.setTag(R$id.save_overlay_view, null);
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
            this.mHasOverlay = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            removeFromOverlay();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1, boolean r2) {
            if (r2 == true) goto L5;
            removeFromOverlay();
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator r1) {
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator r1) {
            if (this.mOverlayView.getParent() != null) goto L6;
            this.mOverlayHost.getOverlay().add(this.mOverlayView);
            return;
        L6:
            this.this$0.cancel();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2, boolean r3) {
            if (r3 == false) goto L5;
            this.mStartView.setTag(R$id.save_overlay_view, this.mOverlayView);
            this.mOverlayHost.getOverlay().add(this.mOverlayView);
            this.mHasOverlay = true;
            return;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
            if (this.mHasOverlay == false) goto L6;
            removeFromOverlay();
            return;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
            r1.removeListener(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r1) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r1) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
        }
    }

    public static class VisibilityInfo {
        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;

        public VisibilityInfo() {
        }
    }

    static {
        sTransitionProperties = new String[]{PROPNAME_VISIBILITY, PROPNAME_PARENT};
    }

    public Visibility() {
        this.mMode = 3;
    }

    private void captureValues(TransitionValues r3) {
        int r2 = r3.view.getVisibility();
        r3.values.put(PROPNAME_VISIBILITY, Integer.valueOf(r2));
        r3.values.put(PROPNAME_PARENT, r3.view.getParent());
        int[] r22 = new int[2];
        r3.view.getLocationOnScreen(r22);
        r3.values.put(PROPNAME_SCREEN_LOCATION, r22);
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues r7, TransitionValues r8) {
        VisibilityInfo r6 = new VisibilityInfo();
        r6.mVisibilityChange = false;
        r6.mFadeIn = false;
        if (r7 != null) goto L5;
    L7:
        r6.mStartVisibility = -1;
        r6.mStartParent = null;
    L8:
        if (r8 != null) goto L10;
    L12:
        r6.mEndVisibility = -1;
        r6.mEndParent = null;
    L14:
        if (r7 == null) goto L36;
        if (r8 == null) goto L36;
        int r72 = r6.mStartVisibility;
        int r82 = r6.mEndVisibility;
        if (r72 == r82) goto L19;
    L21:
        if (r72 == r82) goto L29;
        if (r72 != 0) goto L25;
        r6.mFadeIn = false;
        r6.mVisibilityChange = true;
        return r6;
    L25:
        if (r82 != 0) goto L45;
        r6.mFadeIn = true;
        r6.mVisibilityChange = true;
        return r6;
    L45:
        return r6;
    L29:
        if (r6.mEndParent != null) goto L33;
        r6.mFadeIn = false;
        r6.mVisibilityChange = true;
        return r6;
    L33:
        if (r6.mStartParent != null) goto L45;
        r6.mFadeIn = true;
        r6.mVisibilityChange = true;
        return r6;
    L19:
        if (r6.mStartParent != r6.mEndParent) goto L21;
    L36:
        if (r7 == null) goto L38;
    L41:
        if (r8 != null) goto L45;
        if (r6.mStartVisibility != 0) goto L45;
        r6.mFadeIn = false;
        r6.mVisibilityChange = true;
        goto L45
    L38:
        if (r6.mEndVisibility != 0) goto L41;
        r6.mFadeIn = true;
        r6.mVisibilityChange = true;
        return r6;
    L10:
        if (r8.values.containsKey(PROPNAME_VISIBILITY) == false) goto L12;
        r6.mEndVisibility = ((Integer) r8.values.get(PROPNAME_VISIBILITY)).intValue();
        r6.mEndParent = (ViewGroup) r8.values.get(PROPNAME_PARENT);
        goto L14
    L5:
        if (r7.values.containsKey(PROPNAME_VISIBILITY) == false) goto L7;
        r6.mStartVisibility = ((Integer) r7.values.get(PROPNAME_VISIBILITY)).intValue();
        r6.mStartParent = (ViewGroup) r7.values.get(PROPNAME_PARENT);
        goto L8
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues r1) {
        captureValues(r1);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues r1) {
        captureValues(r1);
    }

    @Override // androidx.transition.Transition
    public Animator createAnimator(ViewGroup r9, TransitionValues r10, TransitionValues r11) {
        VisibilityInfo r0 = getVisibilityChangeInfo(r10, r11);
        if (r0.mVisibilityChange == true) goto L5;
        return null;
    L5:
        if (r0.mStartParent == null) goto L7;
    L8:
        boolean r1 = r0.mFadeIn;
        int r5 = r0.mStartVisibility;
        if (r1 == false) goto L13;
        return onAppear(r9, r10, r5, r11, r0.mEndVisibility);
    L13:
        return onDisappear(r9, r10, r5, r11, r0.mEndVisibility);
    L7:
        if (r0.mEndParent != null) goto L8;
        return null;
    }

    public int getMode() {
        return this.mMode;
    }

    @Override // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    @Override // androidx.transition.Transition
    public boolean isTransitionRequired(TransitionValues r5, TransitionValues r6) {
        if (r5 != null) goto L6;
        if (r6 != null) goto L6;
        return false;
    L6:
        if (r5 == null) goto L11;
        if (r6 == null) goto L11;
        if (r6.values.containsKey(PROPNAME_VISIBILITY) == r5.values.containsKey(PROPNAME_VISIBILITY)) goto L11;
        return false;
    L11:
        VisibilityInfo r4 = getVisibilityChangeInfo(r5, r6);
        if (r4.mVisibilityChange == true) goto L14;
    L19:
        return false;
    L14:
        if (r4.mStartVisibility != 0) goto L16;
        return true;
    L16:
        if (r4.mEndVisibility != 0) goto L19;
        return true;
    }

    public boolean isVisible(TransitionValues r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        int r0 = ((Integer) r3.values.get(PROPNAME_VISIBILITY)).intValue();
        View r32 = (View) r3.values.get(PROPNAME_PARENT);
        if (r0 != 0) goto L10;
        if (r32 == null) goto L11;
        return true;
    L11:
        return false;
    L10:
        return false;
    }

    public abstract Animator onAppear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4);

    public Animator onAppear(ViewGroup r3, TransitionValues r4, int r5, TransitionValues r6, int r7) {
        if ((this.mMode & 1) != 1) goto L12;
        if (r6 == null) goto L12;
        if (r4 != null) goto L11;
        View r52 = (View) r6.view.getParent();
        if (getVisibilityChangeInfo(getMatchedTransitionValues(r52, false), getTransitionValues(r52, false)).mVisibilityChange == false) goto L11;
        return null;
    L11:
        return onAppear(r3, r6.view, r4, r6);
    L12:
        return null;
    }

    public abstract Animator onDisappear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4);

    public Animator onDisappear(ViewGroup r10, TransitionValues r11, int r12, TransitionValues r13, int r14) {
        if ((this.mMode & 2) == 2) goto L5;
        return null;
    L5:
        if (r11 != null) goto L7;
        return null;
    L7:
        View r122 = r11.view;
        if (r13 == null) goto L10;
        View r2 = r13.view;
    L11:
        View r3 = (View) r122.getTag(R$id.save_overlay_view);
        if (r3 == null) goto L14;
        View r22 = null;
        boolean r6 = true;
    L45:
        if (r3 == null) goto L55;
        if (r6 == true) goto L48;
        int[] r142 = (int[]) r11.values.get(PROPNAME_SCREEN_LOCATION);
        int r1 = r142[0];
        int r143 = r142[1];
        int[] r0 = new int[2];
        r10.getLocationOnScreen(r0);
        r3.offsetLeftAndRight((r1 - r0[0]) - r3.getLeft());
        r3.offsetTopAndBottom((r143 - r0[1]) - r3.getTop());
        r10.getOverlay().add(r3);
    L48:
        Animator r112 = onDisappear(r10, r3, r11, r13);
        if (r6 == true) goto L54;
        if (r112 != null) goto L53;
        r10.getOverlay().remove(r3);
        return r112;
    L53:
        r122.setTag(R$id.save_overlay_view, r3);
        OverlayListener r132 = new OverlayListener(this, r10, r3, r122);
        r112.addListener(r132);
        r112.addPauseListener(r132);
        getRootTransition().addListener(r132);
    L54:
        return r112;
    L55:
        if (r22 == null) goto L62;
        int r123 = r22.getVisibility();
        ViewUtils.setTransitionVisibility(r22, 0);
        Animator r102 = onDisappear(r10, r22, r11, r13);
        if (r102 == null) goto L60;
        DisappearListener r113 = new DisappearListener(r22, r14, true);
        r102.addListener(r113);
        getRootTransition().addListener(r113);
        return r102;
    L60:
        ViewUtils.setTransitionVisibility(r22, r123);
        return r102;
    L62:
        return null;
    L14:
        if (r2 != null) goto L16;
    L24:
        if (r2 != null) goto L25;
    L23:
        r2 = null;
        View r32 = null;
        boolean r62 = true;
    L26:
        if (r62 == true) goto L28;
    L35:
        View r63 = r32;
        r3 = r2;
        r22 = r63;
        r6 = false;
        goto L45
    L28:
        if (r122.getParent() != null) goto L31;
    L44:
        r22 = r32;
        r6 = false;
        r3 = r122;
        goto L45
    L31:
        if ((r122.getParent() instanceof View) == false) goto L35;
        View r64 = (View) r122.getParent();
        if (getVisibilityChangeInfo(getTransitionValues(r64, true), getMatchedTransitionValues(r64, true)).mVisibilityChange == true) goto L36;
        r2 = TransitionUtils.copyViewImage(r10, r122, r64);
        goto L35
    L36:
        int r7 = r64.getId();
        if (r64.getParent() != null) goto L35;
        if (r7 == (-1)) goto L35;
        if (r10.findViewById(r7) == null) goto L35;
        if (this.mCanRemoveViews == false) goto L35;
    L25:
        r32 = null;
        r62 = false;
        goto L26
    L16:
        if (r2.getParent() == null) goto L24;
        if (r14 == 4) goto L22;
        if (r122 != r2) goto L23;
    L22:
        r32 = r2;
        r62 = false;
        r2 = null;
        goto L26
    L10:
        r2 = null;
        goto L11
    }

    public void setMode(int r2) {
        if ((r2 & (-4)) != 0) goto L6;
        this.mMode = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Only MODE_IN and MODE_OUT flags are allowed");
    }
}
