package org.telegram.ui;

import org.telegram.messenger.NotificationCenter;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectAnimatedEmojiDialog$25$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ NotificationCenter f$0;

    public /* synthetic */ SelectAnimatedEmojiDialog$25$$ExternalSyntheticLambda0(NotificationCenter r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.runDelayedNotifications();
    }
}
