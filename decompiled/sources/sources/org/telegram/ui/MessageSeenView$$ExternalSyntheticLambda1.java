package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class MessageSeenView$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MessageSeenView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ TLRPC.Chat f$5;

    public /* synthetic */ MessageSeenView$$ExternalSyntheticLambda1(MessageSeenView r1, TLRPC.TL_error r2, TLObject r3, long r4, int r6, TLRPC.Chat r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessageSeenView.$r8$lambda$b3Rbbzbuw3GSg4hyrcPTvrQpe00(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
