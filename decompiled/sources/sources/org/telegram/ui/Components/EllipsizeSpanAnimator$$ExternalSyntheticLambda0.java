package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import org.telegram.ui.Components.EllipsizeSpanAnimator;

/* loaded from: classes3.dex */
public final /* synthetic */ class EllipsizeSpanAnimator$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ EllipsizeSpanAnimator f$0;
    public final /* synthetic */ EllipsizeSpanAnimator.TextAlphaSpan f$1;

    public /* synthetic */ EllipsizeSpanAnimator$$ExternalSyntheticLambda0(EllipsizeSpanAnimator r1, EllipsizeSpanAnimator.TextAlphaSpan r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        EllipsizeSpanAnimator.$r8$lambda$PNZdAHm7dfH31ADedZCUSlzjEwk(this.f$0, this.f$1, r2);
    }
}
