package com.google.android.material.search;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.google.android.material.search.SearchViewAnimationHelper;

/* loaded from: classes5.dex */
public final /* synthetic */ class SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SearchViewAnimationHelper.DefaultAnimationDelegate f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ float[] f$2;
    public final /* synthetic */ Rect f$3;

    public /* synthetic */ SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda1(SearchViewAnimationHelper.DefaultAnimationDelegate r1, float r2, float[] r3, Rect r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r4) {
        SearchViewAnimationHelper.DefaultAnimationDelegate.$r8$lambda$qIGmc5_uOjMwP8RsayWx6fvxohk(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
