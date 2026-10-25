package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda19 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ TLRPC.InputChannel f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ long f$5;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda19(MessagesStorage r1, long r2, int r4, TLRPC.InputChannel r5, int r6, long r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4710$r8$lambda$NNgqnOymp6EAsnDTSYNzf0GnI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
