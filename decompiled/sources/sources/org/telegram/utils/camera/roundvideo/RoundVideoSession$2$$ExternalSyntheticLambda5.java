package org.telegram.utils.camera.roundvideo;

import org.telegram.utils.camera.roundvideo.RoundVideoSession;

/* loaded from: classes6.dex */
public final /* synthetic */ class RoundVideoSession$2$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ RoundVideoSession.Listener f$0;

    public /* synthetic */ RoundVideoSession$2$$ExternalSyntheticLambda5(RoundVideoSession.Listener r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.onCameraSwitchFirstFrame();
    }
}
