package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda253 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.Message f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda253(MessagesStorage r1, int r2, long r3, TLRPC.Message r5, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4673$r8$lambda$6RAV56BIbEt7CJZsR5u4PHncI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
