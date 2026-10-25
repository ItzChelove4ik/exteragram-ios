package org.telegram.messenger.voip;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda46 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda46(VoIPService r1, TLRPC.TL_error r2, TLObject r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.m5329$r8$lambda$x1h7GKPOdb9CIPz45jTbzlaUg(this.f$0, this.f$1, this.f$2);
    }
}
