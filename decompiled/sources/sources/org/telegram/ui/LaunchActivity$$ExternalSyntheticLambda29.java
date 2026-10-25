package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda29 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda29(LaunchActivity r1, TLRPC.TL_messages_requestUrlAuth r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        LaunchActivity.m15635$r8$lambda$nsI2TlPIaWR0QFFQL6LRSfknWQ(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
