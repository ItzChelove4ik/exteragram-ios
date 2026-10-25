package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda35 implements RequestDelegate {
    public final /* synthetic */ CallLogActivity f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda35(CallLogActivity r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        CallLogActivity.m6518$r8$lambda$dLleP_JXuFgdRE8qZKi2yebA6w(this.f$0, this.f$1, r2, r3);
    }
}
