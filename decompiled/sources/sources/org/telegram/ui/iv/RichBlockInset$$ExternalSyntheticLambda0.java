package org.telegram.ui.iv;

import android.animation.ValueAnimator;
import org.telegram.ui.iv.RichBlockInset;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichBlockInset$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ RichBlockInset f$0;
    public final /* synthetic */ RichBlockInset.Applier f$1;

    public /* synthetic */ RichBlockInset$$ExternalSyntheticLambda0(RichBlockInset r1, RichBlockInset.Applier r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        RichBlockInset.$r8$lambda$Di3KT2Inc4EglP7yhvJIoVFWmds(this.f$0, this.f$1, r2);
    }
}
