package org.telegram.ui;

import android.animation.ValueAnimator;
import android.view.View;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatPullingDownDrawable$$ExternalSyntheticLambda6 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ChatPullingDownDrawable f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ ChatPullingDownDrawable$$ExternalSyntheticLambda6(ChatPullingDownDrawable r1, View r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        ChatPullingDownDrawable.$r8$lambda$1jB9sw1tKCBNYD7eO5TXjc96q44(this.f$0, this.f$1, r2);
    }
}
