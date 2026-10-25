package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda530 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ long[] f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda530(ChatActivity r1, Runnable r2, long[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChatActivity.$r8$lambda$l93lqzTIL3bJeofbUVjr8eKudbc(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
