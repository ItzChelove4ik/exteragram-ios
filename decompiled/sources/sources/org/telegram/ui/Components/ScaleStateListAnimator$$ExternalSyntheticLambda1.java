package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import java.util.function.Consumer;

/* loaded from: classes3.dex */
public final /* synthetic */ class ScaleStateListAnimator$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ ScaleStateListAnimator$$ExternalSyntheticLambda1(Consumer r1) {
        this.f$0 = r1;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r1) {
        ScaleStateListAnimator.m12500$r8$lambda$tDVRH9xfWOFEBlCBT529PtiqFs(this.f$0, r1);
    }
}
