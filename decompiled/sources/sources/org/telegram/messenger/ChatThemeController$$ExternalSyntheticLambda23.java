package org.telegram.messenger;

import java.util.List;
import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ ChatThemeController f$0;
    public final /* synthetic */ TL_account.Tl_chatThemes f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ List f$3;
    public final /* synthetic */ ResultCallback f$4;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda23(ChatThemeController r1, TL_account.Tl_chatThemes r2, List r3, List r4, ResultCallback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatThemeController.m3925$r8$lambda$T6jSDG816nBfdXxCbRTlrTjMNg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
