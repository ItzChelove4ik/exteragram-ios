package org.telegram.ui.Components.voip;

import android.animation.ValueAnimator;

/* loaded from: classes7.dex */
public final /* synthetic */ class VoIpGradientLayout$$ExternalSyntheticLambda3 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ VoIpGradientLayout f$0;
    public final /* synthetic */ VoIPBackgroundProvider f$1;

    public /* synthetic */ VoIpGradientLayout$$ExternalSyntheticLambda3(VoIpGradientLayout r1, VoIPBackgroundProvider r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        VoIpGradientLayout.$r8$lambda$24GHYzt0oxBGS1BxzfqmWhlW_c0(this.f$0, this.f$1, r2);
    }
}
