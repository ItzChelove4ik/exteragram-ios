package com.google.android.material.search;

import android.animation.ValueAnimator;
import android.view.View;
import com.google.android.material.shape.MaterialShapeDrawable;

/* loaded from: classes5.dex */
public final /* synthetic */ class SearchBarAnimationHelper$$ExternalSyntheticLambda4 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ MaterialShapeDrawable f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ SearchBarAnimationHelper$$ExternalSyntheticLambda4(MaterialShapeDrawable r1, View r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        SearchBarAnimationHelper.$r8$lambda$vZTEmJCpAmQlXswIkwShQsoE92A(this.f$0, this.f$1, r2);
    }
}
