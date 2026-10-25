package com.exteragram.messenger.utils.network;

import org.telegram.messenger.AccountInstance;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ AccountInstance f$0;
    public final /* synthetic */ TLRPC.TL_messages_getHistory f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda9(AccountInstance r1, TLRPC.TL_messages_getHistory r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RemoteUtils.m1792$r8$lambda$E3fegTDkhtibGgJJ4Zwo5Ymw0(this.f$0, this.f$1, this.f$2);
    }
}
