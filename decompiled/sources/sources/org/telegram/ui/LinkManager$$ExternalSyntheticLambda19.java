package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$$ExternalSyntheticLambda19 implements Runnable {
    public final /* synthetic */ LinkManager f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_inputInvoiceSlug f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ LinkManager$$ExternalSyntheticLambda19(LinkManager r1, TLRPC.TL_error r2, TLObject r3, TLRPC.TL_inputInvoiceSlug r4, String r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LinkManager.$r8$lambda$SmkEcCXj4_l5XMhTPvRUEK5ACMw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
