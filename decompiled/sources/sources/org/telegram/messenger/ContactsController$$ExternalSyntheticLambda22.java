package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class ContactsController$$ExternalSyntheticLambda22 implements RequestDelegate {
    public final /* synthetic */ ContactsController f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ ContactsController$$ExternalSyntheticLambda22(ContactsController r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r4, TLRPC.TL_error r5) {
        ContactsController.$r8$lambda$LPXaMnhvb9vvbhLiZsqb60MvmRY(this.f$0, this.f$1, r4, r5);
    }
}
