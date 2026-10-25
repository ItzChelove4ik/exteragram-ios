package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda65 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda65(MessagesController r1, long r2, Utilities.Callback2 r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        MessagesController.m4501$r8$lambda$68EvR01bG_6nYQLpNaMXAIbTG8(this.f$0, this.f$1, this.f$2, (TLRPC.Bool) r7, (TLRPC.TL_error) r8);
    }
}
