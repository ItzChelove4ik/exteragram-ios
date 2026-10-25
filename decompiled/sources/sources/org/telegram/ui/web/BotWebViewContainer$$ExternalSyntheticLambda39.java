package org.telegram.ui.web;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda39 implements Runnable {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.TL_inputInvoiceSlug f$3;
    public final /* synthetic */ TLObject f$4;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda39(BotWebViewContainer r1, TLRPC.TL_error r2, String r3, TLRPC.TL_inputInvoiceSlug r4, TLObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BotWebViewContainer.$r8$lambda$fMzyHdeDmfgvULRkWJty0ZkYkxk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
