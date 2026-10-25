package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.graphics.Rect;

/* loaded from: classes5.dex */
public final /* synthetic */ class ExpandCollapseAnimationHelper$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ExpandCollapseAnimationHelper f$0;
    public final /* synthetic */ Rect f$1;

    public /* synthetic */ ExpandCollapseAnimationHelper$$ExternalSyntheticLambda0(ExpandCollapseAnimationHelper r1, Rect r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        ExpandCollapseAnimationHelper.$r8$lambda$rNtcHfP3zBxS8d5wSmAlJ8Csw9c(this.f$0, this.f$1, r2);
    }
}
