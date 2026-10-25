package org.telegram.ui.recyclerview;

import android.animation.ValueAnimator;
import org.telegram.ui.Cells.ChatActionCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatListItemAnimator$$ExternalSyntheticLambda7 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ChatActionCell.TransitionParams f$0;
    public final /* synthetic */ ChatActionCell f$1;

    public /* synthetic */ ChatListItemAnimator$$ExternalSyntheticLambda7(ChatActionCell.TransitionParams r1, ChatActionCell r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        ChatListItemAnimator.$r8$lambda$51q0GWi0ObdvhDKFihDT07qKlQQ(this.f$0, this.f$1, r2);
    }
}
