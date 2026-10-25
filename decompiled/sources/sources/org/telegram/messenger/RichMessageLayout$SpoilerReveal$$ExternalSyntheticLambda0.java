package org.telegram.messenger;

import android.animation.ValueAnimator;
import android.view.View;
import org.telegram.messenger.RichMessageLayout;

/* loaded from: classes5.dex */
public final /* synthetic */ class RichMessageLayout$SpoilerReveal$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ RichMessageLayout.SpoilerReveal f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ RichMessageLayout$SpoilerReveal$$ExternalSyntheticLambda0(RichMessageLayout.SpoilerReveal r1, View r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        RichMessageLayout.SpoilerReveal.m4861$r8$lambda$oUwmpVBwcCiqGBU8dZzzI6lgcI(this.f$0, this.f$1, r2);
    }
}
