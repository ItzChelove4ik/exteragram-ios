package androidx.media3.session;

import androidx.media3.common.MediaMetadata;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda17 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ MediaMetadata f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda17(MediaMetadata r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r1, int r2) {
        MediaSessionImpl.PlayerListener.$r8$lambda$v0mCKdUiJrbkBzdqVTuZOYGKSC8(this.f$0, r1, r2);
    }
}
