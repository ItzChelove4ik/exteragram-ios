package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import com.google.android.material.shape.MaterialShapeDrawable;

/* loaded from: classes5.dex */
public final /* synthetic */ class AppBarLayout$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AppBarLayout f$0;
    public final /* synthetic */ MaterialShapeDrawable f$1;

    public /* synthetic */ AppBarLayout$$ExternalSyntheticLambda1(AppBarLayout r1, MaterialShapeDrawable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        AppBarLayout.$r8$lambda$PpQ1I3993Gy02bYDF0fSWxB_DpQ(this.f$0, this.f$1, r2);
    }
}
