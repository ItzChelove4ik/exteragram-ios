package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotWebViewSheet$$ExternalSyntheticLambda10 implements Utilities.Callback2 {
    public final /* synthetic */ BotWebViewSheet f$0;

    public /* synthetic */ BotWebViewSheet$$ExternalSyntheticLambda10(BotWebViewSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        BotWebViewSheet.m21361$r8$lambda$i51czUNVwE0XuE5LZv5CWWALJY(this.f$0, (TLRPC.TL_webViewResultUrl) r1, (TLRPC.TL_error) r2);
    }
}
