package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda85 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSessionStub.MediaItemPlayerTask f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;
    public final /* synthetic */ List f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda85(MediaSessionImpl r1, MediaSessionStub.MediaItemPlayerTask r2, MediaSession.ControllerInfo r3, List r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSessionStub.$r8$lambda$yJgpcYXXKZF9DSZCkx_tkq7p_pA(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
