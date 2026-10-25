package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda11 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ PlayerWrapper f$1;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda11(int r1, PlayerWrapper r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r2, int r3) {
        MediaSessionImpl.PlayerListener.$r8$lambda$ZVGaSeuTadNkFzpw0tZnQzGRbsM(this.f$0, this.f$1, r2, r3);
    }
}
