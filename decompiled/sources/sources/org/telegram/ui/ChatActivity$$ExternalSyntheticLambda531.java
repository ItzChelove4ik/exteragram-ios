package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda531 implements Utilities.Callback2 {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TL_account.getWebPagePreview f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda531(ChatActivity r1, TL_account.getWebPagePreview r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        ChatActivity.m7515$r8$lambda$j3l16P23aWyZUdVriijwdEF8oc(this.f$0, this.f$1, this.f$2, (Boolean) r3, (TLRPC.WebPage) r4);
    }
}
