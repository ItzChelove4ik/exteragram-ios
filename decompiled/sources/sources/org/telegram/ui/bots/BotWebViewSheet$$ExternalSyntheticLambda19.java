package org.telegram.ui.bots;

import org.telegram.ui.bots.BotWebViewSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotWebViewSheet$$ExternalSyntheticLambda19 implements Runnable {
    public final /* synthetic */ BotWebViewSheet.WindowView f$0;

    public /* synthetic */ BotWebViewSheet$$ExternalSyntheticLambda19(BotWebViewSheet.WindowView r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidate();
    }
}
