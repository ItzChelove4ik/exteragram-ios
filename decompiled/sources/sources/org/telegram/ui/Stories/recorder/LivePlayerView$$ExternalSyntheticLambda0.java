package org.telegram.ui.Stories.recorder;

import org.telegram.ui.Stories.LivePlayer;

/* loaded from: classes7.dex */
public final /* synthetic */ class LivePlayerView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LivePlayer f$0;

    public /* synthetic */ LivePlayerView$$ExternalSyntheticLambda0(LivePlayer r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.continueStreaming();
    }
}
