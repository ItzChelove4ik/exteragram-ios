package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class AutoMessageHeardReceiver$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ AccountInstance f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ AutoMessageHeardReceiver$$ExternalSyntheticLambda2(AccountInstance r1, TLRPC.User r2, int r3, long r4, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AutoMessageHeardReceiver.$r8$lambda$JZ0ZVRnE_mudffloArbdprRwmHY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
