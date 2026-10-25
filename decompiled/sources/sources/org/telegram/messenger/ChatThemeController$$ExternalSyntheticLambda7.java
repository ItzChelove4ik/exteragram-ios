package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda7 implements Utilities.Callback2 {
    public final /* synthetic */ ChatThemeController f$0;
    public final /* synthetic */ ResultCallback f$1;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda7(ChatThemeController r1, ResultCallback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ChatThemeController.$r8$lambda$nJ5_4MFWZlRingpABu3V9ucLTQA(this.f$0, this.f$1, (TL_account.ChatThemes) r2, (TLRPC.TL_error) r3);
    }
}
