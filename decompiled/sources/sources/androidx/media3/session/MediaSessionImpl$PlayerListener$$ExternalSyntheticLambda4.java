package androidx.media3.session;

import androidx.media3.common.PlaybackException;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda4 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ PlaybackException f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda4(PlaybackException r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r1, int r2) {
        MediaSessionImpl.PlayerListener.m882$r8$lambda$AGlR_sz6D1gU6mnA8wOm9Ttk20(this.f$0, r1, r2);
    }
}
