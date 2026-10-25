package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import com.google.android.material.shape.MaterialShapeDrawable;

/* loaded from: classes5.dex */
public final /* synthetic */ class AppBarLayout$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AppBarLayout f$0;
    public final /* synthetic */ ColorStateList f$1;
    public final /* synthetic */ MaterialShapeDrawable f$2;
    public final /* synthetic */ Integer f$3;

    public /* synthetic */ AppBarLayout$$ExternalSyntheticLambda0(AppBarLayout r1, ColorStateList r2, MaterialShapeDrawable r3, Integer r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r4) {
        AppBarLayout.$r8$lambda$pWyguk4NB6CmY3Ase6MwHO_zXKU(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
