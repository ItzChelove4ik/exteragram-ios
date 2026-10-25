package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;

/* loaded from: classes4.dex */
public class Fade extends Visibility {

    public static class FadeAnimatorListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private boolean mLayerTypeChanged;
        private final View mView;

        public FadeAnimatorListener(View r2) {
            this.mLayerTypeChanged = false;
            this.mView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            ViewUtils.setTransitionAlpha(this.mView, 1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            onAnimationEnd(r2, false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3, boolean r4) {
            if (this.mLayerTypeChanged == false) goto L5;
            this.mView.setLayerType(0, null);
        L5:
            if (r4 == true) goto L8;
            ViewUtils.setTransitionAlpha(this.mView, 1.0f);
            ViewUtils.clearNonTransitionAlpha(this.mView);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            if (this.mView.hasOverlappingRendering() == true) goto L5;
            return;
        L5:
            if (this.mView.getLayerType() != 0) goto L9;
            this.mLayerTypeChanged = true;
            this.mView.setLayerType(2, null);
            return;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition r1) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(Transition r2) {
            if (this.mView.getVisibility() != 0) goto L5;
            float r22 = ViewUtils.getTransitionAlpha(this.mView);
        L6:
            this.mView.setTag(R$id.transition_pause_alpha, Float.valueOf(r22));
            return;
        L5:
            r22 = 0.0f;
            goto L6
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(Transition r2) {
            this.mView.setTag(R$id.transition_pause_alpha, null);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1, boolean r2) {
        }
    }

    public Fade() {
    }

    public Fade(int r1) {
        setMode(r1);
    }

    private Animator createAnimation(View r3, float r4, float r5) {
        if (r4 != r5) goto L6;
        return null;
    L6:
        ViewUtils.setTransitionAlpha(r3, r4);
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r3, ViewUtils.TRANSITION_ALPHA, new float[]{r5});
        FadeAnimatorListener r52 = new FadeAnimatorListener(r3);
        r42.addListener(r52);
        getRootTransition().addListener(r52);
        return r42;
    }

    private static float getStartAlpha(TransitionValues r1, float r2) {
        if (r1 == null) goto L7;
        Float r12 = (Float) r1.values.get("android:fade:transitionAlpha");
        if (r12 == null) goto L7;
        return r12.floatValue();
    L7:
        return r2;
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void captureStartValues(TransitionValues r2) {
        super.captureStartValues(r2);
        Float r1 = (Float) r2.view.getTag(R$id.transition_pause_alpha);
        if (r1 == null) goto L5;
    L8:
        r2.values.put("android:fade:transitionAlpha", r1);
        return;
    L5:
        if (r2.view.getVisibility() != 0) goto L7;
        r1 = Float.valueOf(ViewUtils.getTransitionAlpha(r2.view));
        goto L8
    L7:
        r1 = Float.valueOf(0.0f);
        goto L8
    }

    @Override // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }

    @Override // androidx.transition.Visibility
    public Animator onAppear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
        ViewUtils.saveNonTransitionAlpha(r2);
        return createAnimation(r2, getStartAlpha(r3, 0.0f), 1.0f);
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup r2, View r3, TransitionValues r4, TransitionValues r5) {
        ViewUtils.saveNonTransitionAlpha(r3);
        Animator r1 = createAnimation(r3, getStartAlpha(r4, 1.0f), 0.0f);
        if (r1 != null) goto L5;
        ViewUtils.setTransitionAlpha(r3, getStartAlpha(r5, 1.0f));
    L5:
        return r1;
    }
}
