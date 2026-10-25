package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda2 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda2(int r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r2, int r3) {
        MediaSessionImpl.PlayerListener.$r8$lambda$qS38FDgcvaM_UZAyz6CaGWc2GMg(this.f$0, this.f$1, r2, r3);
    }
}
