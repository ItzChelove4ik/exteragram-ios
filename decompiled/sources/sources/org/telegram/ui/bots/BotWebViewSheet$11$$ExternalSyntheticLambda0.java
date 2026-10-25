package org.telegram.ui.bots;

import org.telegram.messenger.AnimationNotificationsLocker;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotWebViewSheet$11$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AnimationNotificationsLocker f$0;

    public /* synthetic */ BotWebViewSheet$11$$ExternalSyntheticLambda0(AnimationNotificationsLocker r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.unlock();
    }
}
