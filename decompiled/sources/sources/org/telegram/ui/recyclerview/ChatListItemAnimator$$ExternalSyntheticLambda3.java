package org.telegram.ui.recyclerview;

import android.animation.ValueAnimator;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.recyclerview.ChatListItemAnimator;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatListItemAnimator$$ExternalSyntheticLambda3 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ChatListItemAnimator.MoveInfoExtended f$0;
    public final /* synthetic */ ChatMessageCell.TransitionParams f$1;
    public final /* synthetic */ ChatMessageCell f$2;

    public /* synthetic */ ChatListItemAnimator$$ExternalSyntheticLambda3(ChatListItemAnimator.MoveInfoExtended r1, ChatMessageCell.TransitionParams r2, ChatMessageCell r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        ChatListItemAnimator.$r8$lambda$8W2CifkKWRLeTD6jc3A2ApMzDzg(this.f$0, this.f$1, this.f$2, r3);
    }
}
