package org.telegram.ui.recyclerview;

import android.animation.ValueAnimator;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatListItemAnimator$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ChatMessageCell f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ float f$4;
    public final /* synthetic */ float f$5;
    public final /* synthetic */ float f$6;
    public final /* synthetic */ float f$7;
    public final /* synthetic */ float f$8;

    public /* synthetic */ ChatListItemAnimator$$ExternalSyntheticLambda0(ChatMessageCell r1, float r2, float r3, float r4, float r5, float r6, float r7, float r8, float r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r11) {
        ChatListItemAnimator.$r8$lambda$jgyXUoF7EMpTRfhVSeeH1Kt7DMA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r11);
    }
}
