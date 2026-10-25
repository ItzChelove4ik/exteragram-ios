package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ConferenceCall$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ ConferenceCall f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ ConferenceCall$$ExternalSyntheticLambda6(ConferenceCall r1, long r2, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ConferenceCall.m5270$r8$lambda$JG3ekOsA1eClXwOxvusPIcG9E4(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
