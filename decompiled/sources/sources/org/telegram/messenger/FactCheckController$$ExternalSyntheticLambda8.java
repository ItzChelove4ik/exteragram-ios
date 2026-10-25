package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class FactCheckController$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.TL_factCheck f$1;

    public /* synthetic */ FactCheckController$$ExternalSyntheticLambda8(MessagesStorage r1, TLRPC.TL_factCheck r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FactCheckController.m4000$r8$lambda$hwZvWtn8X40ulbUsYqEavkirk8(this.f$0, this.f$1);
    }
}
