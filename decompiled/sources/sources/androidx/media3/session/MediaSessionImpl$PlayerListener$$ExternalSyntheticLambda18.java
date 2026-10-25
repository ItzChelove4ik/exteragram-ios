package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda18 implements MediaSessionImpl.RemoteControllerTask {
    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda18() {
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r1, int r2) {
        r1.onRenderedFirstFrame(r2);
    }
}
