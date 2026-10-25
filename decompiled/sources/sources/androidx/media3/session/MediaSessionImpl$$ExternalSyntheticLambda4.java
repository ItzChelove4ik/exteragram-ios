package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda4 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ PlayerWrapper f$0;
    public final /* synthetic */ PlayerWrapper f$1;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda4(PlayerWrapper r1, PlayerWrapper r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r2, int r3) {
        MediaSessionImpl.m866$r8$lambda$8Imq7BY3Fm0wc7OUOm25HBznu0(this.f$0, this.f$1, r2, r3);
    }
}
