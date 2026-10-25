package org.telegram.messenger.voip;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda39 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLRPC.Updates f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda39(VoIPService r1, TLRPC.Updates r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.$r8$lambda$wuP5F8kDIisuicXB2OkG0Gjh2Ug(this.f$0, this.f$1, this.f$2);
    }
}
