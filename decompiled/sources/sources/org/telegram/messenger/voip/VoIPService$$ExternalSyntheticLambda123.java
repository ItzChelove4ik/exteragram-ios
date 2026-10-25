package org.telegram.messenger.voip;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda123 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda123(VoIPService r1, TLObject r2, TLRPC.TL_error r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.m5328$r8$lambda$vUja21ssNK4LheXNmu3Z2DoPk(this.f$0, this.f$1, this.f$2);
    }
}
