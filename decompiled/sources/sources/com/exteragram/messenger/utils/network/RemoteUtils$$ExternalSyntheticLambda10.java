package com.exteragram.messenger.utils.network;

import org.telegram.messenger.AccountInstance;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ TLRPC.TL_messages_getHistory f$0;
    public final /* synthetic */ AccountInstance f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda10(TLRPC.TL_messages_getHistory r1, AccountInstance r2, Runnable r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RemoteUtils.$r8$lambda$UEIegV2IjuUzBjubE_kaeXJS8OE(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
