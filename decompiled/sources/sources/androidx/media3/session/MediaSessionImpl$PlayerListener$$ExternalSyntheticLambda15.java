package androidx.media3.session;

import androidx.media3.common.PlaybackParameters;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda15 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ PlaybackParameters f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda15(PlaybackParameters r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r1, int r2) {
        MediaSessionImpl.PlayerListener.m883$r8$lambda$JEHtxHrVtnCF8sj1KeQ5NMbuQM(this.f$0, r1, r2);
    }
}
