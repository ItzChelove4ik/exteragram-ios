package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda64 implements Runnable {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda64(LaunchActivity r1, TLObject r2, TLRPC.TL_messages_requestUrlAuth r3, String r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LaunchActivity.m15624$r8$lambda$dGCM2XliGVg6h2QoNvd1KffWkU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
