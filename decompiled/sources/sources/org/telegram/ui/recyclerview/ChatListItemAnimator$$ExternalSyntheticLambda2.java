package org.telegram.ui.recyclerview;

import android.animation.ValueAnimator;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.recyclerview.ChatListItemAnimator;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatListItemAnimator$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ChatListItemAnimator.MoveInfoExtended f$0;
    public final /* synthetic */ ChatMessageCell.TransitionParams f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ float f$4;
    public final /* synthetic */ ChatMessageCell f$5;
    public final /* synthetic */ int[] f$6;
    public final /* synthetic */ RecyclerView.ViewHolder f$7;

    public /* synthetic */ ChatListItemAnimator$$ExternalSyntheticLambda2(ChatListItemAnimator.MoveInfoExtended r1, ChatMessageCell.TransitionParams r2, boolean r3, float r4, float r5, ChatMessageCell r6, int[] r7, RecyclerView.ViewHolder r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r10) {
        ChatListItemAnimator.m21931$r8$lambda$om2k4wYSnG7ii6RhLEuKuITF6s(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r10);
    }
}
