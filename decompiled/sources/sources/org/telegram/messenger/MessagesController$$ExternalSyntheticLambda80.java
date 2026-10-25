package org.telegram.messenger;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda80 implements MessagesStorage.IntCallback {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.Dialog f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda80(MessagesController r1, TLRPC.Dialog r2, int r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r7) {
        MessagesController.$r8$lambda$cr4Y2KqB51jSvYfaUnnhfdJMHVs(this.f$0, this.f$1, this.f$2, this.f$3, r7);
    }
}
