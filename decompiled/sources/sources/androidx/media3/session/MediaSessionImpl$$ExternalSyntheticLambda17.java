package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda17 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ SessionPositionInfo f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ MediaSession.ControllerInfo f$3;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda17(SessionPositionInfo r1, boolean r2, boolean r3, MediaSession.ControllerInfo r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r7, int r8) {
        MediaSessionImpl.m878$r8$lambda$uCr7tcCAHn1WP3r1i67jXmDWhY(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
