package org.telegram.ui;

import android.animation.ValueAnimator;

/* loaded from: classes6.dex */
public final /* synthetic */ class RecyclerListViewScroller$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ RecyclerListViewScroller f$0;
    public final /* synthetic */ int[] f$1;

    public /* synthetic */ RecyclerListViewScroller$$ExternalSyntheticLambda0(RecyclerListViewScroller r1, int[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        RecyclerListViewScroller.m18466$r8$lambda$ZdUvmtigjrX8Bo0_UY3r5Ur3LU(this.f$0, this.f$1, r2);
    }
}
