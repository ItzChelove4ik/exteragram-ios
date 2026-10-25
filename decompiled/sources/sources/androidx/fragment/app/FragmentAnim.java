package androidx.fragment.app;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.R$animator;
import androidx.fragment.R$id;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
abstract class FragmentAnim {

    public static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        public AnimationOrAnimator(Animator r2) {
            this.animation = null;
            this.animator = r2;
            if (r2 == null) goto L5;
            return;
        L5:
            Segment$$ExternalSyntheticBUOutline1.m("Animator cannot be null");
            throw null;
        }

        public AnimationOrAnimator(Animation r2) {
            this.animation = r2;
            this.animator = null;
            if (r2 == null) goto L5;
            return;
        L5:
            Segment$$ExternalSyntheticBUOutline1.m("Animation cannot be null");
            throw null;
        }
    }

    public static class EndViewTransitionAnimation extends AnimationSet implements Runnable {
        private boolean mAnimating;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        public EndViewTransitionAnimation(Animation r2, ViewGroup r3, View r4) {
            super(false);
            this.mAnimating = true;
            this.mParent = r3;
            this.mChild = r4;
            addAnimation(r2);
            r3.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long r3, Transformation r5) {
            this.mAnimating = true;
            if (this.mEnded == false) goto L7;
            return !this.mTransitionEnded;
        L7:
            if (super.getTransformation(r3, r5) == true) goto L9;
            this.mEnded = true;
            OneShotPreDrawListener.add(this.mParent, this);
        L9:
            return true;
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long r3, Transformation r5, float r6) {
            this.mAnimating = true;
            if (this.mEnded == false) goto L7;
            return !this.mTransitionEnded;
        L7:
            if (super.getTransformation(r3, r5, r6) == true) goto L9;
            this.mEnded = true;
            OneShotPreDrawListener.add(this.mParent, this);
        L9:
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mEnded == false) goto L5;
        L8:
            this.mParent.endViewTransition(this.mChild);
            this.mTransitionEnded = true;
            return;
        L5:
            if (this.mAnimating == false) goto L8;
            this.mAnimating = false;
            this.mParent.post(this);
        }
    }

    private static int getNextAnim(Fragment r0, boolean r1, boolean r2) {
        if (r2 == false) goto L8;
        if (r1 == false) goto L7;
        return r0.getPopEnterAnim();
    L7:
        return r0.getPopExitAnim();
    L8:
        if (r1 == false) goto L12;
        return r0.getEnterAnim();
    L12:
        return r0.getExitAnim();
    }

    public static AnimationOrAnimator loadAnimation(Context r4, Fragment r5, boolean r6, boolean r7) {
        int r0 = r5.getNextTransition();
        int r72 = getNextAnim(r5, r6, r7);
        r5.setAnimations(0, 0, 0, 0);
        ViewGroup r1 = r5.mContainer;
        if (r1 != null) goto L5;
    L7:
        ViewGroup r12 = r5.mContainer;
        if (r12 != null) goto L10;
    L12:
        Animation r13 = r5.onCreateAnimation(r0, r6, r72);
        if (r13 != null) goto L15;
        Animator r52 = r5.onCreateAnimator(r0, r6, r72);
        if (r52 != null) goto L19;
        if (r72 != 0) goto L23;
        if (r0 == 0) goto L23;
        r72 = transitToAnimResourceId(r4, r0, r6);
    L23:
        if (r72 == 0) goto L43;
        boolean r53 = "anim".equals(r4.getResources().getResourceTypeName(r72));
        if (r53 == true) goto L47;
    L45:
        Animator r62 = AnimatorInflater.loadAnimator(r4, r72);     // Catch: RuntimeException -> L36
        if (r62 == null) goto L43;
        return new AnimationOrAnimator(r62);
    L36:
        e = move-exception;
        if (r53 == true) goto L42;
        Animation r42 = AnimationUtils.loadAnimation(r4, r72);
        if (r42 == null) goto L43;
        return new AnimationOrAnimator(r42);
    L42:
        throw e;
    L47:
        Animation r63 = AnimationUtils.loadAnimation(r4, r72);     // Catch: Resources.NotFoundException -> L30 RuntimeException -> L44
        if (r63 == null) goto L43;
        return new AnimationOrAnimator(r63);
    L30:
        e = move-exception;
        throw e;
    L43:
        return null;
    L19:
        return new AnimationOrAnimator(r52);
    L15:
        return new AnimationOrAnimator(r13);
    L10:
        if (r12.getLayoutTransition() == null) goto L12;
        return null;
    L5:
        if (r1.getTag(R$id.visible_removing_fragment_view_tag) == null) goto L7;
        r5.mContainer.setTag(R$id.visible_removing_fragment_view_tag, null);
        goto L7
    }

    private static int toActivityTransitResId(Context r1, int r2) {
        TypedArray r12 = r1.obtainStyledAttributes(R.style.Animation.Activity, new int[]{r2});
        int r22 = r12.getResourceId(0, -1);
        r12.recycle();
        return r22;
    }

    private static int transitToAnimResourceId(Context r1, int r2, boolean r3) {
        if (r2 != 4097) goto L5;
        if (r3 == false) goto L38;
        return R$animator.fragment_open_enter;
    L38:
        return R$animator.fragment_open_exit;
    L5:
        if (r2 != 8194) goto L7;
        if (r3 == false) goto L33;
        return R$animator.fragment_close_enter;
    L33:
        return R$animator.fragment_close_exit;
    L7:
        if (r2 != 8197) goto L9;
        if (r3 == false) goto L28;
        return toActivityTransitResId(r1, R.attr.activityCloseEnterAnimation);
    L28:
        return toActivityTransitResId(r1, R.attr.activityCloseExitAnimation);
    L9:
        if (r2 != 4099) goto L11;
        if (r3 == false) goto L23;
        return R$animator.fragment_fade_enter;
    L23:
        return R$animator.fragment_fade_exit;
    L11:
        if (r2 == 4100) goto L14;
        return -1;
    L14:
        if (r3 == false) goto L18;
        return toActivityTransitResId(r1, R.attr.activityOpenEnterAnimation);
    L18:
        return toActivityTransitResId(r1, R.attr.activityOpenExitAnimation);
    }
}
