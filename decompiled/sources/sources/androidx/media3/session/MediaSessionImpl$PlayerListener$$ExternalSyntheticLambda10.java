package androidx.media3.session;

import androidx.media3.common.Tracks;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda10 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ Tracks f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda10(Tracks r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r1, int r2) {
        MediaSessionImpl.PlayerListener.$r8$lambda$Aj9EoK_619_Fv4CrKWwvCYledKw(this.f$0, r1, r2);
    }
}
