package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.bots.BotWebViewSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotWebViewSheet$3$$ExternalSyntheticLambda16 implements Utilities.CallbackReturn {
    public final /* synthetic */ TLRPC.TL_error f$0;

    public /* synthetic */ BotWebViewSheet$3$$ExternalSyntheticLambda16(TLRPC.TL_error r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r1) {
        return BotWebViewSheet.AnonymousClass3.$r8$lambda$PuO7iSwAPxuRmDbkiIHVJtjRVx4(this.f$0, (BulletinFactory) r1);
    }
}
