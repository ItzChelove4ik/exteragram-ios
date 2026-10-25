package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda243 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.TL_updates_channelDifferenceTooLong f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda243(MessagesStorage r1, long r2, int r4, TLRPC.TL_updates_channelDifferenceTooLong r5, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4708$r8$lambda$LGIQIy2kmD9IlRRRLykeXGa99I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
