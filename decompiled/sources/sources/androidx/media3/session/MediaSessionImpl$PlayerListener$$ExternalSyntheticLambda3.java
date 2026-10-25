package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda3 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ Player.PositionInfo f$0;
    public final /* synthetic */ Player.PositionInfo f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda3(Player.PositionInfo r1, Player.PositionInfo r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public final void run(MediaSession.ControllerCb r3, int r4) {
        MediaSessionImpl.PlayerListener.m884$r8$lambda$UlVEMWzon4aHqT7K7NE1IFyiOY(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
