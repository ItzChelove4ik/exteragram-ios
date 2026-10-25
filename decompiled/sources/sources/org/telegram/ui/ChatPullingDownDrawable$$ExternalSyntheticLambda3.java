package org.telegram.ui;

import android.animation.ValueAnimator;
import android.view.View;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatPullingDownDrawable$$ExternalSyntheticLambda3 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ChatPullingDownDrawable f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ ChatPullingDownDrawable$$ExternalSyntheticLambda3(ChatPullingDownDrawable r1, View r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        ChatPullingDownDrawable.m8382$r8$lambda$D8hVLqwqOvClm2syOTcM_dg8nk(this.f$0, this.f$1, r2);
    }
}
