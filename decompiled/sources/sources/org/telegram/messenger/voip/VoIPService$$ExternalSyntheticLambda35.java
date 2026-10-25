package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegateTimestamp;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda35 implements RequestDelegateTimestamp {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda35(VoIPService r1, int r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegateTimestamp
    public final void run(TLObject r9, TLRPC.TL_error r10, long r11) {
        VoIPService.$r8$lambda$zkH3SpyycjE2jFzZrPKfN1FUano(this.f$0, this.f$1, this.f$2, r9, r10, r11);
    }
}
