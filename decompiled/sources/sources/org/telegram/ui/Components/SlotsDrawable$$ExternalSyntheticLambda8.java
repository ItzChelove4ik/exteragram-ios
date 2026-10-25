package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class SlotsDrawable$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ TLRPC.Document f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ ChatMessageCell f$3;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$4;

    public /* synthetic */ SlotsDrawable$$ExternalSyntheticLambda8(TLRPC.Document r1, int r2, MessageObject r3, ChatMessageCell r4, TLRPC.TL_messages_stickerSet r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SlotsDrawable.m13035$r8$lambda$9KLqIfaDM0m_YCfSseYHuHp5_Y(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
