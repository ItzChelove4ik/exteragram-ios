package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegateTimestamp;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda16 implements RequestDelegateTimestamp {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ int f$5;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda16(VoIPService r1, String r2, int r3, long r4, int r6, int r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegateTimestamp
    public final void run(TLObject r12, TLRPC.TL_error r13, long r14) {
        VoIPService.$r8$lambda$tPV9733HwegDcXm_LqQG5ezlsQE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r12, r13, r14);
    }
}
