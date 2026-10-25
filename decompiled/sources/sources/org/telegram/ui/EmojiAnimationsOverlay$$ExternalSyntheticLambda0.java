package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class EmojiAnimationsOverlay$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ EmojiAnimationsOverlay f$0;
    public final /* synthetic */ MessageObject f$1;

    public /* synthetic */ EmojiAnimationsOverlay$$ExternalSyntheticLambda0(EmojiAnimationsOverlay r1, MessageObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        EmojiAnimationsOverlay.m14700$r8$lambda$Pfe5rKYxtxhuWZHF66lUqbz0xc(this.f$0, this.f$1, r2, r3);
    }
}
