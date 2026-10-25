package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda256 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda256(MessagesController r1, int r2, int r3, int r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        MessagesController.m4552$r8$lambda$Q4Jt5_zEMkk9JVtyNmZKsSFUJ8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
