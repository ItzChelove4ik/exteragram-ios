package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda29 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ SessionCommand f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda29(SessionCommand r1, Bundle r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl r2, MediaSession.ControllerInfo r3, int r4) {
        return MediaSessionStub.$r8$lambda$vZhZQF2vW4WdI1dX10QEfSas5VE(this.f$0, this.f$1, r2, r3, r4);
    }
}
