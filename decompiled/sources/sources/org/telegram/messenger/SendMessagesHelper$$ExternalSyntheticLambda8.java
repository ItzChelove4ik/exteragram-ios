package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLRPC.Message f$3;
    public final /* synthetic */ TLObject f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ HashMap f$6;
    public final /* synthetic */ String f$7;
    public final /* synthetic */ TLObject f$8;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda8(SendMessagesHelper r1, boolean r2, TLRPC.TL_error r3, TLRPC.Message r4, TLObject r5, MessageObject r6, HashMap r7, String r8, TLObject r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$vHE4e2ivIoMuOKnVMv9eYUvr24M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
