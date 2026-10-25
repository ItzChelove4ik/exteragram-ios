package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class TranscribeButton$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ ChatMessageCell.ChatMessageCellDelegate f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ TranscribeButton$$ExternalSyntheticLambda3(int r1, ChatMessageCell.ChatMessageCellDelegate r2, MessageObject r3, long r4, long r6, int r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        TranscribeButton.$r8$lambda$WVaNQpZB4bOQY85fS5DzEIvQlF0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r11, r12);
    }
}
