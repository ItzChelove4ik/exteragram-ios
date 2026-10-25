package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ContactsController$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ ContactsController f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ ContactsController$$ExternalSyntheticLambda8(ContactsController r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ContactsController.m3950$r8$lambda$Fmo1HFpCkIxcaM_lWI0g1Nk2QE(this.f$0, this.f$1, r2, r3);
    }
}
