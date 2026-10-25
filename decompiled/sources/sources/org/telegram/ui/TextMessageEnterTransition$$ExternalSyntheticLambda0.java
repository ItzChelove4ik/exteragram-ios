package org.telegram.ui;

import android.animation.ValueAnimator;
import org.telegram.ui.Components.ChatActivityEnterView;

/* loaded from: classes6.dex */
public final /* synthetic */ class TextMessageEnterTransition$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ TextMessageEnterTransition f$0;
    public final /* synthetic */ ChatActivityEnterView f$1;
    public final /* synthetic */ MessageEnterTransitionContainer f$2;

    public /* synthetic */ TextMessageEnterTransition$$ExternalSyntheticLambda0(TextMessageEnterTransition r1, ChatActivityEnterView r2, MessageEnterTransitionContainer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        TextMessageEnterTransition.m20605$r8$lambda$6vnSlCW6akk5wcBuICaajIguRk(this.f$0, this.f$1, this.f$2, r3);
    }
}
