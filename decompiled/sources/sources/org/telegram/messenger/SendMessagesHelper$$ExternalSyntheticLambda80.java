package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda80 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.Message f$2;
    public final /* synthetic */ TLRPC.Message f$3;
    public final /* synthetic */ TLRPC.Peer f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ ArrayList f$6;
    public final /* synthetic */ long f$7;
    public final /* synthetic */ int f$8;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda80(SendMessagesHelper r1, int r2, TLRPC.Message r3, TLRPC.Message r4, TLRPC.Peer r5, int r6, ArrayList r7, long r8, int r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$oX_cl05KblL8RXK72V4G5wvxLjU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
