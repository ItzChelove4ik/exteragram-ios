package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda276 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.TL_contacts_getBlocked f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda276(MessagesController r1, boolean r2, TLRPC.TL_contacts_getBlocked r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MessagesController.m4487$r8$lambda$1jmbmkZZYsKq_FnyAudR29JQeE(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
