package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda115 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.Reaction f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ Utilities.Callback4 f$7;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda115(MessagesStorage r1, String r2, long r3, TLRPC.Reaction r5, int r6, int r7, boolean r8, Utilities.Callback4 r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$Bf4ZyIUpEomPfjE9bb9A_D2PwrU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
