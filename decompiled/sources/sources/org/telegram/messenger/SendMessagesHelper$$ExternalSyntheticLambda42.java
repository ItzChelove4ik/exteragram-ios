package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda42 implements Utilities.Callback2 {
    public final /* synthetic */ SendMessagesHelper f$0;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda42(SendMessagesHelper r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        SendMessagesHelper.m4919$r8$lambda$QzZ81a6exdLALDMmxCSurGR3fw(this.f$0, (TLRPC.Updates) r1, (TLRPC.TL_error) r2);
    }
}
