package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ LinkManager f$0;
    public final /* synthetic */ TLRPC.TL_inputInvoiceSlug f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ LinkManager$$ExternalSyntheticLambda0(LinkManager r1, TLRPC.TL_inputInvoiceSlug r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        LinkManager.$r8$lambda$Ip5w2MszpA_BOiRYbERrSelV2gA(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
