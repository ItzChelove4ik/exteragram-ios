package org.telegram.messenger;

import androidx.core.util.Consumer;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda521 implements Runnable {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda521(Consumer r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4590$r8$lambda$czRESAjDHYYArlS963jwGBnyMA(this.f$0, this.f$1);
    }
}
