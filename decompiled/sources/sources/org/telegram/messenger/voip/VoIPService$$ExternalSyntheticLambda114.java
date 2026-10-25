package org.telegram.messenger.voip;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda114 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda114(VoIPService r1, TLObject r2, int r3, String r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.m5310$r8$lambda$VCq2r4K4Hgj3QFJSVewPs9UUgw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
