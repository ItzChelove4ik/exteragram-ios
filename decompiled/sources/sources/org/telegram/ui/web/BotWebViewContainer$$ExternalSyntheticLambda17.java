package org.telegram.ui.web;

import org.json.JSONObject;
import org.telegram.messenger.Utilities;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda17 implements Utilities.Callback {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$1;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda17(BotWebViewContainer r1, BotWebViewContainer.WebRequestContext r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        BotWebViewContainer.$r8$lambda$z8xOhAOilDkdM4BRRCxQdZxjT28(this.f$0, this.f$1, (JSONObject) r2);
    }
}
