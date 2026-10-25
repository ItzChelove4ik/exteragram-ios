package org.telegram.messenger.voip;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda105 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLRPC.Updates f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda105(VoIPService r1, TLRPC.Updates r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.m5303$r8$lambda$HIX0weVwPpoX9zNFJYXIS5UHwk(this.f$0, this.f$1, this.f$2);
    }
}
