package androidx.media3.session;

import androidx.media3.session.ConnectedControllersManager;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda80 implements ConnectedControllersManager.AsyncCommand {
    public final /* synthetic */ MediaSessionStub.SessionTask f$0;
    public final /* synthetic */ MediaSessionImpl f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda80(MediaSessionStub.SessionTask r1, MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.media3.session.ConnectedControllersManager.AsyncCommand
    public final ListenableFuture run() {
        return MediaSessionStub.$r8$lambda$RRPw6LAp1NW6DX57p_2Wvz9dOWQ(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
