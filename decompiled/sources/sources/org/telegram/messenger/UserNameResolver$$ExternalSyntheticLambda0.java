package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class UserNameResolver$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ UserNameResolver f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ UserNameResolver$$ExternalSyntheticLambda0(UserNameResolver r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        UserNameResolver.m5048$r8$lambda$ve6_M5vATNUu36dqpXLE6eIH4(this.f$0, this.f$1, r2, r3);
    }
}
