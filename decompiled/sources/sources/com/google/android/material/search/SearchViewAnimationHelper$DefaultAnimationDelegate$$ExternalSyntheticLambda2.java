package com.google.android.material.search;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.google.android.material.search.SearchViewAnimationHelper;

/* loaded from: classes5.dex */
public final /* synthetic */ class SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SearchViewAnimationHelper.DefaultAnimationDelegate f$0;
    public final /* synthetic */ Rect f$1;

    public /* synthetic */ SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda2(SearchViewAnimationHelper.DefaultAnimationDelegate r1, Rect r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        SearchViewAnimationHelper.DefaultAnimationDelegate.m1897$r8$lambda$mm9Vl7gBm_L8DAbthXBLxaIDrw(this.f$0, this.f$1, r2);
    }
}
