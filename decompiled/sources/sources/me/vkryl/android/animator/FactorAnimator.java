package me.vkryl.android.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import me.vkryl.android.AnimatorUtils;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class FactorAnimator {
    private ValueAnimator animator;
    private long duration;
    private float factor;
    private final int id;
    private Interpolator interpolator;
    private boolean isAnimating;
    private boolean isBlocked;
    private long startDelay;
    private Runnable startRunnable;
    private final Target target;
    private float toFactor;

    /* renamed from: me.vkryl.android.animator.FactorAnimator$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ FactorAnimator this$0;
        final /* synthetic */ float val$factorDiff;
        final /* synthetic */ float val$fromFactor;

        public AnonymousClass1(FactorAnimator r1, float r2, float r3) {
            this.this$0 = r1;
            this.val$fromFactor = r2;
            this.val$factorDiff = r3;
        }

        private void finishAnimation() {
            if (FactorAnimator.m3569$$Nest$fgetisAnimating(this.this$0) == false) goto L6;
            FactorAnimator.m3573$$Nest$msetFactor(this.this$0, this.val$fromFactor + this.val$factorDiff, 1.0f);
            FactorAnimator.m3572$$Nest$msetAnimating(this.this$0, false);
            FactorAnimator.m3570$$Nest$fgettarget(this.this$0).onFactorChangeFinished(FactorAnimator.m3568$$Nest$fgetid(this.this$0), FactorAnimator.m3567$$Nest$fgetfactor(this.this$0), this.this$0);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            finishAnimation();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            finishAnimation();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            FactorAnimator.m3571$$Nest$minvokeStartRunnable(this.this$0);
        }
    }

    public interface Target {
        default void onFactorChangeFinished(int r1, float r2, FactorAnimator r3) {
        }

        void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4);
    }

    public static /* synthetic */ void $r8$lambda$LsA1SuGjhk7IY6sdrf71gagdAuI(FactorAnimator r0, float r1, float r2, ValueAnimator r3) {
        r0.lambda$animateTo$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetfactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m3567$$Nest$fgetfactor(FactorAnimator r0) {
        return r0.factor;
    }

    /* renamed from: -$$Nest$fgetid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m3568$$Nest$fgetid(FactorAnimator r0) {
        return r0.id;
    }

    /* renamed from: -$$Nest$fgetisAnimating, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m3569$$Nest$fgetisAnimating(FactorAnimator r0) {
        return r0.isAnimating;
    }

    /* renamed from: -$$Nest$fgettarget, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Target m3570$$Nest$fgettarget(FactorAnimator r0) {
        return r0.target;
    }

    /* renamed from: -$$Nest$minvokeStartRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3571$$Nest$minvokeStartRunnable(FactorAnimator r0) {
        r0.invokeStartRunnable();
    }

    /* renamed from: -$$Nest$msetAnimating, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3572$$Nest$msetAnimating(FactorAnimator r0, boolean r1) {
        r0.setAnimating(r1);
    }

    /* renamed from: -$$Nest$msetFactor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m3573$$Nest$msetFactor(FactorAnimator r0, float r1, float r2) {
        return r0.setFactor(r1, r2);
    }

    public FactorAnimator(int r1, Target r2, Interpolator r3, long r4) {
        if (r2 == null) goto L6;
        this.id = r1;
        this.target = r2;
        this.interpolator = r3;
        this.duration = r4;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    }

    public FactorAnimator(int r1, Target r2, Interpolator r3, long r4, float r6) {
        if (r2 == null) goto L6;
        this.id = r1;
        this.target = r2;
        this.interpolator = r3;
        this.duration = r4;
        this.factor = r6;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
        throw null;
    }

    private void invokeStartRunnable() {
        Runnable r0 = this.startRunnable;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$animateTo$0(float r2, float r3, ValueAnimator r4) {
        if (this.isAnimating == false) goto L6;
        float r42 = AnimatorUtils.getFraction(r4);
        setFactor(r2 + (r3 * r42), r42);
        return;
    }

    private void setAnimating(boolean r2) {
        if (this.isAnimating == r2) goto L6;
        this.isAnimating = r2;
        return;
    }

    private boolean setFactor(float r3, float r4) {
        if (this.factor == r3) goto L6;
        this.factor = r3;
        this.target.onFactorChanged(this.id, r3, r4, this);
        return true;
    L6:
        return false;
    }

    public void animateTo(float r2) {
        animateTo(r2, null);
    }

    public void animateTo(float r10, View r11) {
        if (Looper.myLooper() == Looper.getMainLooper()) goto L5;
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return;
    L5:
        if (this.isAnimating == false) goto L8;
        cancel();
    L8:
        if (this.factor != r10) goto L12;
        invokeStartRunnable();
        this.target.onFactorChangeFinished(this.id, this.factor, this);
        return;
    L12:
        if (this.isBlocked == false) goto L15;
        this.factor = r10;
        invokeStartRunnable();
        this.target.onFactorChanged(this.id, this.factor, 1.0f, this);
        this.target.onFactorChangeFinished(this.id, this.factor, this);
        return;
    L15:
        setAnimating(true);
        float r0 = this.factor;
        float r2 = r10 - r0;
        long r3 = this.duration;
        if (Build.VERSION.SDK_INT < 26) goto L21;
        if (ValueAnimator.areAnimatorsEnabled() == true) goto L21;
        r3 = 0;
    L21:
        if (r3 > 0) goto L24;
        setFactor(r10, 1.0f);
        setAnimating(false);
        this.target.onFactorChangeFinished(this.id, r10, this);
        return;
    L24:
        this.toFactor = r10;
        ValueAnimator r1 = AnimatorUtils.simpleValueAnimator();
        this.animator = r1;
        r1.setDuration(r3);
        this.animator.setInterpolator(this.interpolator);
        this.animator.addUpdateListener(new FactorAnimator$$ExternalSyntheticLambda0(this, r0, r2));
        this.animator.addListener(new AnonymousClass1(this, r0, r2));
        long r02 = this.startDelay;
        if (r02 == 0) goto L27;
        this.animator.setStartDelay(r02);
    L27:
        ValueAnimator r03 = this.animator;
        if (r11 == null) goto L33;
        AnimatorUtils.startAnimator(r11, r03);     // Catch: Throwable -> L31
        return;
    L33:
        r03.start();     // Catch: Throwable -> L31
        return;
    L31:
        th = move-exception;
        Log.e("tgx", "Cannot start animation", th);
        forceFactor(r10);
    }

    public boolean cancel() {
        if (this.isAnimating == true) goto L5;
        return false;
    L5:
        if (Looper.myLooper() != Looper.getMainLooper()) goto L11;
        setAnimating(false);
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L14;
        r0.cancel();
        this.animator = null;
        return true;
    L14:
        return true;
    L11:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return false;
    }

    public void forceFactor(float r3) {
        boolean r0 = cancel();
        if (setFactor(r3, 1.0f) == true) goto L7;
        if (r0 == true) goto L7;
        return;
    L7:
        this.target.onFactorChangeFinished(this.id, r3, this);
    }

    public float getFactor() {
        return this.factor;
    }

    public float getToFactor() {
        if (this.isAnimating == false) goto L7;
        return this.toFactor;
    L7:
        return this.factor;
    }

    public boolean isAnimating() {
        return this.isAnimating;
    }

    public void setStartDelay(long r1) {
        this.startDelay = r1;
    }
}
