package org.telegram.messenger;

import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda22 implements Runnable {
    public final /* synthetic */ ResultCallback f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda22(ResultCallback r1, TLRPC.TL_error r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatThemeController.m3929$r8$lambda$ykBmjJGB4msxwXltDwEbhBgUI(this.f$0, this.f$1);
    }
}
