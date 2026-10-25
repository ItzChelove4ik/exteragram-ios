package org.telegram.messenger.voip;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ConferenceCall$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ ConferenceCall f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ ConferenceCall$$ExternalSyntheticLambda2(ConferenceCall r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r4, TLRPC.TL_error r5) {
        ConferenceCall.$r8$lambda$8GIeGLSxMmbhX51Xeiq_1VoOqG0(this.f$0, this.f$1, r4, r5);
    }
}
