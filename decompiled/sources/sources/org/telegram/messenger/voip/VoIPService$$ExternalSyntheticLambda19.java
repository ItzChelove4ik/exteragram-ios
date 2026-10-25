package org.telegram.messenger.voip;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda19 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda19(VoIPService r1, TLObject r2, TLRPC.TL_error r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.m5293$r8$lambda$c5BB_eYeJTsYOlmaC4XfUN1L3Y(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
