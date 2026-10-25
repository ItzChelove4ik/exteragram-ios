package org.telegram.ui.Components.quickforward;

import android.view.animation.Interpolator;

/* loaded from: classes7.dex */
public final /* synthetic */ class QuickShareSelectorDrawable$$ExternalSyntheticLambda2 implements Interpolator {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ Interpolator f$3;

    public /* synthetic */ QuickShareSelectorDrawable$$ExternalSyntheticLambda2(boolean r1, float r2, float r3, Interpolator r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float r4) {
        return QuickShareSelectorDrawable.m13756$r8$lambda$TFdbEg5B8rng8gyNxj41ZbRLAs(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
