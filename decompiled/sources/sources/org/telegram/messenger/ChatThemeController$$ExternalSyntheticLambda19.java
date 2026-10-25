package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda19 implements Utilities.Callback2 {
    public final /* synthetic */ ChatThemeController f$0;
    public final /* synthetic */ ResultCallback f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda19(ChatThemeController r1, ResultCallback r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        ChatThemeController.m3926$r8$lambda$Ta7_jJnF83wlCXwFca2i6wvnY(this.f$0, this.f$1, this.f$2, (TL_account.Themes) r3, (TLRPC.TL_error) r4);
    }
}
