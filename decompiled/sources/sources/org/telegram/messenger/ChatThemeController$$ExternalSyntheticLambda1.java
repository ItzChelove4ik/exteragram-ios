package org.telegram.messenger;

import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ResultCallback f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda1(ResultCallback r1, TLRPC.TL_error r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatThemeController.$r8$lambda$CZY0ep9kvRBHPwJUOO1LpHzFWPE(this.f$0, this.f$1);
    }
}
