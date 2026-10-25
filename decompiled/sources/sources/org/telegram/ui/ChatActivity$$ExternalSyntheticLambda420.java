package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda420 implements Utilities.Callback2 {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TL_account.getWebPagePreview f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda420(ChatActivity r1, int r2, TL_account.getWebPagePreview r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        ChatActivity.$r8$lambda$L2U_IhyMuHCRXWmKxocRJs_4K88(this.f$0, this.f$1, this.f$2, (Boolean) r3, (TLRPC.WebPage) r4);
    }
}
