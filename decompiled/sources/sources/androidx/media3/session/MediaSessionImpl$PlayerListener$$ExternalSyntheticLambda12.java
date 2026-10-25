package androidx.media3.session;

import androidx.media3.common.Timeline;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda12 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ Timeline f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda12(Timeline r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r2, int r3) {
        MediaSessionImpl.PlayerListener.$r8$lambda$MCfqAsOdHwPMsu9DmvzciysL290(this.f$0, this.f$1, r2, r3);
    }
}
