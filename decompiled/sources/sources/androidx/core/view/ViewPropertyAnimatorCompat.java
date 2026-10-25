package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public final class ViewPropertyAnimatorCompat {
    private final WeakReference<View> mView;

    /* renamed from: androidx.core.view.ViewPropertyAnimatorCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPropertyAnimatorCompat this$0;
        final /* synthetic */ ViewPropertyAnimatorListener val$listener;
        final /* synthetic */ View val$view;

        public AnonymousClass1(ViewPropertyAnimatorCompat r1, ViewPropertyAnimatorListener r2, View r3) {
            this.this$0 = r1;
            this.val$listener = r2;
            this.val$view = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.val$listener.onAnimationCancel(this.val$view);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$listener.onAnimationEnd(this.val$view);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.val$listener.onAnimationStart(this.val$view);
        }
    }

    public static /* synthetic */ void $r8$lambda$yIm158IzmaGNEXn6TreBnR2w5dM(ViewPropertyAnimatorUpdateListener r0, View r1, ValueAnimator r2) {
        r0.onAnimationUpdate(r1);
    }

    public ViewPropertyAnimatorCompat(View r2) {
        this.mView = new WeakReference(r2);
    }

    private void setListenerInternal(View r3, ViewPropertyAnimatorListener r4) {
        if (r4 == null) goto L5;
        r3.animate().setListener(new AnonymousClass1(this, r4, r3));
        return;
    L5:
        r3.animate().setListener(null);
    }

    public ViewPropertyAnimatorCompat alpha(float r2) {
        View r0 = this.mView.get();
        if (r0 == null) goto L5;
        r0.animate().alpha(r2);
    L5:
        return this;
    }

    public void cancel() {
        View r0 = this.mView.get();
        if (r0 == null) goto L6;
        r0.animate().cancel();
        return;
    }

    public long getDuration() {
        View r2 = this.mView.get();
        if (r2 != null) goto L5;
        return 0;
    L5:
        return r2.animate().getDuration();
    }

    public ViewPropertyAnimatorCompat setDuration(long r2) {
        View r0 = this.mView.get();
        if (r0 == null) goto L5;
        r0.animate().setDuration(r2);
    L5:
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator r2) {
        View r0 = this.mView.get();
        if (r0 == null) goto L5;
        r0.animate().setInterpolator(r2);
    L5:
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener r2) {
        View r0 = this.mView.get();
        if (r0 == null) goto L5;
        setListenerInternal(r0, r2);
    L5:
        return this;
    }

    public ViewPropertyAnimatorCompat setStartDelay(long r2) {
        View r0 = this.mView.get();
        if (r0 == null) goto L5;
        r0.animate().setStartDelay(r2);
    L5:
        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener r3) {
        View r0 = this.mView.get();
        if (r0 == null) goto L8;
        if (r3 == null) goto L6;
        ViewPropertyAnimatorCompat$$ExternalSyntheticLambda0 r1 = new ViewPropertyAnimatorCompat$$ExternalSyntheticLambda0(r3, r0);
    L7:
        r0.animate().setUpdateListener(r1);
        goto L8
    L6:
        r1 = null;
    L8:
        return this;
    }

    public void start() {
        View r0 = this.mView.get();
        if (r0 == null) goto L6;
        r0.animate().start();
        return;
    }

    public ViewPropertyAnimatorCompat translationY(float r2) {
        View r0 = this.mView.get();
        if (r0 == null) goto L5;
        r0.animate().translationY(r2);
    L5:
        return this;
    }
}
