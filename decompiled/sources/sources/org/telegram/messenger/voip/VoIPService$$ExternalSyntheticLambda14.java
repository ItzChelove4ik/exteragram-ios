package org.telegram.messenger.voip;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda14 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda14(VoIPService r1, TLRPC.TL_error r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.m5324$r8$lambda$qqAIsdDet_LNigi29u8llEimts(this.f$0, this.f$1, this.f$2);
    }
}
