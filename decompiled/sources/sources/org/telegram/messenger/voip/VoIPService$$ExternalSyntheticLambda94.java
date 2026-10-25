package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda94 implements RequestDelegate {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda94(VoIPService r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        VoIPService.$r8$lambda$fv1sqAhZ2aUYVg0HRmQOuLqFHl4(this.f$0, this.f$1, r2, r3);
    }
}
