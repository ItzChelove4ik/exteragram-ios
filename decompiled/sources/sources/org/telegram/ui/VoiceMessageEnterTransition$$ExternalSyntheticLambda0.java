package org.telegram.ui;

import android.animation.ValueAnimator;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoiceMessageEnterTransition$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ VoiceMessageEnterTransition f$0;
    public final /* synthetic */ MessageEnterTransitionContainer f$1;

    public /* synthetic */ VoiceMessageEnterTransition$$ExternalSyntheticLambda0(VoiceMessageEnterTransition r1, MessageEnterTransitionContainer r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        VoiceMessageEnterTransition.$r8$lambda$raLLVGaOp4ltFAb_PE9og98roqU(this.f$0, this.f$1, r2);
    }
}
