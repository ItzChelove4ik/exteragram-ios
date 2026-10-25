package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class TranscribeButton$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ ChatMessageCell.ChatMessageCellDelegate f$0;
    public final /* synthetic */ TLRPC.TL_messages_transcribedAudio f$1;

    public /* synthetic */ TranscribeButton$$ExternalSyntheticLambda8(ChatMessageCell.ChatMessageCellDelegate r1, TLRPC.TL_messages_transcribedAudio r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TranscribeButton.m13377$r8$lambda$a2o22si0EBawgKyJsqzCtlJsVw(this.f$0, this.f$1);
    }
}
