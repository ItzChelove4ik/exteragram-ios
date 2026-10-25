package org.telegram.messenger.voip;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ConferenceCall$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ ConferenceCall f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ ConferenceCall$$ExternalSyntheticLambda7(ConferenceCall r1, long r2, TLObject r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConferenceCall.$r8$lambda$2sXCvmJRE96Bq3OCBu1Yz6cAvKo(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
