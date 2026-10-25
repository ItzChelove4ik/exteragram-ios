package org.telegram.ui.recyclerview;

import android.animation.ValueAnimator;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.recyclerview.ChatListItemAnimator;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatListItemAnimator$$ExternalSyntheticLambda4 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ MessageObject.GroupedMessages.TransitionParams f$0;
    public final /* synthetic */ ChatListItemAnimator.MoveInfoExtended f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ float f$4;
    public final /* synthetic */ RecyclerListView f$5;

    public /* synthetic */ ChatListItemAnimator$$ExternalSyntheticLambda4(MessageObject.GroupedMessages.TransitionParams r1, ChatListItemAnimator.MoveInfoExtended r2, boolean r3, float r4, float r5, RecyclerListView r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r8) {
        ChatListItemAnimator.$r8$lambda$TDignImIjC3yBCWn8ikobW4ZBn8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
