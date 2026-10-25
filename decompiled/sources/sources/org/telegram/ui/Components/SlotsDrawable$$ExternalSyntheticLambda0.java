package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class SlotsDrawable$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SlotsDrawable f$0;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ ChatMessageCell f$4;

    public /* synthetic */ SlotsDrawable$$ExternalSyntheticLambda0(SlotsDrawable r1, TLRPC.TL_messages_stickerSet r2, int r3, MessageObject r4, ChatMessageCell r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SlotsDrawable.$r8$lambda$SwaipdUDkyQOAr0vEYM6lOjS19Q(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
