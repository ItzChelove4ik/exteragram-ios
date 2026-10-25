package com.exteragram.messenger.utils.network;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda11 implements Utilities.Callback {
    public final /* synthetic */ TLRPC.TL_messages_getHistory f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda11(TLRPC.TL_messages_getHistory r1, Runnable r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        RemoteUtils.$r8$lambda$3SpRrpMm50vpRClqPPGUprUYYYw(this.f$0, this.f$1, this.f$2, (TLRPC.Chat) r3);
    }
}
