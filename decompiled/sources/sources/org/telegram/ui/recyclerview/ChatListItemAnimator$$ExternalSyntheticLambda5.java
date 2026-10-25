package org.telegram.ui.recyclerview;

import android.animation.ValueAnimator;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatListItemAnimator$$ExternalSyntheticLambda5 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ChatMessageCell.TransitionParams f$0;
    public final /* synthetic */ ChatMessageCell f$1;

    public /* synthetic */ ChatListItemAnimator$$ExternalSyntheticLambda5(ChatMessageCell.TransitionParams r1, ChatMessageCell r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        ChatListItemAnimator.$r8$lambda$OQamxP6pcjwMza4x4sJNjqcje74(this.f$0, this.f$1, r2);
    }
}
