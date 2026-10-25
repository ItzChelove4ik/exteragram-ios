package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.animation.Interpolator;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes3.dex */
public class RecyclerListViewScroller {
    int lastScrolled;
    final RecyclerListView recyclerListView;
    ValueAnimator valueAnimator;

    /* renamed from: org.telegram.ui.RecyclerListViewScroller$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ RecyclerListViewScroller this$0;
        final /* synthetic */ int val$dy;
        final /* synthetic */ int[] val$total;

        public AnonymousClass1(RecyclerListViewScroller r1, int r2, int[] r3) {
            this.this$0 = r1;
            this.val$dy = r2;
            this.val$total = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            this.this$0.recyclerListView.scrollBy(0, this.val$dy - this.val$total[0]);
            this.this$0.valueAnimator = null;
        }
    }

    /* renamed from: $r8$lambda$ZdUvmt-igjrX8Bo0_UY3r5Ur3LU, reason: not valid java name */
    public static /* synthetic */ void m18466$r8$lambda$ZdUvmtigjrX8Bo0_UY3r5Ur3LU(RecyclerListViewScroller r0, int[] r1, ValueAnimator r2) {
        r0.lambda$smoothScrollBy$0(r1, r2);
    }

    public RecyclerListViewScroller(RecyclerListView r1) {
        this.recyclerListView = r1;
    }

    private /* synthetic */ void lambda$smoothScrollBy$0(int[] r4, ValueAnimator r5) {
        int r52 = ((Integer) r5.getAnimatedValue()).intValue();
        int r0 = r52 - this.lastScrolled;
        this.recyclerListView.scrollBy(0, r0);
        r4[0] = r4[0] + r0;
        this.lastScrolled = r52;
    }

    public void cancel() {
        ValueAnimator r0 = this.valueAnimator;
        if (r0 == null) goto L6;
        r0.removeAllListeners();
        this.valueAnimator.cancel();
        this.valueAnimator = null;
        return;
    }

    public boolean isRunning() {
        if (this.valueAnimator == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void smoothScrollBy(int r4) {
        smoothScrollBy(r4, 200, CubicBezierInterpolator.DEFAULT);
    }

    public void smoothScrollBy(int r4, long r5, Interpolator r7) {
        ValueAnimator r0 = this.valueAnimator;
        if (r0 == null) goto L5;
        r0.removeAllListeners();
        this.valueAnimator.cancel();
    L5:
        int[] r02 = new int[1];
        this.lastScrolled = 0;
        ValueAnimator r1 = ValueAnimator.ofInt(new int[]{0, r4});
        this.valueAnimator = r1;
        r1.addUpdateListener(new RecyclerListViewScroller$$ExternalSyntheticLambda0(this, r02));
        this.valueAnimator.addListener(new AnonymousClass1(this, r4, r02));
        this.valueAnimator.setDuration(r5);
        this.valueAnimator.setInterpolator(r7);
        this.valueAnimator.start();
    }
}
