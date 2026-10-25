package org.telegram.ui.Stars;

import android.animation.ValueAnimator;
import org.telegram.ui.Stars.SuperRipple;

/* loaded from: classes7.dex */
public final /* synthetic */ class SuperRipple$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SuperRipple f$0;
    public final /* synthetic */ SuperRipple.Effect f$1;

    public /* synthetic */ SuperRipple$$ExternalSyntheticLambda0(SuperRipple r1, SuperRipple.Effect r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        SuperRipple.$r8$lambda$ZXV_F6ezEVeW5a8ZNPcF59eQdA4(this.f$0, this.f$1, r2);
    }
}
