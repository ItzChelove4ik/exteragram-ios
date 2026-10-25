package com.exteragram.messenger.utils.network;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda12 implements RequestDelegate {
    public final /* synthetic */ int f$0;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda12(int r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        RemoteUtils.m1795$r8$lambda$fbLQZhiW34Arj2DTfMUPETNMPM(this.f$0, r1, r2);
    }
}
