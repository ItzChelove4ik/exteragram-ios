package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda78 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_inputChatPhoto f$1;
    public final /* synthetic */ TLRPC.FileLocation f$2;
    public final /* synthetic */ TLRPC.FileLocation f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda78(MessagesController r1, TLRPC.TL_inputChatPhoto r2, TLRPC.FileLocation r3, TLRPC.FileLocation r4, String r5, long r6, Runnable r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        MessagesController.$r8$lambda$DD_1GPNiTa1gNmY8j7pEk8DU8bg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
