package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LinkManager$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ LinkManager f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ LinkManager$$ExternalSyntheticLambda8(LinkManager r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LinkManager.$r8$lambda$mlwVGQ4BT5w8GSLhQdu07x8PgA8(this.f$0, this.f$1, r2, r3);
    }
}
