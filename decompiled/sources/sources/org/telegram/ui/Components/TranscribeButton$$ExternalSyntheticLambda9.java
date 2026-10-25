package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class TranscribeButton$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ MessageObject f$0;
    public final /* synthetic */ ChatMessageCell.ChatMessageCellDelegate f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ TranscribeButton$$ExternalSyntheticLambda9(MessageObject r1, ChatMessageCell.ChatMessageCellDelegate r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TranscribeButton.m13379$r8$lambda$oaPhGEe95pO5I2f8R2G6iDBAyg(this.f$0, this.f$1, this.f$2);
    }
}
