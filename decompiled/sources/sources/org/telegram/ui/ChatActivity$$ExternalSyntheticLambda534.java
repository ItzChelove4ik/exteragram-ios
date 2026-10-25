package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda534 implements Utilities.Callback2 {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda534(ChatActivity r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ChatActivity.$r8$lambda$Hh6pPOti4hxkM4nYOhqNGNAY9uI(this.f$0, this.f$1, (TL_account.webPagePreview) r2, (TLRPC.TL_error) r3);
    }
}
