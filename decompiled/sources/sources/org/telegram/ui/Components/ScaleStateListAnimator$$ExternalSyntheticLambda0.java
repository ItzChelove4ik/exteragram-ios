package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import java.util.function.Consumer;

/* loaded from: classes3.dex */
public final /* synthetic */ class ScaleStateListAnimator$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ ScaleStateListAnimator$$ExternalSyntheticLambda0(Consumer r1) {
        this.f$0 = r1;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r1) {
        ScaleStateListAnimator.$r8$lambda$J6zip7LVAceW8YA6qsT51JKlmDU(this.f$0, r1);
    }
}
