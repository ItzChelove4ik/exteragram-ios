package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ BotWebViewContainer f$1;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda2(boolean[] r1, BotWebViewContainer r2, TLRPC.TL_messages_requestUrlAuth r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        OAuthSheet.$r8$lambda$Foy1WD7Lw4Sl81Axg8hb75DEfbQ(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
