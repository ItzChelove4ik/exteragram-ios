package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ContactsController$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ ContactsController f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ ContactsController$$ExternalSyntheticLambda6(ContactsController r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ContactsController.$r8$lambda$LVPnt8kERKq7ysQ2jLhMqkcyOCk(this.f$0, this.f$1, r2, r3);
    }
}
