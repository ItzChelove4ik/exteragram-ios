package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda297 implements RequestDelegate {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda297(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        MessagesController.m4607$r8$lambda$k6W2QeMxiIPCC5blSEGkYoYpmQ(this.f$0, r1, r2);
    }
}
