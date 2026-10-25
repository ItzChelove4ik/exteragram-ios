package org.telegram.messenger;

import org.telegram.messenger.BotFullscreenButtons;

/* loaded from: classes5.dex */
public final /* synthetic */ class BotFullscreenButtons$OptionsIcon$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BotFullscreenButtons.OptionsIcon f$0;

    public /* synthetic */ BotFullscreenButtons$OptionsIcon$$ExternalSyntheticLambda0(BotFullscreenButtons.OptionsIcon r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidateSelf();
    }
}
