package org.telegram.tgnet;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class ConnectionsManager$$ExternalSyntheticLambda25 implements Runnable {
    public final /* synthetic */ ConnectionsManager f$0;
    public final /* synthetic */ RequestDelegate f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ RequestDelegateTimestamp f$4;
    public final /* synthetic */ long f$5;

    public /* synthetic */ ConnectionsManager$$ExternalSyntheticLambda25(ConnectionsManager r1, RequestDelegate r2, TLObject r3, TLRPC.TL_error r4, RequestDelegateTimestamp r5, long r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConnectionsManager.m5381$r8$lambda$60ImWesLOOpedPbBSgNTbSmlkU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
