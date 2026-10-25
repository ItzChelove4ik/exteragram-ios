package org.telegram.ui.web;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda20 implements Utilities.Callback2 {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$1;
    public final /* synthetic */ BotWebViewContainer.BotWebViewProxy f$2;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda20(BotWebViewContainer r1, BotWebViewContainer.WebRequestContext r2, BotWebViewContainer.BotWebViewProxy r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        BotWebViewContainer.$r8$lambda$JrH7COzs1n74R4yEvdR0iM7Rffs(this.f$0, this.f$1, this.f$2, (String) r3, (ArrayList) r4);
    }
}
